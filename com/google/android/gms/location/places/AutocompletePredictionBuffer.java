package com.google.android.gms.location.places;

import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.location.places.internal.zzb;

public class AutocompletePredictionBuffer extends AbstractDataBuffer<AutocompletePrediction> implements Result {
    public AutocompletePredictionBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    public AutocompletePrediction get(int i) {
        return new zzb(this.zzYX, i);
    }

    public Status getStatus() {
        return PlacesStatusCodes.zzhg(this.zzYX.getStatusCode());
    }

    public String toString() {
        return zzw.zzu(this).zzg(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_STATUS, getStatus()).toString();
    }
}
