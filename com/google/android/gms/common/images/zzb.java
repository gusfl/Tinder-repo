package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzb implements Creator<WebImage> {
    static void zza(WebImage webImage, Parcel parcel, int i) {
        int zzak = com.google.android.gms.common.internal.safeparcel.zzb.zzak(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, webImage.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, webImage.getUrl(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, webImage.getWidth());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 4, webImage.getHeight());
        com.google.android.gms.common.internal.safeparcel.zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzab(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzbu(i);
    }

    public WebImage zzab(Parcel parcel) {
        int i = 0;
        int zzaj = zza.zzaj(parcel);
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzaj) {
            Uri uri2;
            int zzg;
            int zzai = zza.zzai(parcel);
            int i4;
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    i4 = i;
                    i = i2;
                    uri2 = uri;
                    zzg = zza.zzg(parcel, zzai);
                    zzai = i4;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    zzg = i3;
                    i4 = i2;
                    uri2 = (Uri) zza.zza(parcel, zzai, Uri.CREATOR);
                    zzai = i;
                    i = i4;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    uri2 = uri;
                    zzg = i3;
                    i4 = i;
                    i = zza.zzg(parcel, zzai);
                    zzai = i4;
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    zzai = zza.zzg(parcel, zzai);
                    i = i2;
                    uri2 = uri;
                    zzg = i3;
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    zzai = i;
                    i = i2;
                    uri2 = uri;
                    zzg = i3;
                    break;
            }
            i3 = zzg;
            uri = uri2;
            i2 = i;
            i = zzai;
        }
        if (parcel.dataPosition() == zzaj) {
            return new WebImage(i3, uri, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }

    public WebImage[] zzbu(int i) {
        return new WebImage[i];
    }
}
