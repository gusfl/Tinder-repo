package com.google.android.gms.internal;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzw implements zzy {
    protected final HttpClient zzaD;

    public static final class zza extends HttpEntityEnclosingRequestBase {
        public zza(String str) {
            setURI(URI.create(str));
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    public zzw(HttpClient httpClient) {
        this.zzaD = httpClient;
    }

    private static void zza(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, zzk<?> com_google_android_gms_internal_zzk_) throws zza {
        byte[] zzq = com_google_android_gms_internal_zzk_.zzq();
        if (zzq != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(zzq));
        }
    }

    private static void zza(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    static HttpUriRequest zzb(zzk<?> com_google_android_gms_internal_zzk_, Map<String, String> map) throws zza {
        HttpEntityEnclosingRequestBase httpPost;
        switch (com_google_android_gms_internal_zzk_.getMethod()) {
            case ContentLengthStrategy.IDENTITY /*-1*/:
                byte[] zzm = com_google_android_gms_internal_zzk_.zzm();
                if (zzm == null) {
                    return new HttpGet(com_google_android_gms_internal_zzk_.getUrl());
                }
                HttpUriRequest httpPost2 = new HttpPost(com_google_android_gms_internal_zzk_.getUrl());
                httpPost2.addHeader(HTTP.CONTENT_TYPE, com_google_android_gms_internal_zzk_.zzl());
                httpPost2.setEntity(new ByteArrayEntity(zzm));
                return httpPost2;
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
                return new HttpGet(com_google_android_gms_internal_zzk_.getUrl());
            case C3374b.SmoothProgressBar_spb_color /*1*/:
                httpPost = new HttpPost(com_google_android_gms_internal_zzk_.getUrl());
                httpPost.addHeader(HTTP.CONTENT_TYPE, com_google_android_gms_internal_zzk_.zzp());
                zza(httpPost, (zzk) com_google_android_gms_internal_zzk_);
                return httpPost;
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                httpPost = new HttpPut(com_google_android_gms_internal_zzk_.getUrl());
                httpPost.addHeader(HTTP.CONTENT_TYPE, com_google_android_gms_internal_zzk_.zzp());
                zza(httpPost, (zzk) com_google_android_gms_internal_zzk_);
                return httpPost;
            case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                return new HttpDelete(com_google_android_gms_internal_zzk_.getUrl());
            case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                return new HttpHead(com_google_android_gms_internal_zzk_.getUrl());
            case C3374b.SmoothProgressBar_spb_speed /*5*/:
                return new HttpOptions(com_google_android_gms_internal_zzk_.getUrl());
            case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                return new HttpTrace(com_google_android_gms_internal_zzk_.getUrl());
            case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                httpPost = new zza(com_google_android_gms_internal_zzk_.getUrl());
                httpPost.addHeader(HTTP.CONTENT_TYPE, com_google_android_gms_internal_zzk_.zzp());
                zza(httpPost, (zzk) com_google_android_gms_internal_zzk_);
                return httpPost;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
    }

    public HttpResponse zza(zzk<?> com_google_android_gms_internal_zzk_, Map<String, String> map) throws IOException, zza {
        HttpUriRequest zzb = zzb(com_google_android_gms_internal_zzk_, map);
        zza(zzb, (Map) map);
        zza(zzb, com_google_android_gms_internal_zzk_.getHeaders());
        zza(zzb);
        HttpParams params = zzb.getParams();
        int zzt = com_google_android_gms_internal_zzk_.zzt();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, zzt);
        return this.zzaD.execute(zzb);
    }

    protected void zza(HttpUriRequest httpUriRequest) throws IOException {
    }
}
