package com.google.android.gms.ads.internal.reward.client;

import com.google.android.gms.ads.internal.reward.client.zzd.zza;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class zzg extends zza {
    private final RewardedVideoAdListener zzGm;

    public zzg(RewardedVideoAdListener rewardedVideoAdListener) {
        this.zzGm = rewardedVideoAdListener;
    }

    public void onRewardedVideoAdClosed() {
        if (this.zzGm != null) {
            this.zzGm.onRewardedVideoAdClosed();
        }
    }

    public void onRewardedVideoAdFailedToLoad(int i) {
        if (this.zzGm != null) {
            this.zzGm.onRewardedVideoAdFailedToLoad(i);
        }
    }

    public void onRewardedVideoAdLeftApplication() {
        if (this.zzGm != null) {
            this.zzGm.onRewardedVideoAdLeftApplication();
        }
    }

    public void onRewardedVideoAdLoaded() {
        if (this.zzGm != null) {
            this.zzGm.onRewardedVideoAdLoaded();
        }
    }

    public void onRewardedVideoAdOpened() {
        if (this.zzGm != null) {
            this.zzGm.onRewardedVideoAdOpened();
        }
    }

    public void onRewardedVideoStarted() {
        if (this.zzGm != null) {
            this.zzGm.onRewardedVideoStarted();
        }
    }

    public void zza(zza com_google_android_gms_ads_internal_reward_client_zza) {
        if (this.zzGm != null) {
            this.zzGm.onRewarded(new zze(com_google_android_gms_ads_internal_reward_client_zza));
        }
    }
}
