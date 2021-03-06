package android.support.v4.graphics;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.tinder.views.RangeSeekBar;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class ColorUtils {
    private static final int MIN_ALPHA_SEARCH_MAX_ITERATIONS = 10;
    private static final int MIN_ALPHA_SEARCH_PRECISION = 10;

    private ColorUtils() {
    }

    public static int compositeColors(int i, int i2) {
        int alpha = Color.alpha(i2);
        int alpha2 = Color.alpha(i);
        int compositeAlpha = compositeAlpha(alpha2, alpha);
        return Color.argb(compositeAlpha, compositeComponent(Color.red(i), alpha2, Color.red(i2), alpha, compositeAlpha), compositeComponent(Color.green(i), alpha2, Color.green(i2), alpha, compositeAlpha), compositeComponent(Color.blue(i), alpha2, Color.blue(i2), alpha, compositeAlpha));
    }

    private static int compositeAlpha(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / RangeSeekBar.INVALID_POINTER_ID);
    }

    private static int compositeComponent(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * RangeSeekBar.INVALID_POINTER_ID) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * RangeSeekBar.INVALID_POINTER_ID);
    }

    public static double calculateLuminance(int i) {
        double red = ((double) Color.red(i)) / 255.0d;
        double green = ((double) Color.green(i)) / 255.0d;
        double blue = ((double) Color.blue(i)) / 255.0d;
        return (((red < 0.03928d ? red / 12.92d : Math.pow((red + 0.055d) / 1.055d, 2.4d)) * 0.2126d) + ((green < 0.03928d ? green / 12.92d : Math.pow((green + 0.055d) / 1.055d, 2.4d)) * 0.7152d)) + (0.0722d * (blue < 0.03928d ? blue / 12.92d : Math.pow((blue + 0.055d) / 1.055d, 2.4d)));
    }

    public static double calculateContrast(int i, int i2) {
        if (Color.alpha(i2) != RangeSeekBar.INVALID_POINTER_ID) {
            throw new IllegalArgumentException("background can not be translucent");
        }
        if (Color.alpha(i) < RangeSeekBar.INVALID_POINTER_ID) {
            i = compositeColors(i, i2);
        }
        double calculateLuminance = calculateLuminance(i) + 0.05d;
        double calculateLuminance2 = calculateLuminance(i2) + 0.05d;
        return Math.max(calculateLuminance, calculateLuminance2) / Math.min(calculateLuminance, calculateLuminance2);
    }

    public static int calculateMinimumAlpha(int i, int i2, float f) {
        int i3 = 0;
        int i4 = RangeSeekBar.INVALID_POINTER_ID;
        if (Color.alpha(i2) != RangeSeekBar.INVALID_POINTER_ID) {
            throw new IllegalArgumentException("background can not be translucent");
        } else if (calculateContrast(setAlphaComponent(i, RangeSeekBar.INVALID_POINTER_ID), i2) < ((double) f)) {
            return -1;
        } else {
            int i5 = 0;
            while (i5 <= MIN_ALPHA_SEARCH_PRECISION && i4 - i3 > MIN_ALPHA_SEARCH_PRECISION) {
                int i6 = (i3 + i4) / 2;
                if (calculateContrast(setAlphaComponent(i, i6), i2) >= ((double) f)) {
                    i4 = i6;
                    i6 = i3;
                }
                i5++;
                i3 = i6;
            }
            return i4;
        }
    }

    public static void RGBToHSL(int i, int i2, int i3, float[] fArr) {
        float f = ((float) i) / 255.0f;
        float f2 = ((float) i2) / 255.0f;
        float f3 = ((float) i3) / 255.0f;
        float max = Math.max(f, Math.max(f2, f3));
        float min = Math.min(f, Math.min(f2, f3));
        float f4 = max - min;
        float f5 = (max + min) / 2.0f;
        if (max == min) {
            f2 = 0.0f;
            f = 0.0f;
        } else {
            if (max == f) {
                f = ((f2 - f3) / f4) % 6.0f;
            } else if (max == f2) {
                f = ((f3 - f) / f4) + 2.0f;
            } else {
                f = ((f - f2) / f4) + 4.0f;
            }
            f2 = f4 / (1.0f - Math.abs((2.0f * f5) - 1.0f));
        }
        f = (f * BitmapDescriptorFactory.HUE_YELLOW) % 360.0f;
        if (f < 0.0f) {
            f += 360.0f;
        }
        fArr[0] = constrain(f, 0.0f, 360.0f);
        fArr[1] = constrain(f2, 0.0f, 1.0f);
        fArr[2] = constrain(f5, 0.0f, 1.0f);
    }

    public static void colorToHSL(int i, float[] fArr) {
        RGBToHSL(Color.red(i), Color.green(i), Color.blue(i), fArr);
    }

    public static int HSLToColor(float[] fArr) {
        int round;
        int round2;
        int round3;
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float abs = (1.0f - Math.abs((2.0f * f3) - 1.0f)) * f2;
        float f4 = f3 - (0.5f * abs);
        float abs2 = abs * (1.0f - Math.abs(((f / BitmapDescriptorFactory.HUE_YELLOW) % 2.0f) - 1.0f));
        switch (((int) f) / 60) {
            case C3374b.SmoothProgressBar_spbStyle /*0*/:
                round = Math.round((abs + f4) * 255.0f);
                round2 = Math.round((abs2 + f4) * 255.0f);
                round3 = Math.round(255.0f * f4);
                break;
            case C3374b.SmoothProgressBar_spb_color /*1*/:
                round = Math.round((abs2 + f4) * 255.0f);
                round2 = Math.round((abs + f4) * 255.0f);
                round3 = Math.round(255.0f * f4);
                break;
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                round = Math.round(255.0f * f4);
                round2 = Math.round((abs + f4) * 255.0f);
                round3 = Math.round((abs2 + f4) * 255.0f);
                break;
            case C3374b.SmoothProgressBar_spb_stroke_separator_length /*3*/:
                round = Math.round(255.0f * f4);
                round2 = Math.round((abs2 + f4) * 255.0f);
                round3 = Math.round((abs + f4) * 255.0f);
                break;
            case C3374b.SmoothProgressBar_spb_sections_count /*4*/:
                round = Math.round((abs2 + f4) * 255.0f);
                round2 = Math.round(255.0f * f4);
                round3 = Math.round((abs + f4) * 255.0f);
                break;
            case C3374b.SmoothProgressBar_spb_speed /*5*/:
            case C3374b.SmoothProgressBar_spb_progressiveStart_speed /*6*/:
                round = Math.round((abs + f4) * 255.0f);
                round2 = Math.round(255.0f * f4);
                round3 = Math.round((abs2 + f4) * 255.0f);
                break;
            default:
                round3 = 0;
                round2 = 0;
                round = 0;
                break;
        }
        return Color.rgb(constrain(round, 0, (int) RangeSeekBar.INVALID_POINTER_ID), constrain(round2, 0, (int) RangeSeekBar.INVALID_POINTER_ID), constrain(round3, 0, (int) RangeSeekBar.INVALID_POINTER_ID));
    }

    public static int setAlphaComponent(int i, int i2) {
        if (i2 >= 0 && i2 <= RangeSeekBar.INVALID_POINTER_ID) {
            return (ViewCompat.MEASURED_SIZE_MASK & i) | (i2 << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }

    private static float constrain(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return f > f3 ? f3 : f;
    }

    private static int constrain(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }
}
