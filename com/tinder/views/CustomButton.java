package com.tinder.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import com.tinder.utils.CustomFont;
import com.tinder.utils.al;

public class CustomButton extends AppCompatButton {
    public CustomButton(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
        al.m9280a((View) this, context, CustomFont.m9193a(context, attributeSet));
    }
}
