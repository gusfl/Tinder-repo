package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlj;
import com.google.android.gms.internal.zzls;
import com.google.android.gms.internal.zzlt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class SafeParcelResponse extends FastJsonResponse implements SafeParcelable {
    public static final zze CREATOR;
    private final String mClassName;
    private final int mVersionCode;
    private final FieldMappingDictionary zzaeY;
    private final Parcel zzaff;
    private final int zzafg;
    private int zzafh;
    private int zzafi;

    static {
        CREATOR = new zze();
    }

    SafeParcelResponse(int i, Parcel parcel, FieldMappingDictionary fieldMappingDictionary) {
        this.mVersionCode = i;
        this.zzaff = (Parcel) zzx.zzv(parcel);
        this.zzafg = 2;
        this.zzaeY = fieldMappingDictionary;
        if (this.zzaeY == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zzaeY.zzpr();
        }
        this.zzafh = 2;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, FieldMappingDictionary fieldMappingDictionary, String str) {
        this.mVersionCode = 1;
        this.zzaff = Parcel.obtain();
        safeParcelable.writeToParcel(this.zzaff, 0);
        this.zzafg = 1;
        this.zzaeY = (FieldMappingDictionary) zzx.zzv(fieldMappingDictionary);
        this.mClassName = (String) zzx.zzv(str);
        this.zzafh = 2;
    }

    private static HashMap<Integer, Entry<String, Field<?, ?>>> zzE(Map<String, Field<?, ?>> map) {
        HashMap<Integer, Entry<String, Field<?, ?>>> hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(Integer.valueOf(((Field) entry.getValue()).zzpi()), entry);
        }
        return hashMap;
    }

    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse zza(T t) {
        String canonicalName = t.getClass().getCanonicalName();
        return new SafeParcelResponse((SafeParcelable) t, zzb(t), canonicalName);
    }

    private static void zza(FieldMappingDictionary fieldMappingDictionary, FastJsonResponse fastJsonResponse) {
        Class cls = fastJsonResponse.getClass();
        if (!fieldMappingDictionary.zzb(cls)) {
            Map zzpb = fastJsonResponse.zzpb();
            fieldMappingDictionary.zza(cls, zzpb);
            for (String str : zzpb.keySet()) {
                Field field = (Field) zzpb.get(str);
                Class zzpj = field.zzpj();
                if (zzpj != null) {
                    try {
                        zza(fieldMappingDictionary, (FastJsonResponse) zzpj.newInstance());
                    } catch (Throwable e) {
                        throw new IllegalStateException("Could not instantiate an object of type " + field.zzpj().getCanonicalName(), e);
                    } catch (Throwable e2) {
                        throw new IllegalStateException("Could not access object of type " + field.zzpj().getCanonicalName(), e2);
                    }
                }
            }
        }
    }

    private void zza(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
            case C3374b.SmoothProgressBar_spb_color /*1*/:
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
            case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
            case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
            case C3374b.SmoothProgressBar_spb_speed /*5*/:
            case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                stringBuilder.append(obj);
            case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                stringBuilder.append("\"").append(zzls.zzcA(obj.toString())).append("\"");
            case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                stringBuilder.append("\"").append(zzlj.zzi((byte[]) obj)).append("\"");
            case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                stringBuilder.append("\"").append(zzlj.zzj((byte[]) obj));
                stringBuilder.append("\"");
            case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                zzlt.zza(stringBuilder, (HashMap) obj);
            case C3374b.SmoothProgressBar_spb_colors /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void zza(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        switch (field.zzpa()) {
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
                zzb(stringBuilder, (Field) field, zza(field, Integer.valueOf(zza.zzg(parcel, i))));
            case C3374b.SmoothProgressBar_spb_color /*1*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzk(parcel, i)));
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                zzb(stringBuilder, (Field) field, zza(field, Long.valueOf(zza.zzi(parcel, i))));
            case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                zzb(stringBuilder, (Field) field, zza(field, Float.valueOf(zza.zzl(parcel, i))));
            case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                zzb(stringBuilder, (Field) field, zza(field, Double.valueOf(zza.zzm(parcel, i))));
            case C3374b.SmoothProgressBar_spb_speed /*5*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzn(parcel, i)));
            case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                zzb(stringBuilder, (Field) field, zza(field, Boolean.valueOf(zza.zzc(parcel, i))));
            case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzo(parcel, i)));
            case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
            case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                zzb(stringBuilder, (Field) field, zza(field, zza.zzr(parcel, i)));
            case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                zzb(stringBuilder, (Field) field, zza(field, zzi(zza.zzq(parcel, i))));
            case C3374b.SmoothProgressBar_spb_colors /*11*/:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.zzpa());
        }
    }

    private void zza(StringBuilder stringBuilder, String str, Field<?, ?> field, Parcel parcel, int i) {
        stringBuilder.append("\"").append(str).append("\":");
        if (field.zzpl()) {
            zza(stringBuilder, field, parcel, i);
        } else {
            zzb(stringBuilder, field, parcel, i);
        }
    }

    private void zza(StringBuilder stringBuilder, Map<String, Field<?, ?>> map, Parcel parcel) {
        HashMap zzE = zzE(map);
        stringBuilder.append('{');
        int zzaj = zza.zzaj(parcel);
        Object obj = null;
        while (parcel.dataPosition() < zzaj) {
            int zzai = zza.zzai(parcel);
            Entry entry = (Entry) zzE.get(Integer.valueOf(zza.zzbH(zzai)));
            if (entry != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                zza(stringBuilder, (String) entry.getKey(), (Field) entry.getValue(), parcel, zzai);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != zzaj) {
            throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
        }
        stringBuilder.append('}');
    }

    private static FieldMappingDictionary zzb(FastJsonResponse fastJsonResponse) {
        FieldMappingDictionary fieldMappingDictionary = new FieldMappingDictionary(fastJsonResponse.getClass());
        zza(fieldMappingDictionary, fastJsonResponse);
        fieldMappingDictionary.zzpp();
        fieldMappingDictionary.zzpo();
        return fieldMappingDictionary;
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, Parcel parcel, int i) {
        if (field.zzpg()) {
            stringBuilder.append("[");
            switch (field.zzpa()) {
                case C3374b.SmoothProgressBar_spbStyle /*0*/:
                    zzli.zza(stringBuilder, zza.zzu(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    zzli.zza(stringBuilder, zza.zzw(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    zzli.zza(stringBuilder, zza.zzv(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    zzli.zza(stringBuilder, zza.zzx(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    zzli.zza(stringBuilder, zza.zzy(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_speed /*5*/:
                    zzli.zza(stringBuilder, zza.zzz(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                    zzli.zza(stringBuilder, zza.zzt(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                    zzli.zza(stringBuilder, zza.zzA(parcel, i));
                    break;
                case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case C3374b.SmoothProgressBar_spb_colors /*11*/:
                    Parcel[] zzE = zza.zzE(parcel, i);
                    int length = zzE.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            stringBuilder.append(",");
                        }
                        zzE[i2].setDataPosition(0);
                        zza(stringBuilder, field.zzpn(), zzE[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (field.zzpa()) {
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
                stringBuilder.append(zza.zzg(parcel, i));
            case C3374b.SmoothProgressBar_spb_color /*1*/:
                stringBuilder.append(zza.zzk(parcel, i));
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                stringBuilder.append(zza.zzi(parcel, i));
            case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                stringBuilder.append(zza.zzl(parcel, i));
            case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                stringBuilder.append(zza.zzm(parcel, i));
            case C3374b.SmoothProgressBar_spb_speed /*5*/:
                stringBuilder.append(zza.zzn(parcel, i));
            case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                stringBuilder.append(zza.zzc(parcel, i));
            case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                stringBuilder.append("\"").append(zzls.zzcA(zza.zzo(parcel, i))).append("\"");
            case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                stringBuilder.append("\"").append(zzlj.zzi(zza.zzr(parcel, i))).append("\"");
            case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                stringBuilder.append("\"").append(zzlj.zzj(zza.zzr(parcel, i)));
                stringBuilder.append("\"");
            case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                Bundle zzq = zza.zzq(parcel, i);
                Set<String> keySet = zzq.keySet();
                keySet.size();
                stringBuilder.append("{");
                int i3 = 1;
                for (String str : keySet) {
                    if (i3 == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(":");
                    stringBuilder.append("\"").append(zzls.zzcA(zzq.getString(str))).append("\"");
                    i3 = 0;
                }
                stringBuilder.append("}");
            case C3374b.SmoothProgressBar_spb_colors /*11*/:
                Parcel zzD = zza.zzD(parcel, i);
                zzD.setDataPosition(0);
                zza(stringBuilder, field.zzpn(), zzD);
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, Object obj) {
        if (field.zzpf()) {
            zzb(stringBuilder, (Field) field, (ArrayList) obj);
        } else {
            zza(stringBuilder, field.zzoZ(), obj);
        }
    }

    private void zzb(StringBuilder stringBuilder, Field<?, ?> field, ArrayList<?> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(",");
            }
            zza(stringBuilder, field.zzoZ(), arrayList.get(i));
        }
        stringBuilder.append("]");
    }

    public static HashMap<String, String> zzi(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int describeContents() {
        zze com_google_android_gms_common_server_response_zze = CREATOR;
        return 0;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        zzx.zzb(this.zzaeY, (Object) "Cannot convert to JSON on client side.");
        Parcel zzpt = zzpt();
        zzpt.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        zza(stringBuilder, this.zzaeY.zzcx(this.mClassName), zzpt);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze com_google_android_gms_common_server_response_zze = CREATOR;
        zze.zza(this, parcel, i);
    }

    protected Object zzct(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    protected boolean zzcu(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Map<String, Field<?, ?>> zzpb() {
        return this.zzaeY == null ? null : this.zzaeY.zzcx(this.mClassName);
    }

    public Parcel zzpt() {
        switch (this.zzafh) {
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
                this.zzafi = zzb.zzak(this.zzaff);
                zzb.zzH(this.zzaff, this.zzafi);
                this.zzafh = 2;
                break;
            case C3374b.SmoothProgressBar_spb_color /*1*/:
                zzb.zzH(this.zzaff, this.zzafi);
                this.zzafh = 2;
                break;
        }
        return this.zzaff;
    }

    FieldMappingDictionary zzpu() {
        switch (this.zzafg) {
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
                return null;
            case C3374b.SmoothProgressBar_spb_color /*1*/:
                return this.zzaeY;
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                return this.zzaeY;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.zzafg);
        }
    }
}
