package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;

public abstract class ShareMedia implements ShareModel {
    private final Bundle params;

    public static abstract class Builder<M extends ShareMedia, B extends Builder> implements ShareModelBuilder<M, B> {
        private Bundle params;

        public Builder() {
            this.params = new Bundle();
        }

        @Deprecated
        public B setParameter(String str, String str2) {
            this.params.putString(str, str2);
            return this;
        }

        @Deprecated
        public B setParameters(Bundle bundle) {
            this.params.putAll(bundle);
            return this;
        }

        public B readFrom(M m) {
            return m == null ? this : setParameters(m.getParameters());
        }
    }

    protected ShareMedia(Builder builder) {
        this.params = new Bundle(builder.params);
    }

    ShareMedia(Parcel parcel) {
        this.params = parcel.readBundle();
    }

    @Deprecated
    public Bundle getParameters() {
        return new Bundle(this.params);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.params);
    }
}
