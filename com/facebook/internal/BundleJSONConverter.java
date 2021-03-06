package com.facebook.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BundleJSONConverter {
    private static final Map<Class<?>, Setter> SETTERS;

    public interface Setter {
        void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException;

        void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException;
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.1 */
    static class C05241 implements Setter {
        C05241() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.2 */
    static class C05252 implements Setter {
        C05252() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putInt(str, ((Integer) obj).intValue());
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.3 */
    static class C05263 implements Setter {
        C05263() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putLong(str, ((Long) obj).longValue());
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.4 */
    static class C05274 implements Setter {
        C05274() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.5 */
    static class C05285 implements Setter {
        C05285() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putString(str, (String) obj);
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            jSONObject.put(str, obj);
        }
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.6 */
    static class C05296 implements Setter {
        C05296() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            throw new IllegalArgumentException("Unexpected type from JSON");
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            JSONArray jSONArray = new JSONArray();
            for (Object put : (String[]) obj) {
                jSONArray.put(put);
            }
            jSONObject.put(str, jSONArray);
        }
    }

    /* renamed from: com.facebook.internal.BundleJSONConverter.7 */
    static class C05307 implements Setter {
        C05307() {
        }

        public void setOnBundle(Bundle bundle, String str, Object obj) throws JSONException {
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() == 0) {
                bundle.putStringArrayList(str, arrayList);
                return;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                Object obj2 = jSONArray.get(i);
                if (obj2 instanceof String) {
                    arrayList.add((String) obj2);
                    i++;
                } else {
                    throw new IllegalArgumentException("Unexpected type in an array: " + obj2.getClass());
                }
            }
            bundle.putStringArrayList(str, arrayList);
        }

        public void setOnJSON(JSONObject jSONObject, String str, Object obj) throws JSONException {
            throw new IllegalArgumentException("JSONArray's are not supported in bundles.");
        }
    }

    static {
        SETTERS = new HashMap();
        SETTERS.put(Boolean.class, new C05241());
        SETTERS.put(Integer.class, new C05252());
        SETTERS.put(Long.class, new C05263());
        SETTERS.put(Double.class, new C05274());
        SETTERS.put(String.class, new C05285());
        SETTERS.put(String[].class, new C05296());
        SETTERS.put(JSONArray.class, new C05307());
    }

    public static JSONObject convertToJSON(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null) {
                if (obj instanceof List) {
                    JSONArray jSONArray = new JSONArray();
                    for (String put : (List) obj) {
                        jSONArray.put(put);
                    }
                    jSONObject.put(str, jSONArray);
                } else if (obj instanceof Bundle) {
                    jSONObject.put(str, convertToJSON((Bundle) obj));
                } else {
                    Setter setter = (Setter) SETTERS.get(obj.getClass());
                    if (setter == null) {
                        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                    }
                    setter.setOnJSON(jSONObject, str, obj);
                }
            }
        }
        return jSONObject;
    }

    public static Bundle convertToBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (!(obj == null || obj == JSONObject.NULL)) {
                if (obj instanceof JSONObject) {
                    bundle.putBundle(str, convertToBundle((JSONObject) obj));
                } else {
                    Setter setter = (Setter) SETTERS.get(obj.getClass());
                    if (setter == null) {
                        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                    }
                    setter.setOnBundle(bundle, str, obj);
                }
            }
        }
        return bundle;
    }
}
