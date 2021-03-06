package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ChildHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket;
    final Callback mCallback;
    final List<View> mHiddenViews;

    static class Bucket {
        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = Long.MIN_VALUE;
        long mData;
        Bucket next;

        Bucket() {
            this.mData = 0;
        }

        void set(int i) {
            if (i >= BITS_PER_WORD) {
                ensureNext();
                this.next.set(i - 64);
                return;
            }
            this.mData |= 1 << i;
        }

        private void ensureNext() {
            if (this.next == null) {
                this.next = new Bucket();
            }
        }

        void clear(int i) {
            if (i < BITS_PER_WORD) {
                this.mData &= (1 << i) ^ -1;
            } else if (this.next != null) {
                this.next.clear(i - 64);
            }
        }

        boolean get(int i) {
            if (i < BITS_PER_WORD) {
                return (this.mData & (1 << i)) != 0 ? true : ChildHelper.DEBUG;
            } else {
                ensureNext();
                return this.next.get(i - 64);
            }
        }

        void reset() {
            this.mData = 0;
            if (this.next != null) {
                this.next.reset();
            }
        }

        void insert(int i, boolean z) {
            if (i >= BITS_PER_WORD) {
                ensureNext();
                this.next.insert(i - 64, z);
                return;
            }
            boolean z2 = (this.mData & LAST_BIT) != 0 ? true : ChildHelper.DEBUG;
            long j = (1 << i) - 1;
            this.mData = (((j ^ -1) & this.mData) << 1) | (this.mData & j);
            if (z) {
                set(i);
            } else {
                clear(i);
            }
            if (z2 || this.next != null) {
                ensureNext();
                this.next.insert(0, z2);
            }
        }

        boolean remove(int i) {
            if (i >= BITS_PER_WORD) {
                ensureNext();
                return this.next.remove(i - 64);
            }
            long j = 1 << i;
            boolean z = (this.mData & j) != 0 ? true : ChildHelper.DEBUG;
            this.mData &= j ^ -1;
            j--;
            this.mData = Long.rotateRight((j ^ -1) & this.mData, 1) | (this.mData & j);
            if (this.next == null) {
                return z;
            }
            if (this.next.get(0)) {
                set(63);
            }
            this.next.remove(0);
            return z;
        }

        int countOnesBefore(int i) {
            if (this.next == null) {
                if (i >= BITS_PER_WORD) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(this.mData & ((1 << i) - 1));
            } else if (i < BITS_PER_WORD) {
                return Long.bitCount(this.mData & ((1 << i) - 1));
            } else {
                return this.next.countOnesBefore(i - 64) + Long.bitCount(this.mData);
            }
        }

        public String toString() {
            return this.next == null ? Long.toBinaryString(this.mData) : this.next.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }

    interface Callback {
        void addView(View view, int i);

        void attachViewToParent(View view, int i, LayoutParams layoutParams);

        void detachViewFromParent(int i);

        View getChildAt(int i);

        int getChildCount();

        ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void removeAllViews();

        void removeViewAt(int i);
    }

    ChildHelper(Callback callback) {
        this.mCallback = callback;
        this.mBucket = new Bucket();
        this.mHiddenViews = new ArrayList();
    }

    void addView(View view, boolean z) {
        addView(view, -1, z);
    }

    void addView(View view, int i, boolean z) {
        int childCount;
        if (i < 0) {
            childCount = this.mCallback.getChildCount();
        } else {
            childCount = getOffset(i);
        }
        this.mBucket.insert(childCount, z);
        if (z) {
            this.mHiddenViews.add(view);
        }
        this.mCallback.addView(view, childCount);
    }

    private int getOffset(int i) {
        if (i < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int i2 = i;
        while (i2 < childCount) {
            int countOnesBefore = i - (i2 - this.mBucket.countOnesBefore(i2));
            if (countOnesBefore == 0) {
                while (this.mBucket.get(i2)) {
                    i2++;
                }
                return i2;
            }
            i2 += countOnesBefore;
        }
        return -1;
    }

    void removeView(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild >= 0) {
            if (this.mBucket.remove(indexOfChild)) {
                this.mHiddenViews.remove(view);
            }
            this.mCallback.removeViewAt(indexOfChild);
        }
    }

    void removeViewAt(int i) {
        int offset = getOffset(i);
        View childAt = this.mCallback.getChildAt(offset);
        if (childAt != null) {
            if (this.mBucket.remove(offset)) {
                this.mHiddenViews.remove(childAt);
            }
            this.mCallback.removeViewAt(offset);
        }
    }

    View getChildAt(int i) {
        return this.mCallback.getChildAt(getOffset(i));
    }

    void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        this.mHiddenViews.clear();
        this.mCallback.removeAllViews();
    }

    View findHiddenNonRemovedView(int i, int i2) {
        int size = this.mHiddenViews.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) this.mHiddenViews.get(i3);
            ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == i && !childViewHolder.isInvalid() && (i2 == -1 || childViewHolder.getItemViewType() == i2)) {
                return view;
            }
        }
        return null;
    }

    void attachViewToParent(View view, int i, LayoutParams layoutParams, boolean z) {
        int childCount;
        if (i < 0) {
            childCount = this.mCallback.getChildCount();
        } else {
            childCount = getOffset(i);
        }
        this.mBucket.insert(childCount, z);
        if (z) {
            this.mHiddenViews.add(view);
        }
        this.mCallback.attachViewToParent(view, childCount, layoutParams);
    }

    int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    View getUnfilteredChildAt(int i) {
        return this.mCallback.getChildAt(i);
    }

    void detachViewFromParent(int i) {
        int offset = getOffset(i);
        this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }

    int indexOfChild(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1 || this.mBucket.get(indexOfChild)) {
            return -1;
        }
        return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
    }

    boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    void hide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.mBucket.set(indexOfChild);
        this.mHiddenViews.add(view);
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    boolean removeViewIfHidden(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1) {
            return this.mHiddenViews.remove(view) ? true : true;
        } else {
            if (!this.mBucket.get(indexOfChild)) {
                return DEBUG;
            }
            this.mBucket.remove(indexOfChild);
            if (this.mHiddenViews.remove(view)) {
                this.mCallback.removeViewAt(indexOfChild);
            } else {
                this.mCallback.removeViewAt(indexOfChild);
            }
            return true;
        }
    }
}
