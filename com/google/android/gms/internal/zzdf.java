package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzgk
public final class zzdf {
    public static final zzdg zzwI;
    public static final zzdg zzwJ;
    public static final zzdg zzwK;
    public static final zzdg zzwL;
    public static final zzdg zzwM;
    public static final zzdg zzwN;
    public static final zzdg zzwO;
    public static final zzdg zzwP;
    public static final zzdg zzwQ;
    public static final zzdg zzwR;
    public static final zzdg zzwS;
    public static final zzdg zzwT;

    /* renamed from: com.google.android.gms.internal.zzdf.1 */
    static class C10421 implements zzdg {
        C10421() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.2 */
    static class C10432 implements zzdg {
        C10432() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            if (((Boolean) zzby.zzvd.get()).booleanValue()) {
                com_google_android_gms_internal_zzip.zzE(!Boolean.parseBoolean((String) map.get("disabled")));
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.3 */
    static class C10443 implements zzdg {
        C10443() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            String str = (String) map.get("urls");
            if (TextUtils.isEmpty(str)) {
                zzb.zzaE("URLs missing in canOpenURLs GMSG.");
                return;
            }
            String[] split = str.split(",");
            Map hashMap = new HashMap();
            PackageManager packageManager = com_google_android_gms_internal_zzip.getContext().getPackageManager();
            for (String str2 : split) {
                String[] split2 = str2.split(";", 2);
                hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST) != null));
            }
            com_google_android_gms_internal_zzip.zzc("openableURLs", hashMap);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.4 */
    static class C10454 implements zzdg {
        C10454() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            PackageManager packageManager = com_google_android_gms_internal_zzip.getContext().getPackageManager();
            try {
                try {
                    JSONArray jSONArray = new JSONObject((String) map.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).getJSONArray("intents");
                    JSONObject jSONObject = new JSONObject();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String optString = jSONObject2.optString(ShareConstants.WEB_DIALOG_PARAM_ID);
                            Object optString2 = jSONObject2.optString("u");
                            Object optString3 = jSONObject2.optString("i");
                            Object optString4 = jSONObject2.optString("m");
                            Object optString5 = jSONObject2.optString("p");
                            Object optString6 = jSONObject2.optString("c");
                            jSONObject2.optString("f");
                            jSONObject2.optString("e");
                            Intent intent = new Intent();
                            if (!TextUtils.isEmpty(optString2)) {
                                intent.setData(Uri.parse(optString2));
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                intent.setAction(optString3);
                            }
                            if (!TextUtils.isEmpty(optString4)) {
                                intent.setType(optString4);
                            }
                            if (!TextUtils.isEmpty(optString5)) {
                                intent.setPackage(optString5);
                            }
                            if (!TextUtils.isEmpty(optString6)) {
                                String[] split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    intent.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                            try {
                                jSONObject.put(optString, packageManager.resolveActivity(intent, NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REQUEST) != null);
                            } catch (Throwable e) {
                                zzb.zzb("Error constructing openable urls response.", e);
                            }
                        } catch (Throwable e2) {
                            zzb.zzb("Error parsing the intent data.", e2);
                        }
                    }
                    com_google_android_gms_internal_zzip.zzb("openableIntents", jSONObject);
                } catch (JSONException e3) {
                    com_google_android_gms_internal_zzip.zzb("openableIntents", new JSONObject());
                }
            } catch (JSONException e4) {
                com_google_android_gms_internal_zzip.zzb("openableIntents", new JSONObject());
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.5 */
    static class C10465 implements zzdg {
        C10465() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            String str = (String) map.get("u");
            if (str == null) {
                zzb.zzaE("URL missing from click GMSG.");
                return;
            }
            String zzd = zzp.zzbx().zzd(com_google_android_gms_internal_zzip.getContext(), str, com_google_android_gms_internal_zzip.zzha());
            Uri parse = Uri.parse(zzd);
            try {
                zzan zzgU = com_google_android_gms_internal_zzip.zzgU();
                if (zzgU != null && zzgU.zzb(parse)) {
                    parse = zzgU.zza(parse, com_google_android_gms_internal_zzip.getContext());
                }
            } catch (zzao e) {
                zzb.zzaE("Unable to append parameter to URL: " + zzd);
            }
            new zzia(com_google_android_gms_internal_zzip.getContext(), com_google_android_gms_internal_zzip.zzgV().zzIz, parse.toString()).zzgn();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.6 */
    static class C10476 implements zzdg {
        C10476() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            zzd zzgQ = com_google_android_gms_internal_zzip.zzgQ();
            if (zzgQ != null) {
                zzgQ.close();
                return;
            }
            zzgQ = com_google_android_gms_internal_zzip.zzgR();
            if (zzgQ != null) {
                zzgQ.close();
            } else {
                zzb.zzaE("A GMSG tried to close something that wasn't an overlay.");
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.7 */
    static class C10487 implements zzdg {
        C10487() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            com_google_android_gms_internal_zzip.zzD(AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close")));
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.8 */
    static class C10498 implements zzdg {
        C10498() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            String str = (String) map.get("u");
            if (str == null) {
                zzb.zzaE("URL missing from httpTrack GMSG.");
            } else {
                new zzia(com_google_android_gms_internal_zzip.getContext(), com_google_android_gms_internal_zzip.zzgV().zzIz, str).zzgn();
            }
        }
    }

    /* renamed from: com.google.android.gms.internal.zzdf.9 */
    static class C10509 implements zzdg {
        C10509() {
        }

        public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
            zzb.zzaD("Received log message: " + ((String) map.get("string")));
        }
    }

    static {
        zzwI = new C10421();
        zzwJ = new C10443();
        zzwK = new C10454();
        zzwL = new C10465();
        zzwM = new C10476();
        zzwN = new C10487();
        zzwO = new C10498();
        zzwP = new C10509();
        zzwQ = new zzdg() {
            public void zza(zzip com_google_android_gms_internal_zzip, Map<String, String> map) {
                String str = (String) map.get("ty");
                String str2 = (String) map.get("td");
                try {
                    int parseInt = Integer.parseInt((String) map.get("tx"));
                    int parseInt2 = Integer.parseInt(str);
                    int parseInt3 = Integer.parseInt(str2);
                    zzan zzgU = com_google_android_gms_internal_zzip.zzgU();
                    if (zzgU != null) {
                        zzgU.zzab().zza(parseInt, parseInt2, parseInt3);
                    }
                } catch (NumberFormatException e) {
                    zzb.zzaE("Could not parse touch parameters from gmsg.");
                }
            }
        };
        zzwR = new C10432();
        zzwS = new zzdo();
        zzwT = new zzds();
    }
}
