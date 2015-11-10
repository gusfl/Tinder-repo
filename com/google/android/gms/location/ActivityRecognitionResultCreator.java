package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class ActivityRecognitionResultCreator implements Creator<ActivityRecognitionResult> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(ActivityRecognitionResult activityRecognitionResult, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, activityRecognitionResult.zzaBt, false);
        zzb.zzc(parcel, LocationStatusCodes.GEOFENCE_NOT_AVAILABLE, activityRecognitionResult.getVersionCode());
        zzb.zza(parcel, 2, activityRecognitionResult.zzaBu);
        zzb.zza(parcel, 3, activityRecognitionResult.zzaBv);
        zzb.zzc(parcel, 4, activityRecognitionResult.zzaBw);
        zzb.zzH(parcel, zzak);
    }

    public ActivityRecognitionResult createFromParcel(Parcel parcel) {
        long j = 0;
        int i = 0;
        int zzaj = zza.zzaj(parcel);
        List list = null;
        long j2 = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzaj) {
            int zzai = zza.zzai(parcel);
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    list = zza.zzc(parcel, zzai, DetectedActivity.CREATOR);
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    j2 = zza.zzi(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    j = zza.zzi(parcel, zzai);
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    i = zza.zzg(parcel, zzai);
                    break;
                case LocationStatusCodes.GEOFENCE_NOT_AVAILABLE /*1000*/:
                    i2 = zza.zzg(parcel, zzai);
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaj) {
            return new ActivityRecognitionResult(i2, list, j2, j, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }

    public ActivityRecognitionResult[] newArray(int i) {
        return new ActivityRecognitionResult[i];
    }
}
