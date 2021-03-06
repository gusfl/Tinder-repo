package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class zzg implements Creator<RecordConsentRequest> {
    static void zza(RecordConsentRequest recordConsentRequest, Parcel parcel, int i) {
        int zzak = zzb.zzak(parcel);
        zzb.zzc(parcel, 1, recordConsentRequest.mVersionCode);
        zzb.zza(parcel, 2, recordConsentRequest.getAccount(), i, false);
        zzb.zza(parcel, 3, recordConsentRequest.zzzs(), i, false);
        zzb.zza(parcel, 4, recordConsentRequest.zzlG(), false);
        zzb.zzH(parcel, zzak);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzgl(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzjb(i);
    }

    public RecordConsentRequest zzgl(Parcel parcel) {
        String str = null;
        int zzaj = zza.zzaj(parcel);
        int i = 0;
        Scope[] scopeArr = null;
        Account account = null;
        while (parcel.dataPosition() < zzaj) {
            Scope[] scopeArr2;
            Account account2;
            int zzg;
            String str2;
            int zzai = zza.zzai(parcel);
            String str3;
            switch (zza.zzbH(zzai)) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    str3 = str;
                    scopeArr2 = scopeArr;
                    account2 = account;
                    zzg = zza.zzg(parcel, zzai);
                    str2 = str3;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    zzg = i;
                    Scope[] scopeArr3 = scopeArr;
                    account2 = (Account) zza.zza(parcel, zzai, Account.CREATOR);
                    str2 = str;
                    scopeArr2 = scopeArr3;
                    break;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    account2 = account;
                    zzg = i;
                    str3 = str;
                    scopeArr2 = (Scope[]) zza.zzb(parcel, zzai, Scope.CREATOR);
                    str2 = str3;
                    break;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    str2 = zza.zzo(parcel, zzai);
                    scopeArr2 = scopeArr;
                    account2 = account;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzai);
                    str2 = str;
                    scopeArr2 = scopeArr;
                    account2 = account;
                    zzg = i;
                    break;
            }
            i = zzg;
            account = account2;
            scopeArr = scopeArr2;
            str = str2;
        }
        if (parcel.dataPosition() == zzaj) {
            return new RecordConsentRequest(i, account, scopeArr, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzaj, parcel);
    }

    public RecordConsentRequest[] zzjb(int i) {
        return new RecordConsentRequest[i];
    }
}
