package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class IdToken implements SafeParcelable {
    public static final Creator<IdToken> CREATOR;
    final int mVersionCode;
    private final String zzRf;
    private final String zzRn;

    static {
        CREATOR = new zzd();
    }

    IdToken(int i, String str, String str2) {
        this.mVersionCode = i;
        this.zzRf = str;
        this.zzRn = str2;
    }

    public int describeContents() {
        return 0;
    }

    public String getAccountType() {
        return this.zzRf;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public String zzlv() {
        return this.zzRn;
    }
}
