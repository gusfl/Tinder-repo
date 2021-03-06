package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyGrpcRequest;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public interface zzjy extends IInterface {

    public static abstract class zza extends Binder implements zzjy {

        private static class zza implements zzjy {
            private IBinder zznI;

            zza(IBinder iBinder) {
                this.zznI = iBinder;
            }

            public IBinder asBinder() {
                return this.zznI;
            }

            public void zza(zzjx com_google_android_gms_internal_zzjx, ProxyGrpcRequest proxyGrpcRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzjx != null ? com_google_android_gms_internal_zzjx.asBinder() : null);
                    if (proxyGrpcRequest != null) {
                        obtain.writeInt(1);
                        proxyGrpcRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zznI.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzjx com_google_android_gms_internal_zzjx, ProxyRequest proxyRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.auth.api.internal.IAuthService");
                    obtain.writeStrongBinder(com_google_android_gms_internal_zzjx != null ? com_google_android_gms_internal_zzjx.asBinder() : null);
                    if (proxyRequest != null) {
                        obtain.writeInt(1);
                        proxyRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zznI.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzjy zzaw(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzjy)) ? new zza(iBinder) : (zzjy) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ProxyGrpcRequest proxyGrpcRequest = null;
            zzjx zzav;
            switch (i) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    ProxyRequest proxyRequest;
                    parcel.enforceInterface("com.google.android.gms.auth.api.internal.IAuthService");
                    zzav = com.google.android.gms.internal.zzjx.zza.zzav(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        proxyRequest = (ProxyRequest) ProxyRequest.CREATOR.createFromParcel(parcel);
                    }
                    zza(zzav, proxyRequest);
                    parcel2.writeNoException();
                    return true;
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    parcel.enforceInterface("com.google.android.gms.auth.api.internal.IAuthService");
                    zzav = com.google.android.gms.internal.zzjx.zza.zzav(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        proxyGrpcRequest = (ProxyGrpcRequest) ProxyGrpcRequest.CREATOR.createFromParcel(parcel);
                    }
                    zza(zzav, proxyGrpcRequest);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.auth.api.internal.IAuthService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void zza(zzjx com_google_android_gms_internal_zzjx, ProxyGrpcRequest proxyGrpcRequest) throws RemoteException;

    void zza(zzjx com_google_android_gms_internal_zzjx, ProxyRequest proxyRequest) throws RemoteException;
}
