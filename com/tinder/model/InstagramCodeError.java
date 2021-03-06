package com.tinder.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import java.io.Serializable;

public class InstagramCodeError implements Serializable {
    private String mError;
    private String mErrorDescription;
    private String mErrorReason;
    private int mStatusCode;

    public static class Builder {
        private String mError;
        private String mErrorDescription;
        private String mErrorReason;
        private int mStatusCode;

        public Builder(String str) {
            this.mError = str;
        }

        @NonNull
        public Builder ErrorReason(String str) {
            this.mErrorReason = str;
            return this;
        }

        @NonNull
        public Builder ErrorDescription(String str) {
            this.mErrorDescription = str;
            return this;
        }

        @NonNull
        public Builder StatusCode(int i) {
            this.mStatusCode = i;
            return this;
        }

        @NonNull
        public InstagramCodeError build() {
            return new InstagramCodeError(this.mErrorReason, this.mErrorDescription, this.mStatusCode, null);
        }
    }

    private InstagramCodeError(String str, String str2, String str3, int i) {
        this.mError = str;
        this.mErrorReason = str2;
        this.mErrorDescription = str3;
        this.mStatusCode = i;
    }

    public boolean hasError() {
        return !TextUtils.isEmpty(this.mError);
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getError() {
        return this.mError;
    }

    public String getErrorReason() {
        return this.mErrorReason;
    }

    public String getErrorDescription() {
        return this.mErrorDescription;
    }
}
