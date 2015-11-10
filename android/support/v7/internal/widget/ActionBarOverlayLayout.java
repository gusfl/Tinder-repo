package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.appcompat.C0159R;
import android.support.v7.internal.view.menu.C0174l.C0156a;
import android.support.v7.widget.RecyclerView.SmoothScroller.Action;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window.Callback;
import uk.co.senab.actionbarpulltorefresh.library.C3375e.C3374b;

public class ActionBarOverlayLayout extends ViewGroup implements NestedScrollingParent, DecorContentParent {
    static final int[] ATTRS;
    private static final String TAG = "ActionBarOverlayLayout";
    private final int ACTION_BAR_ANIMATE_DELAY;
    private ActionBarContainer mActionBarBottom;
    private int mActionBarHeight;
    private ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    private boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private final Rect mBaseInnerInsets;
    private final ViewPropertyAnimatorListener mBottomAnimatorListener;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    private ViewPropertyAnimatorCompat mCurrentActionBarBottomAnimator;
    private ViewPropertyAnimatorCompat mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private ScrollerCompat mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private boolean mIgnoreWindowContentOverlay;
    private final Rect mInnerInsets;
    private final Rect mLastBaseContentInsets;
    private final Rect mLastInnerInsets;
    private int mLastSystemUiVisibility;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final ViewPropertyAnimatorListener mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

    public interface ActionBarVisibilityCallback {
        void enableContentAnimations(boolean z);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int i);

        void showForSystem();
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.1 */
    class C02001 extends ViewPropertyAnimatorListenerAdapter {
        C02001() {
        }

        public void onAnimationEnd(View view) {
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
        }

        public void onAnimationCancel(View view) {
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.2 */
    class C02012 extends ViewPropertyAnimatorListenerAdapter {
        C02012() {
        }

        public void onAnimationEnd(View view) {
            ActionBarOverlayLayout.this.mCurrentActionBarBottomAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
        }

        public void onAnimationCancel(View view) {
            ActionBarOverlayLayout.this.mCurrentActionBarBottomAnimator = null;
            ActionBarOverlayLayout.this.mAnimatingForFling = false;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.3 */
    class C02023 implements Runnable {
        C02023() {
        }

        public void run() {
            ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ViewCompat.animate(ActionBarOverlayLayout.this.mActionBarTop).translationY(0.0f).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
            if (ActionBarOverlayLayout.this.mActionBarBottom != null && ActionBarOverlayLayout.this.mActionBarBottom.getVisibility() != 8) {
                ActionBarOverlayLayout.this.mCurrentActionBarBottomAnimator = ViewCompat.animate(ActionBarOverlayLayout.this.mActionBarBottom).translationY(0.0f).setListener(ActionBarOverlayLayout.this.mBottomAnimatorListener);
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActionBarOverlayLayout.4 */
    class C02034 implements Runnable {
        C02034() {
        }

        public void run() {
            ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
            ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ViewCompat.animate(ActionBarOverlayLayout.this.mActionBarTop).translationY((float) (-ActionBarOverlayLayout.this.mActionBarTop.getHeight())).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
            if (ActionBarOverlayLayout.this.mActionBarBottom != null && ActionBarOverlayLayout.this.mActionBarBottom.getVisibility() != 8) {
                ActionBarOverlayLayout.this.mCurrentActionBarBottomAnimator = ViewCompat.animate(ActionBarOverlayLayout.this.mActionBarBottom).translationY((float) ActionBarOverlayLayout.this.mActionBarBottom.getHeight()).setListener(ActionBarOverlayLayout.this.mBottomAnimatorListener);
            }
        }
    }

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    static {
        ATTRS = new int[]{C0159R.attr.actionBarSize, 16842841};
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mBaseInnerInsets = new Rect();
        this.mInnerInsets = new Rect();
        this.mLastInnerInsets = new Rect();
        this.ACTION_BAR_ANIMATE_DELAY = 600;
        this.mTopAnimatorListener = new C02001();
        this.mBottomAnimatorListener = new C02012();
        this.mRemoveActionBarHideOffset = new C02023();
        this.mAddActionBarHideOffset = new C02034();
        init(context);
        this.mParentHelper = new NestedScrollingParentHelper(this);
    }

    private void init(Context context) {
        boolean z = true;
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(ATTRS);
        this.mActionBarHeight = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.mWindowContentOverlay = obtainStyledAttributes.getDrawable(1);
        setWillNotDraw(this.mWindowContentOverlay == null);
        obtainStyledAttributes.recycle();
        if (context.getApplicationInfo().targetSdkVersion >= 19) {
            z = false;
        }
        this.mIgnoreWindowContentOverlay = z;
        this.mFlingEstimator = ScrollerCompat.create(context);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        haltActionBarHideOffsetAnimations();
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarVisibilityCallback) {
        this.mActionBarVisibilityCallback = actionBarVisibilityCallback;
        if (getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            if (this.mLastSystemUiVisibility != 0) {
                onWindowSystemUiVisibilityChanged(this.mLastSystemUiVisibility);
                ViewCompat.requestApplyInsets(this);
            }
        }
    }

    public void setOverlayMode(boolean z) {
        this.mOverlayMode = z;
        boolean z2 = z && getContext().getApplicationInfo().targetSdkVersion < 19;
        this.mIgnoreWindowContentOverlay = z2;
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.mHasNonEmbeddedTabs = z;
    }

    public void setShowingForActionMode(boolean z) {
    }

    protected void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        init(getContext());
        ViewCompat.requestApplyInsets(this);
    }

    public void onWindowSystemUiVisibilityChanged(int i) {
        boolean z;
        boolean z2 = true;
        if (VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        pullChildren();
        int i2 = this.mLastSystemUiVisibility ^ i;
        this.mLastSystemUiVisibility = i;
        boolean z3 = (i & 4) == 0;
        if ((i & AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY) != 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.mActionBarVisibilityCallback != null) {
            ActionBarVisibilityCallback actionBarVisibilityCallback = this.mActionBarVisibilityCallback;
            if (z) {
                z2 = false;
            }
            actionBarVisibilityCallback.enableContentAnimations(z2);
            if (z3 || !z) {
                this.mActionBarVisibilityCallback.showForSystem();
            } else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if ((i2 & AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets(this);
        }
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.mWindowVisibility = i;
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(i);
        }
    }

    private boolean applyInsets(View view, Rect rect, boolean z, boolean z2, boolean z3, boolean z4) {
        boolean z5 = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (z && layoutParams.leftMargin != rect.left) {
            layoutParams.leftMargin = rect.left;
            z5 = true;
        }
        if (z2 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z5 = true;
        }
        if (z4 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z5 = true;
        }
        if (!z3 || layoutParams.bottomMargin == rect.bottom) {
            return z5;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    protected boolean fitSystemWindows(Rect rect) {
        pullChildren();
        boolean applyInsets = (ViewCompat.getWindowSystemUiVisibility(this) & AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY) != 0 ? applyInsets(this.mActionBarTop, rect, true, true, false, true) : applyInsets(this.mActionBarTop, rect, true, true, false, true);
        if (this.mActionBarBottom != null) {
            applyInsets |= applyInsets(this.mActionBarBottom, rect, true, false, true, true);
        }
        this.mBaseInnerInsets.set(rect);
        ViewUtils.computeFitSystemWindows(this, this.mBaseInnerInsets, this.mBaseContentInsets);
        if (!this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
            applyInsets = true;
        }
        if (applyInsets) {
            requestLayout();
        }
        return true;
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected void onMeasure(int i, int i2) {
        int max;
        int max2;
        int i3;
        Object obj;
        int i4;
        pullChildren();
        measureChildWithMargins(this.mActionBarTop, i, 0, i2, 0);
        LayoutParams layoutParams = (LayoutParams) this.mActionBarTop.getLayoutParams();
        int max3 = Math.max(0, (this.mActionBarTop.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        int max4 = Math.max(0, layoutParams.bottomMargin + (this.mActionBarTop.getMeasuredHeight() + layoutParams.topMargin));
        int combineMeasuredStates = ViewUtils.combineMeasuredStates(0, ViewCompat.getMeasuredState(this.mActionBarTop));
        if (this.mActionBarBottom != null) {
            measureChildWithMargins(this.mActionBarBottom, i, 0, i2, 0);
            layoutParams = (LayoutParams) this.mActionBarBottom.getLayoutParams();
            max = Math.max(max3, (this.mActionBarBottom.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
            max2 = Math.max(max4, layoutParams.bottomMargin + (this.mActionBarBottom.getMeasuredHeight() + layoutParams.topMargin));
            max3 = ViewUtils.combineMeasuredStates(combineMeasuredStates, ViewCompat.getMeasuredState(this.mActionBarBottom));
            max4 = max;
            i3 = max2;
        } else {
            i3 = max4;
            max4 = max3;
            max3 = combineMeasuredStates;
        }
        if ((ViewCompat.getWindowSystemUiVisibility(this) & AccessibilityNodeInfoCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY) != 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            i4 = this.mActionBarHeight;
            if (this.mHasNonEmbeddedTabs && this.mActionBarTop.getTabContainer() != null) {
                i4 += this.mActionBarHeight;
            }
        } else {
            i4 = this.mActionBarTop.getVisibility() != 8 ? this.mActionBarTop.getMeasuredHeight() : 0;
        }
        if (!this.mDecorToolbar.isSplit() || this.mActionBarBottom == null) {
            max = 0;
        } else if (obj != null) {
            max = this.mActionBarHeight;
        } else {
            max = this.mActionBarBottom.getMeasuredHeight();
        }
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets.set(this.mBaseInnerInsets);
        Rect rect;
        Rect rect2;
        if (this.mOverlayMode || obj != null) {
            rect = this.mInnerInsets;
            rect.top = i4 + rect.top;
            rect2 = this.mInnerInsets;
            rect2.bottom = max + rect2.bottom;
        } else {
            rect = this.mContentInsets;
            rect.top = i4 + rect.top;
            rect2 = this.mContentInsets;
            rect2.bottom = max + rect2.bottom;
        }
        applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
        if (!this.mLastInnerInsets.equals(this.mInnerInsets)) {
            this.mLastInnerInsets.set(this.mInnerInsets);
            this.mContent.dispatchFitSystemWindows(this.mInnerInsets);
        }
        measureChildWithMargins(this.mContent, i, 0, i2, 0);
        layoutParams = (LayoutParams) this.mContent.getLayoutParams();
        max = Math.max(max4, (this.mContent.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
        i4 = Math.max(i3, layoutParams.bottomMargin + (this.mContent.getMeasuredHeight() + layoutParams.topMargin));
        max2 = ViewUtils.combineMeasuredStates(max3, ViewCompat.getMeasuredState(this.mContent));
        setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(max + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()), i, max2), ViewCompat.resolveSizeAndState(Math.max(i4 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), i2, max2 << 16));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i3 - i) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = layoutParams.leftMargin + paddingLeft;
                if (childAt == this.mActionBarBottom) {
                    paddingRight = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                } else {
                    paddingRight = layoutParams.topMargin + paddingTop;
                }
                childAt.layout(i6, paddingRight, measuredWidth + i6, measuredHeight + paddingRight);
            }
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.mWindowContentOverlay != null && !this.mIgnoreWindowContentOverlay) {
            int bottom = this.mActionBarTop.getVisibility() == 0 ? (int) ((((float) this.mActionBarTop.getBottom()) + ViewCompat.getTranslationY(this.mActionBarTop)) + 0.5f) : 0;
            this.mWindowContentOverlay.setBounds(0, bottom, getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + bottom);
            this.mWindowContentOverlay.draw(canvas);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.mActionBarTop.getVisibility() != 0) {
            return false;
        }
        return this.mHideOnContentScroll;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.mParentHelper.onNestedScrollAccepted(view, view2, i);
        this.mHideOnContentScrollReference = getActionBarHideOffset();
        haltActionBarHideOffsetAnimations();
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStarted();
        }
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        this.mHideOnContentScrollReference += i2;
        setActionBarHideOffset(this.mHideOnContentScrollReference);
    }

    public void onStopNestedScroll(View view) {
        if (this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if (this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                postRemoveActionBarHideOffset();
            } else {
                postAddActionBarHideOffset();
            }
        }
        if (this.mActionBarVisibilityCallback != null) {
            this.mActionBarVisibilityCallback.onContentScrollStopped();
        }
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.mHideOnContentScroll || !z) {
            return false;
        }
        if (shouldHideActionBarOnFling(f, f2)) {
            addActionBarHideOffset();
        } else {
            removeActionBarHideOffset();
        }
        this.mAnimatingForFling = true;
        return true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    void pullChildren() {
        if (this.mContent == null) {
            this.mContent = (ContentFrameLayout) findViewById(C0159R.id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer) findViewById(C0159R.id.action_bar_container);
            this.mDecorToolbar = getDecorToolbar(findViewById(C0159R.id.action_bar));
            this.mActionBarBottom = (ActionBarContainer) findViewById(C0159R.id.split_action_bar);
        }
    }

    private DecorToolbar getDecorToolbar(View view) {
        if (view instanceof DecorToolbar) {
            return (DecorToolbar) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if (z != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = z;
            if (!z) {
                haltActionBarHideOffsetAnimations();
                setActionBarHideOffset(0);
            }
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mHideOnContentScroll;
    }

    public int getActionBarHideOffset() {
        return this.mActionBarTop != null ? -((int) ViewCompat.getTranslationY(this.mActionBarTop)) : 0;
    }

    public void setActionBarHideOffset(int i) {
        haltActionBarHideOffsetAnimations();
        int height = this.mActionBarTop.getHeight();
        int max = Math.max(0, Math.min(i, height));
        ViewCompat.setTranslationY(this.mActionBarTop, (float) (-max));
        if (this.mActionBarBottom != null && this.mActionBarBottom.getVisibility() != 8) {
            ViewCompat.setTranslationY(this.mActionBarBottom, (float) ((int) ((((float) max) / ((float) height)) * ((float) this.mActionBarBottom.getHeight()))));
        }
    }

    private void haltActionBarHideOffsetAnimations() {
        removeCallbacks(this.mRemoveActionBarHideOffset);
        removeCallbacks(this.mAddActionBarHideOffset);
        if (this.mCurrentActionBarTopAnimator != null) {
            this.mCurrentActionBarTopAnimator.cancel();
        }
        if (this.mCurrentActionBarBottomAnimator != null) {
            this.mCurrentActionBarBottomAnimator.cancel();
        }
    }

    private void postRemoveActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mRemoveActionBarHideOffset, 600);
    }

    private void postAddActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        postDelayed(this.mAddActionBarHideOffset, 600);
    }

    private void removeActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    private void addActionBarHideOffset() {
        haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean shouldHideActionBarOnFling(float f, float f2) {
        this.mFlingEstimator.fling(0, 0, 0, (int) f2, 0, 0, Action.UNDEFINED_DURATION, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        if (this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight()) {
            return true;
        }
        return false;
    }

    public void setWindowCallback(Callback callback) {
        pullChildren();
        this.mDecorToolbar.setWindowCallback(callback);
    }

    public void setWindowTitle(CharSequence charSequence) {
        pullChildren();
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public CharSequence getTitle() {
        pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    public void initFeature(int i) {
        pullChildren();
        switch (i) {
            case C3374b.SmoothProgressBar_spb_stroke_width /*2*/:
                this.mDecorToolbar.initProgress();
            case C3374b.SmoothProgressBar_spb_speed /*5*/:
                this.mDecorToolbar.initIndeterminateProgress();
            case C3374b.SmoothProgressBar_spb_reversed /*9*/:
                setOverlayMode(true);
            default:
        }
    }

    public void setUiOptions(int i) {
    }

    public boolean hasIcon() {
        pullChildren();
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        pullChildren();
        return this.mDecorToolbar.hasLogo();
    }

    public void setIcon(int i) {
        pullChildren();
        this.mDecorToolbar.setIcon(i);
    }

    public void setIcon(Drawable drawable) {
        pullChildren();
        this.mDecorToolbar.setIcon(drawable);
    }

    public void setLogo(int i) {
        pullChildren();
        this.mDecorToolbar.setLogo(i);
    }

    public boolean canShowOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public void setMenuPrepared() {
        pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setMenu(Menu menu, C0156a c0156a) {
        pullChildren();
        this.mDecorToolbar.setMenu(menu, c0156a);
    }

    public void saveToolbarHierarchyState(SparseArray<Parcelable> sparseArray) {
        pullChildren();
        this.mDecorToolbar.saveHierarchyState(sparseArray);
    }

    public void restoreToolbarHierarchyState(SparseArray<Parcelable> sparseArray) {
        pullChildren();
        this.mDecorToolbar.restoreHierarchyState(sparseArray);
    }

    public void dismissPopups() {
        pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }
}
