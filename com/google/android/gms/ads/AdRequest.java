package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.p007a.C0699a;
import com.google.android.gms.ads.internal.client.zzx;
import com.google.android.gms.ads.internal.client.zzx.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.Set;

public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzx zznN;

    public static final class Builder {
        private final zza zznO;

        public Builder() {
            this.zznO = new zza();
            this.zznO.zzG(AdRequest.DEVICE_ID_EMULATOR);
        }

        public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zznO.zzb((Class) cls, bundle);
            return this;
        }

        public Builder addKeyword(String str) {
            this.zznO.zzF(str);
            return this;
        }

        public Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zznO.zza(networkExtras);
            return this;
        }

        public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zznO.zza(cls, bundle);
            if (cls.equals(C0699a.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zznO.zzH(AdRequest.DEVICE_ID_EMULATOR);
            }
            return this;
        }

        public Builder addTestDevice(String str) {
            this.zznO.zzG(str);
            return this;
        }

        public AdRequest build() {
            return new AdRequest();
        }

        public Builder setBirthday(Date date) {
            this.zznO.zza(date);
            return this;
        }

        public Builder setContentUrl(String str) {
            com.google.android.gms.common.internal.zzx.zzb((Object) str, (Object) "Content URL must be non-null.");
            com.google.android.gms.common.internal.zzx.zzh(str, "Content URL must be non-empty.");
            boolean z = str.length() <= AdRequest.MAX_CONTENT_URL_LENGTH;
            Object[] objArr = new Object[AdRequest.GENDER_FEMALE];
            objArr[AdRequest.GENDER_UNKNOWN] = Integer.valueOf(AdRequest.MAX_CONTENT_URL_LENGTH);
            objArr[AdRequest.GENDER_MALE] = Integer.valueOf(str.length());
            com.google.android.gms.common.internal.zzx.zzb(z, "Content URL must not exceed %d in length.  Provided length was %d.", objArr);
            this.zznO.zzI(str);
            return this;
        }

        public Builder setGender(int i) {
            this.zznO.zzm(i);
            return this;
        }

        public Builder setLocation(Location location) {
            this.zznO.zza(location);
            return this;
        }

        public Builder setRequestAgent(String str) {
            this.zznO.zzK(str);
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean z) {
            this.zznO.zzj(z);
            return this;
        }
    }

    static {
        DEVICE_ID_EMULATOR = zzx.DEVICE_ID_EMULATOR;
    }

    private AdRequest(Builder builder) {
        this.zznN = new zzx(builder.zznO);
    }

    public Date getBirthday() {
        return this.zznN.getBirthday();
    }

    public String getContentUrl() {
        return this.zznN.getContentUrl();
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zznN.getCustomEventExtrasBundle(cls);
    }

    public int getGender() {
        return this.zznN.getGender();
    }

    public Set<String> getKeywords() {
        return this.zznN.getKeywords();
    }

    public Location getLocation() {
        return this.zznN.getLocation();
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zznN.getNetworkExtras(cls);
    }

    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zznN.getNetworkExtrasBundle(cls);
    }

    public boolean isTestDevice(Context context) {
        return this.zznN.isTestDevice(context);
    }

    public zzx zzaF() {
        return this.zznN;
    }
}
