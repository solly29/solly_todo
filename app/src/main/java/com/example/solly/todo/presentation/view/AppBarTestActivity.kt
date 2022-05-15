package com.example.solly.todo.presentation.view

import android.animation.ValueAnimator
import android.util.Log
import android.util.TypedValue
import android.view.animation.Animation
import android.view.animation.AnimationSet
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityAppbarTestBinding
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.base.BaseViewModel
import kotlin.math.roundToInt

class AppBarTestActivity: BaseActivity<BaseViewModel, ActivityAppbarTestBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_appbar_test
    override val viewModel: BaseViewModel = BaseViewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initEvent() {
        with(binding) {
            buttonToolBarMenu.setOnClickListener {
                val tv = TypedValue()
                theme.resolveAttribute(androidx.appcompat.R.attr.actionBarSize, tv, true)
                val actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
                val animation = ValueAnimator.ofInt(actionBarHeight, actionBarHeight + 600).setDuration(800)
                animation.addUpdateListener {
                    val layoutParams = toolbarMain.layoutParams
                    layoutParams.height = animation.animatedValue as Int
                    toolbarMain.requestLayout()
                }
                animation.start()

//                val layoutParams = toolbarMain.layoutParams
//                layoutParams.height = 200
//                toolbarMain.requestLayout()
            }
        }
    }
}