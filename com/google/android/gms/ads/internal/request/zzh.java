package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.viewpagerindicator.C3169d.C3168f;
import java.util.List;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzh implements Creator<AdResponseParcel> {
    static void zza(AdResponseParcel adResponseParcel, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, adResponseParcel.versionCode);
        zzb.zza(parcel, 2, adResponseParcel.zzAT, false);
        zzb.zza(parcel, 3, adResponseParcel.body, false);
        zzb.zzb(parcel, 4, adResponseParcel.zzyw, false);
        zzb.zzc(parcel, 5, adResponseParcel.errorCode);
        zzb.zzb(parcel, 6, adResponseParcel.zzyx, false);
        zzb.zza(parcel, 7, adResponseParcel.zzDW);
        zzb.zza(parcel, 8, adResponseParcel.zzDX);
        zzb.zza(parcel, 9, adResponseParcel.zzDY);
        zzb.zzb(parcel, 10, adResponseParcel.zzDZ, false);
        zzb.zza(parcel, 11, adResponseParcel.zzyA);
        zzb.zzc(parcel, 12, adResponseParcel.orientation);
        zzb.zza(parcel, 13, adResponseParcel.zzEa, false);
        zzb.zza(parcel, 14, adResponseParcel.zzEb);
        zzb.zza(parcel, 15, adResponseParcel.zzEc, false);
        zzb.zza(parcel, 19, adResponseParcel.zzEe, false);
        zzb.zza(parcel, 18, adResponseParcel.zzEd);
        zzb.zza(parcel, 21, adResponseParcel.zzEf, false);
        zzb.zza(parcel, 23, adResponseParcel.zzsJ);
        zzb.zza(parcel, 22, adResponseParcel.zzEg);
        zzb.zza(parcel, 25, adResponseParcel.zzEh);
        zzb.zza(parcel, 24, adResponseParcel.zzDG);
        zzb.zzc(parcel, 27, adResponseParcel.zzEj);
        zzb.zza(parcel, 26, adResponseParcel.zzEi);
        zzb.zza(parcel, 29, adResponseParcel.zzEl, false);
        zzb.zza(parcel, 28, adResponseParcel.zzEk, i, false);
        zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzk(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzD(i);
    }

    public AdResponseParcel[] zzD(int i) {
        return new AdResponseParcel[i];
    }

    public AdResponseParcel zzk(Parcel parcel) {
        int zzaj = zza.zzaj(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        List list = null;
        int i2 = 0;
        List list2 = null;
        long j = 0;
        boolean z = false;
        long j2 = 0;
        List list3 = null;
        long j3 = 0;
        int i3 = 0;
        String str3 = null;
        long j4 = 0;
        String str4 = null;
        boolean z2 = false;
        String str5 = null;
        String str6 = null;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        int i4 = 0;
        LargeParcelTeleporter largeParcelTeleporter = null;
        String str7 = null;
        while (parcel.dataPosition() < zzaj) {
            int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    i = zza.zzg(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    str = zza.zzo(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    str2 = zza.zzo(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    list = zza.zzC(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_speed /*5*/:
                    i2 = zza.zzg(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                    list2 = zza.zzC(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                    j = zza.zzi(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                    z = zza.zzc(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                    j2 = zza.zzi(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                    list3 = zza.zzC(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_colors /*11*/:
                    j3 = zza.zzi(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_progressiveStart_activated /*12*/:
                    i3 = zza.zzg(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_background /*13*/:
                    str3 = zza.zzo(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_generate_background_with_colors /*14*/:
                    j4 = zza.zzi(parcel, zzai);
                    break;
                case C3168f.Toolbar_titleMarginBottom /*15*/:
                    str4 = zza.zzo(parcel, zzai);
                    break;
                case C3168f.Toolbar_collapseContentDescription /*18*/:
                    z2 = zza.zzc(parcel, zzai);
                    break;
                case C3168f.Toolbar_navigationIcon /*19*/:
                    str5 = zza.zzo(parcel, zzai);
                    break;
                case C3168f.Theme_actionBarWidgetTheme /*21*/:
                    str6 = zza.zzo(parcel, zzai);
                    break;
                case C3168f.Theme_actionBarSize /*22*/:
                    z3 = zza.zzc(parcel, zzai);
                    break;
                case C3168f.Theme_actionBarDivider /*23*/:
                    z4 = zza.zzc(parcel, zzai);
                    break;
                case C3168f.Theme_actionBarItemBackground /*24*/:
                    z5 = zza.zzc(parcel, zzai);
                    break;
                case C3168f.Theme_actionMenuTextAppearance /*25*/:
                    z6 = zza.zzc(parcel, zzai);
                    break;
                case C3168f.Theme_actionMenuTextColor /*26*/:
                    z7 = zza.zzc(parcel, zzai);
                    break;
                case C3168f.Theme_actionModeStyle /*27*/:
                    i4 = zza.zzg(parcel, zzai);
                    break;
                case C3168f.Theme_actionModeCloseButtonStyle /*28*/:
                    largeParcelTeleporter = (LargeParcelTeleporter) zza.zza(parcel, zzai, LargeParcelTeleporter.CREATOR);
                    break;
                case C3168f.Theme_actionModeBackground /*29*/:
                    str7 = zza.zzo(parcel, zzai);
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaj) {
            return new AdResponseParcel(i, str, str2, list, i2, list2, j, z, j2, list3, j3, i3, str3, j4, str4, z2, str5, str6, z3, z4, z5, z6, z7, i4, largeParcelTeleporter, str7);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }
}
