package com.google.android.gms.maps.model;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class StreetViewPanoramaLink implements SafeParcelable {
    public static final zzk CREATOR;
    public final float bearing;
    private final int mVersionCode;
    public final String panoId;

    static {
        CREATOR = new zzk();
    }

    StreetViewPanoramaLink(int i, String str, float f) {
        this.mVersionCode = i;
        this.panoId = str;
        if (((double) f) <= 0.0d) {
            f = (f % 360.0f) + 360.0f;
        }
        this.bearing = f % 360.0f;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        return this.panoId.equals(streetViewPanoramaLink.panoId) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaLink.bearing);
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzw.hashCode(this.panoId, Float.valueOf(this.bearing));
    }

    public String toString() {
        return zzw.zzu(this).zzg("panoId", this.panoId).zzg("bearing", Float.valueOf(this.bearing)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }
}
