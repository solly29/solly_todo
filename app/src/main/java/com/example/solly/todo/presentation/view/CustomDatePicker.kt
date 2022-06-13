package com.example.solly.todo.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.DatePicker
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.example.solly.todo.R
import com.example.solly.todo.databinding.CustomDatepickerBinding
import java.lang.IllegalArgumentException
import java.util.*

class CustomDatePicker @JvmOverloads constructor(context: Context, val attr: AttributeSet? = null, defStyle: Int = 0): LinearLayout(context, attr, defStyle) {

    private val binding: CustomDatepickerBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.custom_datepicker,
        this,
        true
    )

    var weekArrayList: List<String> = listOf("월", "화", "수", "목", "금", "토", "일")

    var maxYear = 5000
        set(value) {
            if(value < minYear || value > 5000)
                throw IllegalArgumentException("최소 년도보다 커야되고 5000보다 작아야됩니다.")
            else field = value
        }

    var minYear = 1000
        set(value) {
            field = if(value > maxYear || value < 1000)
                throw IllegalArgumentException("최대 년도보다 작아야되고 1000보다 커야됩니다.")
            else
                value
        }

    var maxMonth = 12
        set(value) {
            if(value < minMonth || value > 12)
                throw IllegalArgumentException("12보다 작아야됩니다.")
            else field = value
        }

    var minMonth = 1
        set(value) {
            field = if(value > maxMonth || value < 1)
                throw IllegalArgumentException("1보다 커야됩니다.")
            else
                value
        }

    var maxDay = 31
        set(value) {
            if(value < minDay || value > 31)
                throw IllegalArgumentException("31보다 작아야됩니다.")
            else field = value
        }

    var minDay = 1
        set(value) {
            field = if(value > maxDay || value < 1)
                throw IllegalArgumentException("1보다 커야됩니다.")
            else
                value
        }

    init {
        initAttr()
        initViewSetting()
        initEvent()
    }

    private fun initAttr() {
        attr?.let {
            val typeArray = context.obtainStyledAttributes(it, R.styleable.CustomDatePicker)

            maxYear = typeArray.getInteger(R.styleable.CustomDatePicker_maxYear, 5000)
            maxMonth = typeArray.getInteger(R.styleable.CustomDatePicker_maxMonth, 12)
//            maxDay = typeArray.getInteger(R.styleable.CustomDatePicker_maxDay, 31)

            minYear = typeArray.getInteger(R.styleable.CustomDatePicker_minYear, 1000)
            minMonth = typeArray.getInteger(R.styleable.CustomDatePicker_minMonth, 1)
//            minDay = typeArray.getInteger(R.styleable.CustomDatePicker_minDay, 1)

            typeArray.recycle()
        }
    }

    private fun initViewSetting() {
        with(binding) {
            npCustomDpYear.apply {
                maxValue = maxYear
                minValue = minYear
                wrapSelectorWheel = false
            }

            npCustomDpMonth.apply {
                maxValue = maxMonth
                minValue = minMonth
                wrapSelectorWheel = false
            }

            npCustomDpDay.apply {
                maxValue = maxDay
                minValue = minDay
                wrapSelectorWheel = false
            }

            npCustomDpWeek.apply {
                maxValue = weekArrayList.size - 1
                minValue = 0
                wrapSelectorWheel = false
                displayedValues = weekArrayList.toTypedArray()
            }
        }
    }

    private fun initEvent() {
        with(binding) {
            npCustomDpYear.setOnValueChangedListener { picker, oldVal, newVal ->
                Log.e("Test", newVal.toString())
            }
        }
    }
}