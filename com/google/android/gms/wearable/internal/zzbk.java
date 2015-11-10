package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzbk implements Creator<StorageInfoResponse> {
    static void zza(StorageInfoResponse storageInfoResponse, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, storageInfoResponse.versionCode);
        zzb.zzc(parcel, 2, storageInfoResponse.statusCode);
        zzb.zza(parcel, 3, storageInfoResponse.zzbaQ);
        zzb.zzc(parcel, 4, storageInfoResponse.zzbaS, false);
        zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzhW(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzlh(i);
    }

    public StorageInfoResponse zzhW(Parcel parcel) {
        int i = 0;
        int zzaj = zza.zzaj(parcel);
        long j = 0;
        List list = null;
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
                    j = zza.zzi(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    list = zza.zzc(parcel, zzai, PackageStorageInfo.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaj) {
            return new StorageInfoResponse(i2, i, j, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }

    public StorageInfoResponse[] zzlh(int i) {
        return new StorageInfoResponse[i];
    }
}
