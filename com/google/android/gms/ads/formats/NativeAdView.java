package com.google.android.gms.ads.formats;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzk;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzcl;

public abstract class NativeAdView extends FrameLayout {
    private final FrameLayout zznY;
    private final zzcl zznZ;

    public NativeAdView(Context context) {
        super(context);
        this.zznY = zzm(context);
        this.zznZ = zzaI();
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zznY = zzm(context);
        this.zznZ = zzaI();
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.zznY = zzm(context);
        this.zznZ = zzaI();
    }

    public NativeAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.zznY = zzm(context);
        this.zznZ = zzaI();
    }

    private zzcl zzaI() {
        zzx.zzb(this.zznY, (Object) "createDelegate must be called after mOverlayFrame has been created");
        return zzk.zzcI().zza(this.zznY.getContext(), this, this.zznY);
    }

    private FrameLayout zzm(Context context) {
        View zzn = zzn(context);
        zzn.setLayoutParams(new LayoutParams(-1, -1));
        addView(zzn);
        return zzn;
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.zznY);
    }

    public void bringChildToFront(View view) {
        super.bringChildToFront(view);
        if (this.zznY != view) {
            super.bringChildToFront(this.zznY);
        }
    }

    public void removeAllViews() {
        super.removeAllViews();
        super.addView(this.zznY);
    }

    public void removeView(View view) {
        if (this.zznY != view) {
            super.removeView(view);
        }
    }

    public void setNativeAd(NativeAd nativeAd) {
        try {
            this.zznZ.zzb((zzd) nativeAd.zzaH());
        } catch (Throwable e) {
            zzb.zzb("Unable to call setNativeAd on delegate", e);
        }
    }

    protected void zza(String str, View view) {
        try {
            this.zznZ.zza(str, zze.zzx(view));
        } catch (Throwable e) {
            zzb.zzb("Unable to call setAssetView on delegate", e);
        }
    }

    protected View zzm(String str) {
        try {
            zzd zzU = this.zznZ.zzU(str);
            if (zzU != null) {
                return (View) zze.zzp(zzU);
            }
        } catch (Throwable e) {
            zzb.zzb("Unable to call getAssetView on delegate", e);
        }
        return null;
    }

    FrameLayout zzn(Context context) {
        return new FrameLayout(context);
    }
}
