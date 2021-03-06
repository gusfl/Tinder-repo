package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.v7.appcompat.C0159R;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class AppCompatCheckBox extends CheckBox {
    private static final int[] TINT_ATTRS;
    private Drawable mButtonDrawable;
    private TintManager mTintManager;

    static {
        TINT_ATTRS = new int[]{16843015};
    }

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0159R.attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (TintManager.SHOULD_BE_USED) {
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, TINT_ATTRS, i, 0);
            setButtonDrawable(obtainStyledAttributes.getDrawable(0));
            obtainStyledAttributes.recycle();
            this.mTintManager = obtainStyledAttributes.getTintManager();
        }
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        this.mButtonDrawable = drawable;
    }

    public void setButtonDrawable(@DrawableRes int i) {
        if (this.mTintManager != null) {
            setButtonDrawable(this.mTintManager.getDrawable(i));
        } else {
            super.setButtonDrawable(i);
        }
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        if (VERSION.SDK_INT >= 17 || this.mButtonDrawable == null) {
            return compoundPaddingLeft;
        }
        return compoundPaddingLeft + this.mButtonDrawable.getIntrinsicWidth();
    }
}
