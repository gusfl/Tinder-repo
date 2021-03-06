package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.stetho.common.Predicate;
import com.facebook.stetho.common.Util;

public final class ViewUtil {

    private static class ViewCompat {
        private static final ViewCompat sInstance;

        @TargetApi(11)
        private static class ViewCompatHoneycomb extends ViewCompat {
            private ViewCompatHoneycomb() {
            }

            public float getAlpha(View view) {
                return view.getAlpha();
            }
        }

        static {
            if (VERSION.SDK_INT >= 11) {
                sInstance = new ViewCompatHoneycomb();
            } else {
                sInstance = new ViewCompat();
            }
        }

        public static ViewCompat getInstance() {
            return sInstance;
        }

        protected ViewCompat() {
        }

        public float getAlpha(View view) {
            return 1.0f;
        }
    }

    private ViewUtil() {
    }

    private static boolean isHittable(View view) {
        if (view.getVisibility() == 0 && ViewCompat.getInstance().getAlpha(view) >= 0.001f) {
            return true;
        }
        return false;
    }

    public static View hitTest(View view, float f, float f2) {
        return hitTest(view, f, f2, null);
    }

    public static View hitTest(View view, float f, float f2, Predicate<View> predicate) {
        View hitTestImpl = hitTestImpl(view, f, f2, predicate, false);
        if (hitTestImpl == null) {
            return hitTestImpl(view, f, f2, predicate, true);
        }
        return hitTestImpl;
    }

    private static View hitTestImpl(View view, float f, float f2, Predicate<View> predicate, boolean z) {
        if (!isHittable(view) || !pointInView(view, f, f2)) {
            return null;
        }
        if (predicate != null && !predicate.apply(view)) {
            return null;
        }
        if (!(view instanceof ViewGroup)) {
            return view;
        }
        view = (ViewGroup) view;
        if (view.getChildCount() > 0) {
            PointF pointF = new PointF();
            for (int childCount = view.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = view.getChildAt(childCount);
                if (isTransformedPointInView(view, childAt, f, f2, pointF)) {
                    childAt = hitTest(childAt, pointF.x, pointF.y, predicate);
                    if (childAt != null) {
                        return childAt;
                    }
                }
            }
        }
        if (!z) {
            view = null;
        }
        return view;
    }

    public static boolean pointInView(View view, float f, float f2) {
        return f >= 0.0f && f < ((float) (view.getRight() - view.getLeft())) && f2 >= 0.0f && f2 < ((float) (view.getBottom() - view.getTop()));
    }

    public static boolean isTransformedPointInView(ViewGroup viewGroup, View view, float f, float f2, PointF pointF) {
        Util.throwIfNull(viewGroup);
        Util.throwIfNull(view);
        float scrollX = (((float) viewGroup.getScrollX()) + f) - ((float) view.getLeft());
        float scrollY = (((float) viewGroup.getScrollY()) + f2) - ((float) view.getTop());
        boolean pointInView = pointInView(view, scrollX, scrollY);
        if (pointInView && pointF != null) {
            pointF.set(scrollX, scrollY);
        }
        return pointInView;
    }

    public static Activity tryGetActivity(View view) {
        if (view == null) {
            return null;
        }
        Context context = view.getContext();
        if (context instanceof Activity) {
            return (Activity) context;
        }
        ViewParent parent = view.getParent();
        return parent instanceof View ? tryGetActivity((View) parent) : null;
    }
}
