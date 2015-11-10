package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzl implements Creator<StreetViewPanoramaLocation> {
    static void zza(StreetViewPanoramaLocation streetViewPanoramaLocation, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaLocation.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaLocation.links, i, false);
        zzb.zza(parcel, 3, streetViewPanoramaLocation.position, i, false);
        zzb.zza(parcel, 4, streetViewPanoramaLocation.panoId, false);
        zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfh(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhH(i);
    }

    public StreetViewPanoramaLocation zzfh(Parcel parcel) {
        String str = null;
        int zzaj = zza.zzaj(parcel);
        int i = 0;
        LatLng latLng = null;
        StreetViewPanoramaLink[] streetViewPanoramaLinkArr = null;
        while (parcel.dataPosition() < zzaj) {
            LatLng latLng2;
            StreetViewPanoramaLink[] streetViewPanoramaLinkArr2;
            int zzg;
            String str2;
            int zzai = zza.zzai(parcel);
            String str3;
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    str3 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    zzg = zza.zzg(parcel, zzai);
                    str2 = str3;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    zzg = i;
                    LatLng latLng3 = latLng;
                    streetViewPanoramaLinkArr2 = (StreetViewPanoramaLink[]) zza.zzb(parcel, zzai, StreetViewPanoramaLink.CREATOR);
                    str2 = str;
                    latLng2 = latLng3;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    zzg = i;
                    str3 = str;
                    latLng2 = (LatLng) zza.zza(parcel, zzai, LatLng.CREATOR);
                    str2 = str3;
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    str2 = zza.zzo(parcel, zzai);
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    str2 = str;
                    latLng2 = latLng;
                    streetViewPanoramaLinkArr2 = streetViewPanoramaLinkArr;
                    zzg = i;
                    break;
            }
            i = zzg;
            streetViewPanoramaLinkArr = streetViewPanoramaLinkArr2;
            latLng = latLng2;
            str = str2;
        }
        if (parcel.dataPosition() == zzaj) {
            return new StreetViewPanoramaLocation(i, streetViewPanoramaLinkArr, latLng, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }

    public StreetViewPanoramaLocation[] zzhH(int i) {
        return new StreetViewPanoramaLocation[i];
    }
}
