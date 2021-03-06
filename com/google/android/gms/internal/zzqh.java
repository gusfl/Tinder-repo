package com.google.android.gms.internal;

import com.google.android.gms.internal.zzaf.zzf;
import com.google.android.gms.internal.zzaf.zzj;
import com.viewpagerindicator.C3169d.C3168f;
import java.io.IOException;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public interface zzqh {

    public static final class zza extends zzrr<zza> {
        public long zzaTt;
        public zzj zzaTu;
        public zzf zziR;

        public zza() {
            zzBo();
        }

        public static zza zzs(byte[] bArr) throws zzrw {
            return (zza) zzrx.zza(new zza(), bArr);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzqh_zza = (zza) obj;
            if (this.zzaTt != com_google_android_gms_internal_zzqh_zza.zzaTt) {
                return false;
            }
            if (this.zziR == null) {
                if (com_google_android_gms_internal_zzqh_zza.zziR != null) {
                    return false;
                }
            } else if (!this.zziR.equals(com_google_android_gms_internal_zzqh_zza.zziR)) {
                return false;
            }
            if (this.zzaTu == null) {
                if (com_google_android_gms_internal_zzqh_zza.zzaTu != null) {
                    return false;
                }
            } else if (!this.zzaTu.equals(com_google_android_gms_internal_zzqh_zza.zzaTu)) {
                return false;
            }
            return zza((zzrr) com_google_android_gms_internal_zzqh_zza);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zziR == null ? 0 : this.zziR.hashCode()) + ((((int) (this.zzaTt ^ (this.zzaTt >>> 32))) + 527) * 31)) * 31;
            if (this.zzaTu != null) {
                i = this.zzaTu.hashCode();
            }
            return ((hashCode + i) * 31) + zzDk();
        }

        protected int zzB() {
            int zzB = super.zzB() + zzrq.zzd(1, this.zzaTt);
            if (this.zziR != null) {
                zzB += zzrq.zzc(2, this.zziR);
            }
            return this.zzaTu != null ? zzB + zzrq.zzc(3, this.zzaTu) : zzB;
        }

        public zza zzBo() {
            this.zzaTt = 0;
            this.zziR = null;
            this.zzaTu = null;
            this.zzbca = null;
            this.zzbcl = -1;
            return this;
        }

        public void zza(zzrq com_google_android_gms_internal_zzrq) throws IOException {
            com_google_android_gms_internal_zzrq.zzb(1, this.zzaTt);
            if (this.zziR != null) {
                com_google_android_gms_internal_zzrq.zza(2, this.zziR);
            }
            if (this.zzaTu != null) {
                com_google_android_gms_internal_zzrq.zza(3, this.zzaTu);
            }
            super.zza(com_google_android_gms_internal_zzrq);
        }

        public /* synthetic */ zzrx zzb(zzrp com_google_android_gms_internal_zzrp) throws IOException {
            return zzu(com_google_android_gms_internal_zzrp);
        }

        public zza zzu(zzrp com_google_android_gms_internal_zzrp) throws IOException {
            while (true) {
                int zzCT = com_google_android_gms_internal_zzrp.zzCT();
                switch (zzCT) {
                    case C3374b.SmoothProgressBar_spbStyle /*0*/:
                        break;
                    case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                        this.zzaTt = com_google_android_gms_internal_zzrp.zzCV();
                        continue;
                    case C3168f.Toolbar_collapseContentDescription /*18*/:
                        if (this.zziR == null) {
                            this.zziR = new zzf();
                        }
                        com_google_android_gms_internal_zzrp.zza(this.zziR);
                        continue;
                    case C3168f.Theme_actionMenuTextColor /*26*/:
                        if (this.zzaTu == null) {
                            this.zzaTu = new zzj();
                        }
                        com_google_android_gms_internal_zzrp.zza(this.zzaTu);
                        continue;
                    default:
                        if (!zza(com_google_android_gms_internal_zzrp, zzCT)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }
    }
}
