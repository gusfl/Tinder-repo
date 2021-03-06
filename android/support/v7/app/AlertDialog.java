package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertController.AlertParams;
import android.support.v7.appcompat.C0159R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AlertDialog extends AppCompatDialog implements DialogInterface {
    static final int LAYOUT_HINT_NONE = 0;
    static final int LAYOUT_HINT_SIDE = 1;
    private AlertController mAlert;

    public static class Builder {
        private final AlertParams f57P;
        private int mTheme;

        public Builder(Context context) {
            this(context, AlertDialog.resolveDialogTheme(context, AlertDialog.LAYOUT_HINT_NONE));
        }

        public Builder(Context context, int i) {
            this.f57P = new AlertParams(new ContextThemeWrapper(context, AlertDialog.resolveDialogTheme(context, i)));
            this.mTheme = i;
        }

        public Context getContext() {
            return this.f57P.mContext;
        }

        public Builder setTitle(int i) {
            this.f57P.mTitle = this.f57P.mContext.getText(i);
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.f57P.mTitle = charSequence;
            return this;
        }

        public Builder setCustomTitle(View view) {
            this.f57P.mCustomTitleView = view;
            return this;
        }

        public Builder setMessage(int i) {
            this.f57P.mMessage = this.f57P.mContext.getText(i);
            return this;
        }

        public Builder setMessage(CharSequence charSequence) {
            this.f57P.mMessage = charSequence;
            return this;
        }

        public Builder setIcon(int i) {
            this.f57P.mIconId = i;
            return this;
        }

        public Builder setIcon(Drawable drawable) {
            this.f57P.mIcon = drawable;
            return this;
        }

        public Builder setIconAttribute(int i) {
            TypedValue typedValue = new TypedValue();
            this.f57P.mContext.getTheme().resolveAttribute(i, typedValue, true);
            this.f57P.mIconId = typedValue.resourceId;
            return this;
        }

        public Builder setPositiveButton(int i, OnClickListener onClickListener) {
            this.f57P.mPositiveButtonText = this.f57P.mContext.getText(i);
            this.f57P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setPositiveButton(CharSequence charSequence, OnClickListener onClickListener) {
            this.f57P.mPositiveButtonText = charSequence;
            this.f57P.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(int i, OnClickListener onClickListener) {
            this.f57P.mNegativeButtonText = this.f57P.mContext.getText(i);
            this.f57P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNegativeButton(CharSequence charSequence, OnClickListener onClickListener) {
            this.f57P.mNegativeButtonText = charSequence;
            this.f57P.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(int i, OnClickListener onClickListener) {
            this.f57P.mNeutralButtonText = this.f57P.mContext.getText(i);
            this.f57P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setNeutralButton(CharSequence charSequence, OnClickListener onClickListener) {
            this.f57P.mNeutralButtonText = charSequence;
            this.f57P.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder setCancelable(boolean z) {
            this.f57P.mCancelable = z;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.f57P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.f57P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.f57P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setItems(int i, OnClickListener onClickListener) {
            this.f57P.mItems = this.f57P.mContext.getResources().getTextArray(i);
            this.f57P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setItems(CharSequence[] charSequenceArr, OnClickListener onClickListener) {
            this.f57P.mItems = charSequenceArr;
            this.f57P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setAdapter(ListAdapter listAdapter, OnClickListener onClickListener) {
            this.f57P.mAdapter = listAdapter;
            this.f57P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setCursor(Cursor cursor, OnClickListener onClickListener, String str) {
            this.f57P.mCursor = cursor;
            this.f57P.mLabelColumn = str;
            this.f57P.mOnClickListener = onClickListener;
            return this;
        }

        public Builder setMultiChoiceItems(int i, boolean[] zArr, OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f57P.mItems = this.f57P.mContext.getResources().getTextArray(i);
            this.f57P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f57P.mCheckedItems = zArr;
            this.f57P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(CharSequence[] charSequenceArr, boolean[] zArr, OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f57P.mItems = charSequenceArr;
            this.f57P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f57P.mCheckedItems = zArr;
            this.f57P.mIsMultiChoice = true;
            return this;
        }

        public Builder setMultiChoiceItems(Cursor cursor, String str, String str2, OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f57P.mCursor = cursor;
            this.f57P.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f57P.mIsCheckedColumn = str;
            this.f57P.mLabelColumn = str2;
            this.f57P.mIsMultiChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(int i, int i2, OnClickListener onClickListener) {
            this.f57P.mItems = this.f57P.mContext.getResources().getTextArray(i);
            this.f57P.mOnClickListener = onClickListener;
            this.f57P.mCheckedItem = i2;
            this.f57P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(Cursor cursor, int i, String str, OnClickListener onClickListener) {
            this.f57P.mCursor = cursor;
            this.f57P.mOnClickListener = onClickListener;
            this.f57P.mCheckedItem = i;
            this.f57P.mLabelColumn = str;
            this.f57P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(CharSequence[] charSequenceArr, int i, OnClickListener onClickListener) {
            this.f57P.mItems = charSequenceArr;
            this.f57P.mOnClickListener = onClickListener;
            this.f57P.mCheckedItem = i;
            this.f57P.mIsSingleChoice = true;
            return this;
        }

        public Builder setSingleChoiceItems(ListAdapter listAdapter, int i, OnClickListener onClickListener) {
            this.f57P.mAdapter = listAdapter;
            this.f57P.mOnClickListener = onClickListener;
            this.f57P.mCheckedItem = i;
            this.f57P.mIsSingleChoice = true;
            return this;
        }

        public Builder setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
            this.f57P.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder setView(int i) {
            this.f57P.mView = null;
            this.f57P.mViewLayoutResId = i;
            this.f57P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view) {
            this.f57P.mView = view;
            this.f57P.mViewLayoutResId = AlertDialog.LAYOUT_HINT_NONE;
            this.f57P.mViewSpacingSpecified = false;
            return this;
        }

        public Builder setView(View view, int i, int i2, int i3, int i4) {
            this.f57P.mView = view;
            this.f57P.mViewLayoutResId = AlertDialog.LAYOUT_HINT_NONE;
            this.f57P.mViewSpacingSpecified = true;
            this.f57P.mViewSpacingLeft = i;
            this.f57P.mViewSpacingTop = i2;
            this.f57P.mViewSpacingRight = i3;
            this.f57P.mViewSpacingBottom = i4;
            return this;
        }

        public Builder setInverseBackgroundForced(boolean z) {
            this.f57P.mForceInverseBackground = z;
            return this;
        }

        public Builder setRecycleOnMeasureEnabled(boolean z) {
            this.f57P.mRecycleOnMeasure = z;
            return this;
        }

        public AlertDialog create() {
            AlertDialog alertDialog = new AlertDialog(this.f57P.mContext, this.mTheme, false);
            this.f57P.apply(alertDialog.mAlert);
            alertDialog.setCancelable(this.f57P.mCancelable);
            if (this.f57P.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f57P.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f57P.mOnDismissListener);
            if (this.f57P.mOnKeyListener != null) {
                alertDialog.setOnKeyListener(this.f57P.mOnKeyListener);
            }
            return alertDialog;
        }

        public AlertDialog show() {
            AlertDialog create = create();
            create.show();
            return create;
        }
    }

    protected AlertDialog(Context context) {
        this(context, resolveDialogTheme(context, LAYOUT_HINT_NONE), true);
    }

    protected AlertDialog(Context context, int i) {
        this(context, i, true);
    }

    AlertDialog(Context context, int i, boolean z) {
        super(context, resolveDialogTheme(context, i));
        this.mAlert = new AlertController(getContext(), this, getWindow());
    }

    protected AlertDialog(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, resolveDialogTheme(context, LAYOUT_HINT_NONE));
        setCancelable(z);
        setOnCancelListener(onCancelListener);
        this.mAlert = new AlertController(context, this, getWindow());
    }

    static int resolveDialogTheme(Context context, int i) {
        if (i >= ViewCompat.MEASURED_STATE_TOO_SMALL) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(C0159R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public Button getButton(int i) {
        return this.mAlert.getButton(i);
    }

    public ListView getListView() {
        return this.mAlert.getListView();
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.setTitle(charSequence);
    }

    public void setCustomTitle(View view) {
        this.mAlert.setCustomTitle(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlert.setMessage(charSequence);
    }

    public void setView(View view) {
        this.mAlert.setView(view);
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mAlert.setView(view, i, i2, i3, i4);
    }

    void setButtonPanelLayoutHint(int i) {
        this.mAlert.setButtonPanelLayoutHint(i);
    }

    public void setButton(int i, CharSequence charSequence, Message message) {
        this.mAlert.setButton(i, charSequence, null, message);
    }

    public void setButton(int i, CharSequence charSequence, OnClickListener onClickListener) {
        this.mAlert.setButton(i, charSequence, onClickListener, null);
    }

    public void setIcon(int i) {
        this.mAlert.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        this.mAlert.setIcon(drawable);
    }

    public void setIconAttribute(int i) {
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(i, typedValue, true);
        this.mAlert.setIcon(typedValue.resourceId);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.installContent();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mAlert.onKeyDown(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mAlert.onKeyUp(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }
}
