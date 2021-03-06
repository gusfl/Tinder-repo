package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CredentialPickerConfig implements SafeParcelable {
    public static final Creator<CredentialPickerConfig> CREATOR;
    private final boolean mShowCancelButton;
    final int mVersionCode;
    private final boolean zzRi;

    public static class Builder {
        private boolean mShowCancelButton;
        private boolean zzRi;

        public Builder() {
            this.zzRi = false;
            this.mShowCancelButton = true;
        }

        public CredentialPickerConfig build() {
            return new CredentialPickerConfig();
        }

        public Builder setShowAddAccountButton(boolean z) {
            this.zzRi = z;
            return this;
        }

        public Builder setShowCancelButton(boolean z) {
            this.mShowCancelButton = z;
            return this;
        }
    }

    static {
        CREATOR = new zzb();
    }

    CredentialPickerConfig(int i, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.zzRi = z;
        this.mShowCancelButton = z2;
    }

    private CredentialPickerConfig(Builder builder) {
        this(1, builder.zzRi, builder.mShowCancelButton);
    }

    public int describeContents() {
        return 0;
    }

    public boolean shouldShowAddAccountButton() {
        return this.zzRi;
    }

    public boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
