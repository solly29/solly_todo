package com.example.solly.todo.presentation.adapter

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class SliderTransformerTest(private val offscreenPageLimit: Int) : ViewPager2.PageTransformer {

    companion object {
        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_FACTOR = 1.2f

        private const val SCALE_FACTOR = .24f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .3f
        private const val DEFAULT_ALPHA = 1f
    }

    /**
     * 이 함수는 viewpager를 스와이프 하면 호출된다.
     */
    override fun transformPage(page: View, position: Float) {
        page.apply {
            /**
             * position
             * 0 -> 페이지가 화면을 채울때의 상태
             * 1 -> 화면기준 가장 오른쪽
             * -1 -> 화면기준 가장 왼쪽
             *
             * 예를 들어서 position이 0.5이면 오른쪽에서 들어오는 페이지
             * -0.5이면 정면에서 왼쪽으로 빠지는 페이지이다.
             */

            ViewCompat.setElevation(page, -abs(position)) // 그림자

            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

            when {
                position <= 0f -> { // 드래그해서 왼쪽으로 빠지는 페이지와 중앙으로 들어오는 페이지
                    translationX = DEFAULT_TRANSLATION_X
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA + position
                }
                position <= offscreenPageLimit - 1 -> { // [0보다 크고 1 이하]오른쪽에서 들어오는 페이지
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                    alpha = alphaFactor
                }
                else -> { // 이전 페이지
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR) * position
                    alpha = alphaFactor
                }
            }
        }
    }
}