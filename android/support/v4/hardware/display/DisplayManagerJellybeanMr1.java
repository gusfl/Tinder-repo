package android.support.v4.hardware.display;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;
import com.facebook.internal.ServerProtocol;

final class DisplayManagerJellybeanMr1 {
    DisplayManagerJellybeanMr1() {
    }

    public static Object getDisplayManager(Context context) {
        return context.getSystemService(ServerProtocol.DIALOG_PARAM_DISPLAY);
    }

    public static Display getDisplay(Object obj, int i) {
        return ((DisplayManager) obj).getDisplay(i);
    }

    public static Display[] getDisplays(Object obj) {
        return ((DisplayManager) obj).getDisplays();
    }

    public static Display[] getDisplays(Object obj, String str) {
        return ((DisplayManager) obj).getDisplays(str);
    }
}
