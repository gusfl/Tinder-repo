package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.PlaceDetectionApi;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.PlaceLikelihoodBuffer;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzl;
import com.google.android.gms.location.places.zzl.zzd;
import com.google.android.gms.location.places.zzl.zzf;

public class zzi implements PlaceDetectionApi {

    /* renamed from: com.google.android.gms.location.places.internal.zzi.1 */
    class C12041 extends zzd<zzj> {
        final /* synthetic */ PlaceFilter zzaEn;
        final /* synthetic */ zzi zzaEo;

        C12041(zzi com_google_android_gms_location_places_internal_zzi, zzc com_google_android_gms_common_api_Api_zzc, GoogleApiClient googleApiClient, PlaceFilter placeFilter) {
            this.zzaEo = com_google_android_gms_location_places_internal_zzi;
            this.zzaEn = placeFilter;
            super(com_google_android_gms_common_api_Api_zzc, googleApiClient);
        }

        protected void zza(zzj com_google_android_gms_location_places_internal_zzj) throws RemoteException {
            com_google_android_gms_location_places_internal_zzj.zza(new zzl((zzd) this, com_google_android_gms_location_places_internal_zzj.getContext()), this.zzaEn);
        }
    }

    /* renamed from: com.google.android.gms.location.places.internal.zzi.2 */
    class C12052 extends zzf<zzj> {
        final /* synthetic */ zzi zzaEo;
        final /* synthetic */ PlaceReport zzaEp;

        C12052(zzi com_google_android_gms_location_places_internal_zzi, zzc com_google_android_gms_common_api_Api_zzc, GoogleApiClient googleApiClient, PlaceReport placeReport) {
            this.zzaEo = com_google_android_gms_location_places_internal_zzi;
            this.zzaEp = placeReport;
            super(com_google_android_gms_common_api_Api_zzc, googleApiClient);
        }

        protected void zza(zzj com_google_android_gms_location_places_internal_zzj) throws RemoteException {
            com_google_android_gms_location_places_internal_zzj.zza(new zzl((zzf) this), this.zzaEp);
        }
    }

    public PendingResult<PlaceLikelihoodBuffer> getCurrentPlace(GoogleApiClient googleApiClient, PlaceFilter placeFilter) {
        return googleApiClient.zza(new C12041(this, Places.zzaDO, googleApiClient, placeFilter));
    }

    public PendingResult<Status> reportDeviceAtPlace(GoogleApiClient googleApiClient, PlaceReport placeReport) {
        return googleApiClient.zzb(new C12052(this, Places.zzaDO, googleApiClient, placeReport));
    }
}
