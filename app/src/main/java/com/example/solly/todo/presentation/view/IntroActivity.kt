package com.example.solly.todo.presentation.view

import android.animation.Animator
import android.content.Intent
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityIntroBinding
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.base.BaseViewModel
import com.example.solly.todo.presentation.ext.showIntent

class IntroActivity: BaseActivity<BaseViewModel, ActivityIntroBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_intro
    override val viewModel: BaseViewModel = BaseViewModel()

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initEvent() {
        binding.lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                this@IntroActivity.showIntent(MainActivity::class.java) {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}

        })
    }
}