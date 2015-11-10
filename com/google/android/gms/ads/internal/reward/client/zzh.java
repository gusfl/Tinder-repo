package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzh implements Creator<RewardedVideoAdRequestParcel> {
    static void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, rewardedVideoAdRequestParcel.versionCode);
        zzb.zza(parcel, 2, rewardedVideoAdRequestParcel.zzDy, i, false);
        zzb.zza(parcel, 3, rewardedVideoAdRequestParcel.zzpZ, false);
        zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzo(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzI(i);
    }

    public RewardedVideoAdRequestParcel[] zzI(int i) {
        return new RewardedVideoAdRequestParcel[i];
    }

    public RewardedVideoAdRequestParcel zzo(Parcel parcel) {
        String str = null;
        int zzaj = zza.zzaj(parcel);
        int i = 0;
        AdRequestParcel adRequestParcel = null;
        while (parcel.dataPosition() < zzaj) {
            AdRequestParcel adRequestParcel2;
            int zzg;
            String str2;
            int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    String str3 = str;
                    adRequestParcel2 = adRequestParcel;
                    zzg = zza.zzg(parcel, zzai);
                    str2 = str3;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    zzg = i;
                    AdRequestParcel adRequestParcel3 = (AdRequestParcel) zza.zza(parcel, zzai, AdRequestParcel.CREATOR);
                    str2 = str;
                    adRequestParcel2 = adRequestParcel3;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    str2 = zza.zzo(parcel, zzai);
                    adRequestParcel2 = adRequestParcel;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    str2 = str;
                    adRequestParcel2 = adRequestParcel;
                    zzg = i;
                    break;
            }
            i = zzg;
            adRequestParcel = adRequestParcel2;
            str = str2;
        }
        if (parcel.dataPosition() == zzaj) {
            return new RewardedVideoAdRequestParcel(i, adRequestParcel, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }
}
