package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzaz implements Creator<MessageEventParcelable> {
    static void zza(MessageEventParcelable messageEventParcelable, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, messageEventParcelable.mVersionCode);
        zzb.zzc(parcel, 2, messageEventParcelable.getRequestId());
        zzb.zza(parcel, 3, messageEventParcelable.getPath(), false);
        zzb.zza(parcel, 4, messageEventParcelable.getData(), false);
        zzb.zza(parcel, 5, messageEventParcelable.getSourceNodeId(), false);
        zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzhO(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzkZ(i);
    }

    public MessageEventParcelable zzhO(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzaj = zza.zzaj(parcel);
        byte[] bArr = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaj) {
            int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    i2 = zza.zzg(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    i = zza.zzg(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    str2 = zza.zzo(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    bArr = zza.zzr(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_speed /*5*/:
                    str = zza.zzo(parcel, zzai);
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaj) {
            return new MessageEventParcelable(i2, i, str2, bArr, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }

    public MessageEventParcelable[] zzkZ(int i) {
        return new MessageEventParcelable[i];
    }
}
