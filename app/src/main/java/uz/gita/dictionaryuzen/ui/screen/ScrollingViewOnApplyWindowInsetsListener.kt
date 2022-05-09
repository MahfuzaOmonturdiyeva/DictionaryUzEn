package uz.gita.dictionaryuzen.ui.screen

import android.graphics.Rect
import android.view.View
import android.view.WindowInsets
import androidx.recyclerview.widget.RecyclerView
import me.zhanghai.android.fastscroll.FastScroller


class ScrollingViewOnApplyWindowInsetsListener(
    rvContainerChapterWithLessons: RecyclerView,
    fastScroller: Any
) : View.OnApplyWindowInsetsListener {

    private val mPadding: Rect = Rect()

    private var mFastScroller: FastScroller? = null

    fun ScrollingViewOnApplyWindowInsetsListener(
        view: View?,
        fastScroller: FastScroller?
    ) {
        if (view != null) {
            mPadding[view.paddingLeft, view.paddingTop, view.paddingRight] = view.paddingBottom
        }
        mFastScroller = fastScroller
    }


    override fun onApplyWindowInsets(view: View, insets: WindowInsets): WindowInsets {
        view.setPadding(
            mPadding.left + insets.systemWindowInsetLeft, mPadding.top,
            mPadding.right + insets.systemWindowInsetRight,
            mPadding.bottom + insets.systemWindowInsetBottom
        )
        mFastScroller?.let {
            it.setPadding(
                insets.systemWindowInsetLeft, 0,
                insets.systemWindowInsetRight, insets.systemWindowInsetBottom
            )
        }
        return insets
    }
}

