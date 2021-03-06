package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzrz {
    final int tag;
    final byte[] zzbcm;

    zzrz(int i, byte[] bArr) {
        this.tag = i;
        this.zzbcm = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzrz)) {
            return false;
        }
        zzrz com_google_android_gms_internal_zzrz = (zzrz) obj;
        return this.tag == com_google_android_gms_internal_zzrz.tag && Arrays.equals(this.zzbcm, com_google_android_gms_internal_zzrz.zzbcm);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzbcm);
    }

    int zzB() {
        return (0 + zzrq.zzlx(this.tag)) + this.zzbcm.length;
    }

    void zza(zzrq com_google_android_gms_internal_zzrq) throws IOException {
        com_google_android_gms_internal_zzrq.zzlw(this.tag);
        com_google_android_gms_internal_zzrq.zzD(this.zzbcm);
    }
}
