package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.viewpagerindicator.C3169d.C3168f;
import org.apache.http.util.LangUtils;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public interface zzc extends IInterface {

    public static abstract class zza extends Binder implements zzc {

        private static class zza implements zzc {
            private IBinder zznI;

            zza(IBinder iBinder) {
                this.zznI = iBinder;
            }

            public IBinder asBinder() {
                return this.zznI;
            }

            public Bundle getArguments() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getRetainInstance() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTargetRequestCode() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean getUserVisibleHint() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd getView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd zzbk = com.google.android.gms.dynamic.zzd.zza.zzbk(obtain2.readStrongBinder());
                    return zzbk;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isAdded() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isDetached() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isHidden() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isInLayout() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isRemoving() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isResumed() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isVisible() throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setHasOptionsMenu(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zznI.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMenuVisibility(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zznI.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRetainInstance(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zznI.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setUserVisibleHint(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.zznI.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startActivity(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zznI.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startActivityForResult(Intent intent, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.zznI.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzn(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zznI.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzo(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zznI.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd zzro() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd zzbk = com.google.android.gms.dynamic.zzd.zza.zzbk(obtain2.readStrongBinder());
                    return zzbk;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzc zzrp() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    zzc zzbj = zza.zzbj(obtain2.readStrongBinder());
                    return zzbj;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd zzrq() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    zzd zzbk = com.google.android.gms.dynamic.zzd.zza.zzbk(obtain2.readStrongBinder());
                    return zzbk;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzc zzrr() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamic.IFragmentWrapper");
                    this.zznI.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    zzc zzbj = zza.zzbj(obtain2.readStrongBinder());
                    return zzbj;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static zzc zzbj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzc)) ? new zza(iBinder) : (zzc) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Intent intent = null;
            boolean z = false;
            zzd zzro;
            IBinder asBinder;
            int id;
            zzc zzrp;
            boolean retainInstance;
            int i3;
            switch (i) {
                case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzro = zzro();
                    parcel2.writeNoException();
                    if (zzro != null) {
                        asBinder = zzro.asBinder();
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    Bundle arguments = getArguments();
                    parcel2.writeNoException();
                    if (arguments != null) {
                        parcel2.writeInt(1);
                        arguments.writeToParcel(parcel2, 1);
                        return true;
                    }
                    parcel2.writeInt(0);
                    return true;
                case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    id = getId();
                    parcel2.writeNoException();
                    parcel2.writeInt(id);
                    return true;
                case C3374b.SmoothProgressBar_spb_speed /*5*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzrp = zzrp();
                    parcel2.writeNoException();
                    if (zzrp != null) {
                        asBinder = zzrp.asBinder();
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzro = zzrq();
                    parcel2.writeNoException();
                    if (zzro != null) {
                        asBinder = zzro.asBinder();
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case C3374b.SmoothProgressBar_spb_progressiveStop_speed /*7*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = getRetainInstance();
                    parcel2.writeNoException();
                    parcel2.writeInt(retainInstance ? 1 : 0);
                    return true;
                case C3374b.SmoothProgressBar_spb_interpolator /*8*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    String tag = getTag();
                    parcel2.writeNoException();
                    parcel2.writeString(tag);
                    return true;
                case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzrp = zzrr();
                    parcel2.writeNoException();
                    if (zzrp != null) {
                        asBinder = zzrp.asBinder();
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case C3374b.SmoothProgressBar_spb_mirror_mode /*10*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    id = getTargetRequestCode();
                    parcel2.writeNoException();
                    parcel2.writeInt(id);
                    return true;
                case C3374b.SmoothProgressBar_spb_colors /*11*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = getUserVisibleHint();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3374b.SmoothProgressBar_spb_progressiveStart_activated /*12*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzro = getView();
                    parcel2.writeNoException();
                    if (zzro != null) {
                        asBinder = zzro.asBinder();
                    }
                    parcel2.writeStrongBinder(asBinder);
                    return true;
                case C3374b.SmoothProgressBar_spb_background /*13*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isAdded();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3374b.SmoothProgressBar_spb_generate_background_with_colors /*14*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isDetached();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3168f.Toolbar_titleMarginBottom /*15*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isHidden();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3168f.Toolbar_maxButtonHeight /*16*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isInLayout();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case LangUtils.HASH_SEED /*17*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isRemoving();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3168f.Toolbar_collapseContentDescription /*18*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isResumed();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3168f.Toolbar_navigationIcon /*19*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    retainInstance = isVisible();
                    parcel2.writeNoException();
                    if (retainInstance) {
                        i3 = 1;
                    }
                    parcel2.writeInt(i3);
                    return true;
                case C3168f.Toolbar_navigationContentDescription /*20*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzn(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionBarWidgetTheme /*21*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setHasOptionsMenu(z);
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionBarSize /*22*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setMenuVisibility(z);
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionBarDivider /*23*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setRetainInstance(z);
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionBarItemBackground /*24*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        z = true;
                    }
                    setUserVisibleHint(z);
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionMenuTextAppearance /*25*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    startActivity(intent);
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionMenuTextColor /*26*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    if (parcel.readInt() != 0) {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    startActivityForResult(intent, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case C3168f.Theme_actionModeStyle /*27*/:
                    parcel.enforceInterface("com.google.android.gms.dynamic.IFragmentWrapper");
                    zzo(com.google.android.gms.dynamic.zzd.zza.zzbk(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.dynamic.IFragmentWrapper");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    Bundle getArguments() throws RemoteException;

    int getId() throws RemoteException;

    boolean getRetainInstance() throws RemoteException;

    String getTag() throws RemoteException;

    int getTargetRequestCode() throws RemoteException;

    boolean getUserVisibleHint() throws RemoteException;

    zzd getView() throws RemoteException;

    boolean isAdded() throws RemoteException;

    boolean isDetached() throws RemoteException;

    boolean isHidden() throws RemoteException;

    boolean isInLayout() throws RemoteException;

    boolean isRemoving() throws RemoteException;

    boolean isResumed() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void setHasOptionsMenu(boolean z) throws RemoteException;

    void setMenuVisibility(boolean z) throws RemoteException;

    void setRetainInstance(boolean z) throws RemoteException;

    void setUserVisibleHint(boolean z) throws RemoteException;

    void startActivity(Intent intent) throws RemoteException;

    void startActivityForResult(Intent intent, int i) throws RemoteException;

    void zzn(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    void zzo(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzd zzro() throws RemoteException;

    zzc zzrp() throws RemoteException;

    zzd zzrq() throws RemoteException;

    zzc zzrr() throws RemoteException;
}
