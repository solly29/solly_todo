package com.example.solly.todo.presentation.view

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.children
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import androidx.core.view.updatePaddingRelative
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.solly.todo.R
import com.example.solly.todo.databinding.ActivityMainBinding
import com.example.solly.todo.presentation.adapter.MainTodoItemAdapter
import com.example.solly.todo.presentation.adapter.SliderTransformer
import com.example.solly.todo.presentation.adapter.SliderTransformerTest
import com.example.solly.todo.presentation.base.BaseActivity
import com.example.solly.todo.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()
    private val sliderTransformer = SliderTransformer(3)
    private var isCardChange: Boolean = false
    private var cardPosition = 0

    private var isClickMenu = false

    private lateinit var menuAnimation: AnimationDrawable

    val transformer = CompositePageTransformer().apply {
        addTransformer(MarginPageTransformer(40))
        addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
    }

//    private val click = { paddingStart: Int, paddingEnd: Int, enable: Boolean ->
//        if(binding.viewPagerMainTodo.isUserInputEnabled != enable) {
//            val animator = ValueAnimator.ofInt(paddingStart, paddingEnd)
//            animator.addUpdateListener {
//
//                binding.viewPagerMainTodo.setPageTransformer(SliderTransformer(3))
//                binding.viewPagerMainTodo.updatePadding(animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int, animator.animatedValue as Int)
//
////                binding.viewPagerMainTodo.setPageTransformer(transformer)
//            }
//            animator.start()
//            if(!enable) {
//                binding.buttonMainCardChange.text = "카드 변경"
//            } else {
//                binding.buttonMainCardChange.text = "카드 선택"
//            }
//            isCardChange = !isCardChange
//        }
//    }

    override fun initStartView() {
        binding.viewPagerMainTodo.apply {
            adapter = MainTodoItemAdapter()
            offscreenPageLimit = 2
//            isUserInputEnabled = false

            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


//            binding.viewPagerMainTodo.setPageTransformer(transformer)
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
//            buttonMainCardChange.setOnClickListener {
//                if(!isCardChange) {
//                    click(0, 50, true)
//                } else {
//                    click(50, 0, false)
//                }
//            }

            linearCompleteCount.setOnClickListener {
                Toast.makeText(this@MainActivity, "clicke", Toast.LENGTH_SHORT).show()
            }

            buttonAppbar.setOnClickListener {
                if(!isClickMenu) {
                    buttonAppbar.setImageResource(R.drawable.ic_baseline_close_24)
                    motionLayoutAppbar.transitionToEnd()
                } else {
                    buttonAppbar.setImageResource(R.drawable.ic_baseline_menu_24)
                    motionLayoutAppbar.transitionToStart()
                }
                isClickMenu = !isClickMenu
            }

            floatingCloseButton.setOnClickListener {
                if(!isClickMenu) {
                    buttonAppbar.setImageResource(R.drawable.ic_baseline_close_24)
                    motionLayoutAppbar.transitionToEnd()
                } else {
                    buttonAppbar.setImageResource(R.drawable.ic_baseline_menu_24)
                    motionLayoutAppbar.transitionToStart()
                }
                isClickMenu = !isClickMenu
            }
        }
    }
}