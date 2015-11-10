package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc.zzb;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzje;
import com.google.android.gms.internal.zzjf;
import java.util.List;

public class GetRecentContextCall {

    public static class Request implements SafeParcelable {
        public static final zzf CREATOR;
        final int mVersionCode;
        public final Account zzPl;
        public final boolean zzPm;
        public final boolean zzPn;
        public final boolean zzPo;
        public final String zzPp;

        public static final class zza {
            private Account zzPq;
            private boolean zzPr;
            private boolean zzPs;
            private boolean zzPt;
            private String zzPu;

            public zza zzL(boolean z) {
                this.zzPs = z;
                return this;
            }

            public zza zzbw(String str) {
                this.zzPu = str;
                return this;
            }

            public Request zzlb() {
                return new Request(this.zzPq, this.zzPr, this.zzPs, this.zzPt, this.zzPu);
            }
        }

        static {
            CREATOR = new zzf();
        }

        public Request() {
            this(null, false, false, false, null);
        }

        Request(int i, Account account, boolean z, boolean z2, boolean z3, String str) {
            this.mVersionCode = i;
            this.zzPl = account;
            this.zzPm = z;
            this.zzPn = z2;
            this.zzPo = z3;
            this.zzPp = str;
        }

        public Request(Account account, boolean z, boolean z2, boolean z3, String str) {
            this(1, account, z, z2, z3, str);
        }

        public int describeContents() {
            zzf com_google_android_gms_appdatasearch_zzf = CREATOR;
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzf com_google_android_gms_appdatasearch_zzf = CREATOR;
            zzf.zza(this, parcel, i);
        }
    }

    public static class Response implements Result, SafeParcelable {
        public static final zzg CREATOR;
        final int mVersionCode;
        public Status zzPv;
        public List<UsageInfo> zzPw;
        public String[] zzPx;

        static {
            CREATOR = new zzg();
        }

        public Response() {
            this.mVersionCode = 1;
        }

        Response(int i, Status status, List<UsageInfo> list, String[] strArr) {
            this.mVersionCode = i;
            this.zzPv = status;
            this.zzPw = list;
            this.zzPx = strArr;
        }

        public int describeContents() {
            zzg com_google_android_gms_appdatasearch_zzg = CREATOR;
            return 0;
        }

        public Status getStatus() {
            return this.zzPv;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzg com_google_android_gms_appdatasearch_zzg = CREATOR;
            zzg.zza(this, parcel, i);
        }
    }

    public static class zza extends com.google.android.gms.common.api.zzc.zza<Response, zzjf> {
        private final Request zzPj;

        /* renamed from: com.google.android.gms.appdatasearch.GetRecentContextCall.zza.1 */
        class C07791 extends zzje<Response> {
            final /* synthetic */ zza zzPk;

            C07791(zza com_google_android_gms_appdatasearch_GetRecentContextCall_zza, zzb com_google_android_gms_common_api_zzc_zzb) {
                this.zzPk = com_google_android_gms_appdatasearch_GetRecentContextCall_zza;
                super(com_google_android_gms_common_api_zzc_zzb);
            }

            public void zza(Response response) {
                this.zzPW.zzn(response);
            }
        }

        public zza(Request request, GoogleApiClient googleApiClient) {
            super(zza.zzOO, googleApiClient);
            this.zzPj = request;
        }

        protected Response zza(Status status) {
            Response response = new Response();
            response.zzPv = status;
            return response;
        }

        protected void zza(zzjf com_google_android_gms_internal_zzjf) throws RemoteException {
            com_google_android_gms_internal_zzjf.zzlg().zza(this.zzPj, new C07791(this, this));
        }

        protected /* synthetic */ Result zzb(Status status) {
            return zza(status);
        }
    }
}
