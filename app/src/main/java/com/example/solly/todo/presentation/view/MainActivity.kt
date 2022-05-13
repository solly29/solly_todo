package com.example.solly.todo.presentation.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import androidx.core.view.updatePaddingRelative
import androidx.viewpager2.widget.ViewPager2
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityMainBinding
import com.example.solly.todo.presentation.adapter.MainTodoItemAdapter
import com.example.solly.todo.presentation.adapter.SliderTransformer
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()
    private val sliderTransformer = SliderTransformer(3)
    private var isCardChange: Boolean = false
    private var cardPosition = 0

    private val click = { paddingStart: Int, paddingEnd: Int, enable: Boolean ->
        if(binding.viewPagerMainTodo.isUserInputEnabled != enable) {
            val animator = ValueAnimator.ofInt(paddingStart, paddingEnd)
            animator.addUpdateListener {
                if(!enable) {
//                    binding.viewPagerMainTodo.setPageTransformer(null)
                    binding.viewPagerMainTodo.currentItem = cardPosition
                } else {
//                    binding.viewPagerMainTodo.setPageTransformer(SliderTransformer(3))
                }
//                binding.viewPagerMainTodo.setPadding(animator.animatedValue as Int)
                binding.viewPagerMainTodo.updatePadding(animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int)
                binding.viewPagerMainTodo.isUserInputEnabled = enable
                binding.viewPagerMainTodo.setPageTransformer(SliderTransformer(3))
            }
            animator.start()
            if(!enable) {
                binding.buttonMainCardChange.text = "카드 변경"
            } else {
                binding.buttonMainCardChange.text = "카드 선택"
            }
            isCardChange = !isCardChange
        }
    }

    override fun initStartView() {
        binding.viewPagerMainTodo.apply {
            adapter = MainTodoItemAdapter(click)
            offscreenPageLimit = 1
            isUserInputEnabled = false
            binding.viewPagerMainTodo.setPageTransformer(SliderTransformer(3))
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    cardPosition = position
                    Log.e("onPageSelected", position.toString())
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    Log.e("onPageScrollStateChanged", state.toString())
                }
            })
        }
    }

    override fun initDataBinding() {

    }

    override fun initEvent() {
        with(binding) {
            buttonMainCardChange.setOnClickListener {
                if(!isCardChange) {
                    click(0, 50, true)
                } else {
                    click(50, 0, false)
                }
            }
//            viewPagerMainTodo.setOnClickListener {
//                val animator = ValueAnimator.ofInt(viewPagerMainTodo.paddingBottom, 0)
//                animator.addUpdateListener {
//                    viewPagerMainTodo.updatePadding(animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int)
////                    viewPagerMainTodo.isUserInputEnabled = false
//                }
//                animator.start()
//                Log.e("setOnContextClickListener", "click")
//            }

//            viewPagerMainTodo.setOnLongClickListener {
//                val animator = ValueAnimator.ofInt(viewPagerMainTodo.paddingBottom, 50)
//                animator.addUpdateListener {
//                    viewPagerMainTodo.updatePadding(animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int)
////                    viewPagerMainTodo.isUserInputEnabled = false
//                    viewPagerMainTodo.setPageTransformer(SliderTransformer(3))
//                }
//                animator.start()
//                return@setOnLongClickListener true
//            }
        }
    }
}