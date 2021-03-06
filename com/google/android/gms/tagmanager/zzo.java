package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.facebook.stetho.BuildConfig;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tagmanager.ContainerHolder.ContainerAvailableListener;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

class zzo implements ContainerHolder {
    private Status zzQA;
    private final Looper zzYV;
    private Container zzaPa;
    private Container zzaPb;
    private zzb zzaPc;
    private zza zzaPd;
    private TagManager zzaPe;
    private boolean zzahz;

    public interface zza {
        void zzey(String str);

        String zzzE();

        void zzzG();
    }

    private class zzb extends Handler {
        private final ContainerAvailableListener zzaPf;
        final /* synthetic */ zzo zzaPg;

        public zzb(zzo com_google_android_gms_tagmanager_zzo, ContainerAvailableListener containerAvailableListener, Looper looper) {
            this.zzaPg = com_google_android_gms_tagmanager_zzo;
            super(looper);
            this.zzaPf = containerAvailableListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case C3374b.SmoothProgressBar_spb_color /*1*/:
                    zzeA((String) message.obj);
                default:
                    zzbg.m1004e("Don't know how to handle this message.");
            }
        }

        protected void zzeA(String str) {
            this.zzaPf.onContainerAvailable(this.zzaPg, str);
        }

        public void zzez(String str) {
            sendMessage(obtainMessage(1, str));
        }
    }

    public zzo(Status status) {
        this.zzQA = status;
        this.zzYV = null;
    }

    public zzo(TagManager tagManager, Looper looper, Container container, zza com_google_android_gms_tagmanager_zzo_zza) {
        this.zzaPe = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzYV = looper;
        this.zzaPa = container;
        this.zzaPd = com_google_android_gms_tagmanager_zzo_zza;
        this.zzQA = Status.zzaaD;
        tagManager.zza(this);
    }

    private void zzzF() {
        if (this.zzaPc != null) {
            this.zzaPc.zzez(this.zzaPb.zzzC());
        }
    }

    public synchronized Container getContainer() {
        Container container = null;
        synchronized (this) {
            if (this.zzahz) {
                zzbg.m1004e("ContainerHolder is released.");
            } else {
                if (this.zzaPb != null) {
                    this.zzaPa = this.zzaPb;
                    this.zzaPb = null;
                }
                container = this.zzaPa;
            }
        }
        return container;
    }

    String getContainerId() {
        if (!this.zzahz) {
            return this.zzaPa.getContainerId();
        }
        zzbg.m1004e("getContainerId called on a released ContainerHolder.");
        return BuildConfig.FLAVOR;
    }

    public Status getStatus() {
        return this.zzQA;
    }

    public synchronized void refresh() {
        if (this.zzahz) {
            zzbg.m1004e("Refreshing a released ContainerHolder.");
        } else {
            this.zzaPd.zzzG();
        }
    }

    public synchronized void release() {
        if (this.zzahz) {
            zzbg.m1004e("Releasing a released ContainerHolder.");
        } else {
            this.zzahz = true;
            this.zzaPe.zzb(this);
            this.zzaPa.release();
            this.zzaPa = null;
            this.zzaPb = null;
            this.zzaPd = null;
            this.zzaPc = null;
        }
    }

    public synchronized void setContainerAvailableListener(ContainerAvailableListener containerAvailableListener) {
        if (this.zzahz) {
            zzbg.m1004e("ContainerHolder is released.");
        } else if (containerAvailableListener == null) {
            this.zzaPc = null;
        } else {
            this.zzaPc = new zzb(this, containerAvailableListener, this.zzYV);
            if (this.zzaPb != null) {
                zzzF();
            }
        }
    }

    public synchronized void zza(Container container) {
        if (!this.zzahz) {
            if (container == null) {
                zzbg.m1004e("Unexpected null container.");
            } else {
                this.zzaPb = container;
                zzzF();
            }
        }
    }

    public synchronized void zzew(String str) {
        if (!this.zzahz) {
            this.zzaPa.zzew(str);
        }
    }

    void zzey(String str) {
        if (this.zzahz) {
            zzbg.m1004e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        } else {
            this.zzaPd.zzey(str);
        }
    }

    String zzzE() {
        if (!this.zzahz) {
            return this.zzaPd.zzzE();
        }
        zzbg.m1004e("setCtfeUrlPathAndQuery called on a released ContainerHolder.");
        return BuildConfig.FLAVOR;
    }
}
