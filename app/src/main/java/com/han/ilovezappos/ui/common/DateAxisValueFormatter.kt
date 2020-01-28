package com.han.ilovezappos.ui.common

import android.text.format.DateFormat
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

import java.util.*

class DateAxisValueFormatter(val startTimestamp: Int) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = (startTimestamp + value.toInt()) * 1000L
        return DateFormat.format("hh:mm:ss", calendar).toString()
    }
}