package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.internal.zzlj;
import com.google.android.gms.internal.zzls;
import com.google.android.gms.internal.zzlt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public abstract class FastJsonResponse {

    public interface zza<I, O> {
        I convertBack(O o);

        int zzoZ();

        int zzpa();
    }

    public static class Field<I, O> implements SafeParcelable {
        public static final zza CREATOR;
        private final int mVersionCode;
        protected final int zzaeQ;
        protected final boolean zzaeR;
        protected final int zzaeS;
        protected final boolean zzaeT;
        protected final String zzaeU;
        protected final int zzaeV;
        protected final Class<? extends FastJsonResponse> zzaeW;
        protected final String zzaeX;
        private FieldMappingDictionary zzaeY;
        private zza<I, O> zzaeZ;

        static {
            CREATOR = new zza();
        }

        Field(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, ConverterWrapper converterWrapper) {
            this.mVersionCode = i;
            this.zzaeQ = i2;
            this.zzaeR = z;
            this.zzaeS = i3;
            this.zzaeT = z2;
            this.zzaeU = str;
            this.zzaeV = i4;
            if (str2 == null) {
                this.zzaeW = null;
                this.zzaeX = null;
            } else {
                this.zzaeW = SafeParcelResponse.class;
                this.zzaeX = str2;
            }
            if (converterWrapper == null) {
                this.zzaeZ = null;
            } else {
                this.zzaeZ = converterWrapper.zzoX();
            }
        }

        protected Field(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends FastJsonResponse> cls, zza<I, O> com_google_android_gms_common_server_response_FastJsonResponse_zza_I__O) {
            this.mVersionCode = 1;
            this.zzaeQ = i;
            this.zzaeR = z;
            this.zzaeS = i2;
            this.zzaeT = z2;
            this.zzaeU = str;
            this.zzaeV = i3;
            this.zzaeW = cls;
            if (cls == null) {
                this.zzaeX = null;
            } else {
                this.zzaeX = cls.getCanonicalName();
            }
            this.zzaeZ = com_google_android_gms_common_server_response_FastJsonResponse_zza_I__O;
        }

        public static Field zza(String str, int i, zza<?, ?> com_google_android_gms_common_server_response_FastJsonResponse_zza___, boolean z) {
            return new Field(com_google_android_gms_common_server_response_FastJsonResponse_zza___.zzoZ(), z, com_google_android_gms_common_server_response_FastJsonResponse_zza___.zzpa(), false, str, i, null, com_google_android_gms_common_server_response_FastJsonResponse_zza___);
        }

        public static <T extends FastJsonResponse> Field<T, T> zza(String str, int i, Class<T> cls) {
            return new Field(11, false, 11, false, str, i, cls, null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new Field(11, true, 11, true, str, i, cls, null);
        }

        public static Field<Integer, Integer> zzj(String str, int i) {
            return new Field(0, false, 0, false, str, i, null, null);
        }

        public static Field<Double, Double> zzk(String str, int i) {
            return new Field(4, false, 4, false, str, i, null, null);
        }

        public static Field<Boolean, Boolean> zzl(String str, int i) {
            return new Field(6, false, 6, false, str, i, null, null);
        }

        public static Field<String, String> zzm(String str, int i) {
            return new Field(7, false, 7, false, str, i, null, null);
        }

        public static Field<ArrayList<String>, ArrayList<String>> zzn(String str, int i) {
            return new Field(7, true, 7, true, str, i, null, null);
        }

        public I convertBack(O o) {
            return this.zzaeZ.convertBack(o);
        }

        public int describeContents() {
            zza com_google_android_gms_common_server_response_zza = CREATOR;
            return 0;
        }

        public int getVersionCode() {
            return this.mVersionCode;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Field\n");
            stringBuilder.append("            versionCode=").append(this.mVersionCode).append('\n');
            stringBuilder.append("                 typeIn=").append(this.zzaeQ).append('\n');
            stringBuilder.append("            typeInArray=").append(this.zzaeR).append('\n');
            stringBuilder.append("                typeOut=").append(this.zzaeS).append('\n');
            stringBuilder.append("           typeOutArray=").append(this.zzaeT).append('\n');
            stringBuilder.append("        outputFieldName=").append(this.zzaeU).append('\n');
            stringBuilder.append("      safeParcelFieldId=").append(this.zzaeV).append('\n');
            stringBuilder.append("       concreteTypeName=").append(zzpk()).append('\n');
            if (zzpj() != null) {
                stringBuilder.append("     concreteType.class=").append(zzpj().getCanonicalName()).append('\n');
            }
            stringBuilder.append("          converterName=").append(this.zzaeZ == null ? "null" : this.zzaeZ.getClass().getCanonicalName()).append('\n');
            return stringBuilder.toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zza com_google_android_gms_common_server_response_zza = CREATOR;
            zza.zza(this, parcel, i);
        }

        public void zza(FieldMappingDictionary fieldMappingDictionary) {
            this.zzaeY = fieldMappingDictionary;
        }

        public int zzoZ() {
            return this.zzaeQ;
        }

        public int zzpa() {
            return this.zzaeS;
        }

        public Field<I, O> zzpe() {
            return new Field(this.mVersionCode, this.zzaeQ, this.zzaeR, this.zzaeS, this.zzaeT, this.zzaeU, this.zzaeV, this.zzaeX, zzpm());
        }

        public boolean zzpf() {
            return this.zzaeR;
        }

        public boolean zzpg() {
            return this.zzaeT;
        }

        public String zzph() {
            return this.zzaeU;
        }

        public int zzpi() {
            return this.zzaeV;
        }

        public Class<? extends FastJsonResponse> zzpj() {
            return this.zzaeW;
        }

        String zzpk() {
            return this.zzaeX == null ? null : this.zzaeX;
        }

        public boolean zzpl() {
            return this.zzaeZ != null;
        }

        ConverterWrapper zzpm() {
            return this.zzaeZ == null ? null : ConverterWrapper.zza(this.zzaeZ);
        }

        public Map<String, Field<?, ?>> zzpn() {
            zzx.zzv(this.zzaeX);
            zzx.zzv(this.zzaeY);
            return this.zzaeY.zzcx(this.zzaeX);
        }
    }

    private void zza(StringBuilder stringBuilder, Field field, Object obj) {
        if (field.zzoZ() == 11) {
            stringBuilder.append(((FastJsonResponse) field.zzpj().cast(obj)).toString());
        } else if (field.zzoZ() == 7) {
            stringBuilder.append("\"");
            stringBuilder.append(zzls.zzcA((String) obj));
            stringBuilder.append("\"");
        } else {
            stringBuilder.append(obj);
        }
    }

    private void zza(StringBuilder stringBuilder, Field field, ArrayList<Object> arrayList) {
        stringBuilder.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(stringBuilder, field, obj);
            }
        }
        stringBuilder.append("]");
    }

    public String toString() {
        Map zzpb = zzpb();
        StringBuilder stringBuilder = new StringBuilder(100);
        for (String str : zzpb.keySet()) {
            Field field = (Field) zzpb.get(str);
            if (zza(field)) {
                Object zza = zza(field, zzb(field));
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{");
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append("\"").append(str).append("\":");
                if (zza != null) {
                    switch (field.zzpa()) {
                        case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                            stringBuilder.append("\"").append(zzlj.zzi((byte[]) zza)).append("\"");
                            break;
                        case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                            stringBuilder.append("\"").append(zzlj.zzj((byte[]) zza)).append("\"");
                            break;
                        case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                            zzlt.zza(stringBuilder, (HashMap) zza);
                            break;
                        default:
                            if (!field.zzpf()) {
                                zza(stringBuilder, field, zza);
                                break;
                            }
                            zza(stringBuilder, field, (ArrayList) zza);
                            break;
                    }
                }
                stringBuilder.append("null");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.append("}");
        } else {
            stringBuilder.append("{}");
        }
        return stringBuilder.toString();
    }

    protected <O, I> I zza(Field<I, O> field, Object obj) {
        return field.zzaeZ != null ? field.convertBack(obj) : obj;
    }

    protected boolean zza(Field field) {
        return field.zzpa() == 11 ? field.zzpg() ? zzcw(field.zzph()) : zzcv(field.zzph()) : zzcu(field.zzph());
    }

    protected Object zzb(Field field) {
        String zzph = field.zzph();
        if (field.zzpj() == null) {
            return zzct(field.zzph());
        }
        zzx.zza(zzct(field.zzph()) == null, "Concrete field shouldn't be value object: %s", field.zzph());
        Map zzpd = field.zzpg() ? zzpd() : zzpc();
        if (zzpd != null) {
            return zzpd.get(zzph);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(zzph.charAt(0)) + zzph.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract Object zzct(String str);

    protected abstract boolean zzcu(String str);

    protected boolean zzcv(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    protected boolean zzcw(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public abstract Map<String, Field<?, ?>> zzpb();

    public HashMap<String, Object> zzpc() {
        return null;
    }

    public HashMap<String, Object> zzpd() {
        return null;
    }
}
