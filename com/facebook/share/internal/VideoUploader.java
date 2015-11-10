package com.facebook.share.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.internal.WorkQueue;
import com.facebook.internal.WorkQueue.WorkItem;
import com.facebook.share.Sharer.Result;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoUploader {
    private static final String ERROR_BAD_SERVER_RESPONSE = "Unexpected error in server response";
    private static final String ERROR_UPLOAD = "Video upload failed";
    private static final int MAX_RETRIES_PER_PHASE = 2;
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_END_OFFSET = "end_offset";
    private static final String PARAM_FILE_SIZE = "file_size";
    private static final String PARAM_REF = "ref";
    private static final String PARAM_SESSION_ID = "upload_session_id";
    private static final String PARAM_START_OFFSET = "start_offset";
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_UPLOAD_PHASE = "upload_phase";
    private static final String PARAM_VALUE_UPLOAD_FINISH_PHASE = "finish";
    private static final String PARAM_VALUE_UPLOAD_START_PHASE = "start";
    private static final String PARAM_VALUE_UPLOAD_TRANSFER_PHASE = "transfer";
    private static final String PARAM_VIDEO_FILE_CHUNK = "video_file_chunk";
    private static final String PARAM_VIDEO_ID = "video_id";
    private static final int RETRY_DELAY_BACK_OFF_FACTOR = 3;
    private static final int RETRY_DELAY_UNIT_MS = 5000;
    private static final String TAG = "VideoUploader";
    private static final int UPLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static AccessTokenTracker accessTokenTracker;
    private static Handler handler;
    private static boolean initialized;
    private static Set<UploadContext> pendingUploads;
    private static WorkQueue uploadQueue;

    /* renamed from: com.facebook.share.internal.VideoUploader.1 */
    static class C06231 extends AccessTokenTracker {
        C06231() {
        }

        protected void onCurrentAccessTokenChanged(AccessToken accessToken, AccessToken accessToken2) {
            if (accessToken != null) {
                if (accessToken2 == null || !Utility.areObjectsEqual(accessToken2.getUserId(), accessToken.getUserId())) {
                    VideoUploader.cancelAllRequests();
                }
            }
        }
    }

    private static abstract class UploadWorkItemBase implements Runnable {
        protected int completedRetries;
        protected UploadContext uploadContext;

        /* renamed from: com.facebook.share.internal.VideoUploader.UploadWorkItemBase.1 */
        class C06271 implements Runnable {
            C06271() {
            }

            public void run() {
                UploadWorkItemBase.this.enqueueRetry(UploadWorkItemBase.this.completedRetries + 1);
            }
        }

        /* renamed from: com.facebook.share.internal.VideoUploader.UploadWorkItemBase.2 */
        class C06282 implements Runnable {
            final /* synthetic */ FacebookException val$error;
            final /* synthetic */ String val$videoId;

            C06282(FacebookException facebookException, String str) {
                this.val$error = facebookException;
                this.val$videoId = str;
            }

            public void run() {
                VideoUploader.issueResponse(UploadWorkItemBase.this.uploadContext, this.val$error, this.val$videoId);
            }
        }

        protected abstract void enqueueRetry(int i);

        protected abstract Bundle getParameters() throws Exception;

        protected abstract Set<Integer> getTransientErrorCodes();

        protected abstract void handleError(FacebookException facebookException);

        protected abstract void handleSuccess(JSONObject jSONObject) throws JSONException;

        protected UploadWorkItemBase(UploadContext uploadContext, int i) {
            this.uploadContext = uploadContext;
            this.completedRetries = i;
        }

        public void run() {
            if (this.uploadContext.isCanceled) {
                endUploadWithFailure(null);
                return;
            }
            try {
                executeGraphRequestSynchronously(getParameters());
            } catch (FacebookException e) {
                endUploadWithFailure(e);
            } catch (Throwable e2) {
                endUploadWithFailure(new FacebookException(VideoUploader.ERROR_UPLOAD, e2));
            }
        }

        protected void executeGraphRequestSynchronously(Bundle bundle) {
            Bundle bundle2 = bundle;
            GraphResponse executeAndWait = new GraphRequest(this.uploadContext.accessToken, String.format(Locale.ROOT, "%s/videos", new Object[]{this.uploadContext.graphNode}), bundle2, HttpMethod.POST, null).executeAndWait();
            if (executeAndWait != null) {
                FacebookRequestError error = executeAndWait.getError();
                JSONObject jSONObject = executeAndWait.getJSONObject();
                if (error != null) {
                    if (!attemptRetry(error.getSubErrorCode())) {
                        handleError(new FacebookGraphResponseException(executeAndWait, VideoUploader.ERROR_UPLOAD));
                        return;
                    }
                    return;
                } else if (jSONObject != null) {
                    try {
                        handleSuccess(jSONObject);
                        return;
                    } catch (Throwable e) {
                        endUploadWithFailure(new FacebookException(VideoUploader.ERROR_BAD_SERVER_RESPONSE, e));
                        return;
                    }
                } else {
                    handleError(new FacebookException(VideoUploader.ERROR_BAD_SERVER_RESPONSE));
                    return;
                }
            }
            handleError(new FacebookException(VideoUploader.ERROR_BAD_SERVER_RESPONSE));
        }

        private boolean attemptRetry(int i) {
            if (this.completedRetries >= VideoUploader.MAX_RETRIES_PER_PHASE || !getTransientErrorCodes().contains(Integer.valueOf(i))) {
                return false;
            }
            VideoUploader.getHandler().postDelayed(new C06271(), (long) (((int) Math.pow(3.0d, (double) this.completedRetries)) * VideoUploader.RETRY_DELAY_UNIT_MS));
            return true;
        }

        protected void endUploadWithFailure(FacebookException facebookException) {
            issueResponseOnMainThread(facebookException, null);
        }

        protected void issueResponseOnMainThread(FacebookException facebookException, String str) {
            VideoUploader.getHandler().post(new C06282(facebookException, str));
        }
    }

    private static class FinishUploadWorkItem extends UploadWorkItemBase {
        static final Set<Integer> transientErrorCodes;

        /* renamed from: com.facebook.share.internal.VideoUploader.FinishUploadWorkItem.1 */
        static class C06241 extends HashSet<Integer> {
            C06241() {
                add(Integer.valueOf(1363011));
            }
        }

        static {
            transientErrorCodes = new C06241();
        }

        public FinishUploadWorkItem(UploadContext uploadContext, int i) {
            super(uploadContext, i);
        }

        public Bundle getParameters() {
            Bundle bundle = new Bundle();
            if (this.uploadContext.params != null) {
                bundle.putAll(this.uploadContext.params);
            }
            bundle.putString(VideoUploader.PARAM_UPLOAD_PHASE, VideoUploader.PARAM_VALUE_UPLOAD_FINISH_PHASE);
            bundle.putString(VideoUploader.PARAM_SESSION_ID, this.uploadContext.sessionId);
            Utility.putNonEmptyString(bundle, VideoUploader.PARAM_TITLE, this.uploadContext.title);
            Utility.putNonEmptyString(bundle, VideoUploader.PARAM_DESCRIPTION, this.uploadContext.description);
            Utility.putNonEmptyString(bundle, VideoUploader.PARAM_REF, this.uploadContext.ref);
            return bundle;
        }

        protected void handleSuccess(JSONObject jSONObject) throws JSONException {
            if (jSONObject.getBoolean(GraphResponse.SUCCESS_KEY)) {
                issueResponseOnMainThread(null, this.uploadContext.videoId);
            } else {
                handleError(new FacebookException(VideoUploader.ERROR_BAD_SERVER_RESPONSE));
            }
        }

        protected void handleError(FacebookException facebookException) {
            VideoUploader.logError(facebookException, "Video '%s' failed to finish uploading", this.uploadContext.videoId);
            endUploadWithFailure(facebookException);
        }

        protected Set<Integer> getTransientErrorCodes() {
            return transientErrorCodes;
        }

        protected void enqueueRetry(int i) {
            VideoUploader.enqueueUploadFinish(this.uploadContext, i);
        }
    }

    private static class StartUploadWorkItem extends UploadWorkItemBase {
        static final Set<Integer> transientErrorCodes;

        /* renamed from: com.facebook.share.internal.VideoUploader.StartUploadWorkItem.1 */
        static class C06251 extends HashSet<Integer> {
            C06251() {
                add(Integer.valueOf(6000));
            }
        }

        static {
            transientErrorCodes = new C06251();
        }

        public StartUploadWorkItem(UploadContext uploadContext, int i) {
            super(uploadContext, i);
        }

        public Bundle getParameters() {
            Bundle bundle = new Bundle();
            bundle.putString(VideoUploader.PARAM_UPLOAD_PHASE, VideoUploader.PARAM_VALUE_UPLOAD_START_PHASE);
            bundle.putLong(VideoUploader.PARAM_FILE_SIZE, this.uploadContext.videoSize);
            return bundle;
        }

        protected void handleSuccess(JSONObject jSONObject) throws JSONException {
            this.uploadContext.sessionId = jSONObject.getString(VideoUploader.PARAM_SESSION_ID);
            this.uploadContext.videoId = jSONObject.getString(VideoUploader.PARAM_VIDEO_ID);
            VideoUploader.enqueueUploadChunk(this.uploadContext, jSONObject.getString(VideoUploader.PARAM_START_OFFSET), jSONObject.getString(VideoUploader.PARAM_END_OFFSET), 0);
        }

        protected void handleError(FacebookException facebookException) {
            VideoUploader.logError(facebookException, "Error starting video upload", new Object[0]);
            endUploadWithFailure(facebookException);
        }

        protected Set<Integer> getTransientErrorCodes() {
            return transientErrorCodes;
        }

        protected void enqueueRetry(int i) {
            VideoUploader.enqueueUploadStart(this.uploadContext, i);
        }
    }

    private static class TransferChunkWorkItem extends UploadWorkItemBase {
        static final Set<Integer> transientErrorCodes;
        private String chunkEnd;
        private String chunkStart;

        /* renamed from: com.facebook.share.internal.VideoUploader.TransferChunkWorkItem.1 */
        static class C06261 extends HashSet<Integer> {
            C06261() {
                add(Integer.valueOf(1363019));
                add(Integer.valueOf(1363021));
                add(Integer.valueOf(1363030));
                add(Integer.valueOf(1363033));
                add(Integer.valueOf(1363041));
            }
        }

        static {
            transientErrorCodes = new C06261();
        }

        public TransferChunkWorkItem(UploadContext uploadContext, String str, String str2, int i) {
            super(uploadContext, i);
            this.chunkStart = str;
            this.chunkEnd = str2;
        }

        public Bundle getParameters() throws IOException {
            Bundle bundle = new Bundle();
            bundle.putString(VideoUploader.PARAM_UPLOAD_PHASE, VideoUploader.PARAM_VALUE_UPLOAD_TRANSFER_PHASE);
            bundle.putString(VideoUploader.PARAM_SESSION_ID, this.uploadContext.sessionId);
            bundle.putString(VideoUploader.PARAM_START_OFFSET, this.chunkStart);
            byte[] access$600 = VideoUploader.getChunk(this.uploadContext, this.chunkStart, this.chunkEnd);
            if (access$600 != null) {
                bundle.putByteArray(VideoUploader.PARAM_VIDEO_FILE_CHUNK, access$600);
                return bundle;
            }
            throw new FacebookException("Error reading video");
        }

        protected void handleSuccess(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString(VideoUploader.PARAM_START_OFFSET);
            String string2 = jSONObject.getString(VideoUploader.PARAM_END_OFFSET);
            if (Utility.areObjectsEqual(string, string2)) {
                VideoUploader.enqueueUploadFinish(this.uploadContext, 0);
            } else {
                VideoUploader.enqueueUploadChunk(this.uploadContext, string, string2, 0);
            }
        }

        protected void handleError(FacebookException facebookException) {
            VideoUploader.logError(facebookException, "Error uploading video '%s'", this.uploadContext.videoId);
            endUploadWithFailure(facebookException);
        }

        protected Set<Integer> getTransientErrorCodes() {
            return transientErrorCodes;
        }

        protected void enqueueRetry(int i) {
            VideoUploader.enqueueUploadChunk(this.uploadContext, this.chunkStart, this.chunkEnd, i);
        }
    }

    private static class UploadContext {
        public final AccessToken accessToken;
        public final FacebookCallback<Result> callback;
        public String chunkStart;
        public final String description;
        public final String graphNode;
        public boolean isCanceled;
        public Bundle params;
        public final String ref;
        public String sessionId;
        public final String title;
        public String videoId;
        public long videoSize;
        public InputStream videoStream;
        public final Uri videoUri;
        public WorkItem workItem;

        private UploadContext(ShareVideoContent shareVideoContent, String str, FacebookCallback<Result> facebookCallback) {
            this.chunkStart = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.accessToken = AccessToken.getCurrentAccessToken();
            this.videoUri = shareVideoContent.getVideo().getLocalUrl();
            this.title = shareVideoContent.getContentTitle();
            this.description = shareVideoContent.getContentDescription();
            this.ref = shareVideoContent.getRef();
            this.graphNode = str;
            this.callback = facebookCallback;
            this.params = shareVideoContent.getVideo().getParameters();
        }

        private void initialize() throws FileNotFoundException {
            try {
                if (Utility.isFileUri(this.videoUri)) {
                    ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(this.videoUri.getPath()), 268435456);
                    this.videoSize = open.getStatSize();
                    this.videoStream = new AutoCloseInputStream(open);
                } else if (Utility.isContentUri(this.videoUri)) {
                    this.videoSize = Utility.getContentSize(this.videoUri);
                    this.videoStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(this.videoUri);
                } else {
                    throw new FacebookException("Uri must be a content:// or file:// uri");
                }
            } catch (FileNotFoundException e) {
                Utility.closeQuietly(this.videoStream);
                throw e;
            }
        }
    }

    static {
        uploadQueue = new WorkQueue(UPLOAD_QUEUE_MAX_CONCURRENT);
        pendingUploads = new HashSet();
    }

    public static synchronized void uploadAsync(ShareVideoContent shareVideoContent, FacebookCallback<Result> facebookCallback) throws FileNotFoundException {
        synchronized (VideoUploader.class) {
            uploadAsync(shareVideoContent, "me", facebookCallback);
        }
    }

    public static synchronized void uploadAsync(ShareVideoContent shareVideoContent, String str, FacebookCallback<Result> facebookCallback) throws FileNotFoundException {
        synchronized (VideoUploader.class) {
            if (!initialized) {
                registerAccessTokenTracker();
                initialized = true;
            }
            Validate.notNull(shareVideoContent, "videoContent");
            Validate.notNull(str, "graphNode");
            ShareVideo video = shareVideoContent.getVideo();
            Validate.notNull(video, "videoContent.video");
            Validate.notNull(video.getLocalUrl(), "videoContent.video.localUrl");
            UploadContext uploadContext = new UploadContext(str, facebookCallback, null);
            uploadContext.initialize();
            pendingUploads.add(uploadContext);
            enqueueUploadStart(uploadContext, 0);
        }
    }

    private static synchronized void cancelAllRequests() {
        synchronized (VideoUploader.class) {
            for (UploadContext uploadContext : pendingUploads) {
                uploadContext.isCanceled = true;
            }
        }
    }

    private static synchronized void removePendingUpload(UploadContext uploadContext) {
        synchronized (VideoUploader.class) {
            pendingUploads.remove(uploadContext);
        }
    }

    private static synchronized Handler getHandler() {
        Handler handler;
        synchronized (VideoUploader.class) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler = handler;
        }
        return handler;
    }

    private static void issueResponse(UploadContext uploadContext, FacebookException facebookException, String str) {
        removePendingUpload(uploadContext);
        Utility.closeQuietly(uploadContext.videoStream);
        if (uploadContext.callback == null) {
            return;
        }
        if (facebookException != null) {
            ShareInternalUtility.invokeOnErrorCallback(uploadContext.callback, facebookException);
        } else if (uploadContext.isCanceled) {
            ShareInternalUtility.invokeOnCancelCallback(uploadContext.callback);
        } else {
            ShareInternalUtility.invokeOnSuccessCallback(uploadContext.callback, str);
        }
    }

    private static void enqueueUploadStart(UploadContext uploadContext, int i) {
        enqueueRequest(uploadContext, new StartUploadWorkItem(uploadContext, i));
    }

    private static void enqueueUploadChunk(UploadContext uploadContext, String str, String str2, int i) {
        enqueueRequest(uploadContext, new TransferChunkWorkItem(uploadContext, str, str2, i));
    }

    private static void enqueueUploadFinish(UploadContext uploadContext, int i) {
        enqueueRequest(uploadContext, new FinishUploadWorkItem(uploadContext, i));
    }

    private static synchronized void enqueueRequest(UploadContext uploadContext, Runnable runnable) {
        synchronized (VideoUploader.class) {
            uploadContext.workItem = uploadQueue.addActiveWorkItem(runnable);
        }
    }

    private static byte[] getChunk(UploadContext uploadContext, String str, String str2) throws IOException {
        if (Utility.areObjectsEqual(str, uploadContext.chunkStart)) {
            int read;
            int parseLong = (int) (Long.parseLong(str2) - Long.parseLong(str));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[Math.min(Utility.DEFAULT_STREAM_BUFFER_SIZE, parseLong)];
            do {
                read = uploadContext.videoStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                    parseLong -= read;
                    if (parseLong == 0) {
                    }
                }
                uploadContext.chunkStart = str2;
                return byteArrayOutputStream.toByteArray();
            } while (parseLong >= 0);
            Object[] objArr = new Object[MAX_RETRIES_PER_PHASE];
            objArr[0] = Integer.valueOf(parseLong + read);
            objArr[1] = Integer.valueOf(read);
            logError(null, "Error reading video chunk. Expected buffer length - '%d'. Actual - '%d'.", objArr);
            return null;
        }
        Object[] objArr2 = new Object[MAX_RETRIES_PER_PHASE];
        objArr2[0] = uploadContext.chunkStart;
        objArr2[1] = str;
        logError(null, "Error reading video chunk. Expected chunk '%s'. Requested chunk '%s'.", objArr2);
        return null;
    }

    private static void registerAccessTokenTracker() {
        accessTokenTracker = new C06231();
    }

    private static void logError(Exception exception, String str, Object... objArr) {
        Log.e(TAG, String.format(Locale.ROOT, str, objArr), exception);
    }
}
