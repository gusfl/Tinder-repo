package org.apache.http.util;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.location.places.Place;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.protocol.HTTP;

public final class EntityUtils {
    private EntityUtils() {
    }

    public static void consumeQuietly(HttpEntity httpEntity) {
        try {
            consume(httpEntity);
        } catch (IOException e) {
        }
    }

    public static void consume(HttpEntity httpEntity) throws IOException {
        if (httpEntity != null && httpEntity.isStreaming()) {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                content.close();
            }
        }
    }

    public static void updateEntity(HttpResponse httpResponse, HttpEntity httpEntity) throws IOException {
        Args.notNull(httpResponse, "Response");
        consume(httpResponse.getEntity());
        httpResponse.setEntity(httpEntity);
    }

    public static byte[] toByteArray(HttpEntity httpEntity) throws IOException {
        int i = AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD;
        boolean z = false;
        Args.notNull(httpEntity, "Entity");
        InputStream content = httpEntity.getContent();
        if (content == null) {
            return null;
        }
        try {
            if (httpEntity.getContentLength() <= 2147483647L) {
                z = true;
            }
            Args.check(z, "HTTP entity too large to be buffered in memory");
            int contentLength = (int) httpEntity.getContentLength();
            if (contentLength >= 0) {
                i = contentLength;
            }
            ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(i);
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayBuffer.append(bArr, 0, read);
            }
            bArr = byteArrayBuffer.toByteArray();
            return bArr;
        } finally {
            content.close();
        }
    }

    @Deprecated
    public static String getContentCharSet(HttpEntity httpEntity) throws ParseException {
        Args.notNull(httpEntity, "Entity");
        if (httpEntity.getContentType() == null) {
            return null;
        }
        HeaderElement[] elements = httpEntity.getContentType().getElements();
        if (elements.length <= 0) {
            return null;
        }
        NameValuePair parameterByName = elements[0].getParameterByName("charset");
        if (parameterByName != null) {
            return parameterByName.getValue();
        }
        return null;
    }

    @Deprecated
    public static String getContentMimeType(HttpEntity httpEntity) throws ParseException {
        Args.notNull(httpEntity, "Entity");
        if (httpEntity.getContentType() == null) {
            return null;
        }
        HeaderElement[] elements = httpEntity.getContentType().getElements();
        if (elements.length > 0) {
            return elements[0].getName();
        }
        return null;
    }

    public static String toString(HttpEntity httpEntity, Charset charset) throws IOException, ParseException {
        String charArrayBuffer;
        Charset charset2 = null;
        boolean z = false;
        Args.notNull(httpEntity, "Entity");
        InputStream content = httpEntity.getContent();
        if (content != null) {
            try {
                if (httpEntity.getContentLength() <= 2147483647L) {
                    z = true;
                }
                Args.check(z, "HTTP entity too large to be buffered in memory");
                int contentLength = (int) httpEntity.getContentLength();
                if (contentLength < 0) {
                    contentLength = AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD;
                }
                ContentType contentType = ContentType.get(httpEntity);
                if (contentType != null) {
                    charset2 = contentType.getCharset();
                }
                if (charset2 == null) {
                    charset2 = charset;
                }
                if (charset2 == null) {
                    charset2 = HTTP.DEF_CONTENT_CHARSET;
                }
                Reader inputStreamReader = new InputStreamReader(content, charset2);
                CharArrayBuffer charArrayBuffer2 = new CharArrayBuffer(contentLength);
                char[] cArr = new char[Place.TYPE_SUBLOCALITY_LEVEL_2];
                while (true) {
                    int read = inputStreamReader.read(cArr);
                    if (read == -1) {
                        break;
                    }
                    charArrayBuffer2.append(cArr, 0, read);
                }
                charArrayBuffer = charArrayBuffer2.toString();
                content.close();
            } catch (UnsupportedCharsetException e) {
                throw new UnsupportedEncodingException(e.getMessage());
            } catch (Throwable th) {
                content.close();
            }
        }
        return charArrayBuffer;
    }

    public static String toString(HttpEntity httpEntity, String str) throws IOException, ParseException {
        return toString(httpEntity, str != null ? Charset.forName(str) : null);
    }

    public static String toString(HttpEntity httpEntity) throws IOException, ParseException {
        return toString(httpEntity, (Charset) null);
    }
}
