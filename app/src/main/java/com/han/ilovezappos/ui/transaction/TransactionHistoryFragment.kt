package com.han.ilovezappos.ui.transaction

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.EntryXComparator

import com.han.ilovezappos.R
import com.han.ilovezappos.model.Crypto
import com.han.ilovezappos.ui.common.DateAxisValueFormatter
import com.han.ilovezappos.ui.common.PriceMarkerView
import kotlinx.android.synthetic.main.fragment_transaction_history.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

class TransactionHistoryFragment : Fragment() {


    companion object {
        fun newInstance() = TransactionHistoryFragment()
    }

    private lateinit var viewModel: TransactionHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()

        viewModel = ViewModelProviders.of(this).get(TransactionHistoryViewModel::class.java)
        viewModel.cryptoPrices.observe(viewLifecycleOwner, Observer {
            view_loading.visibility = View.GONE
            setChartData(it)
            view_chart.animateX(1500)
            view_chart.invalidate()
        })
    }

    fun initChart() {
        view_chart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            setDrawGridBackground(false)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            axisRight.isEnabled = false
        }

        context?.let {
            val markerView = PriceMarkerView(it, R.layout.view_marker).apply {
                chartView = view_chart
            }
            view_chart.marker = markerView
        }


        view_chart.legend.form = Legend.LegendForm.LINE

        view_chart.invalidate()
    }

    private fun setChartData(cryptos: List<Crypto>) {
        val entries = ArrayList<Entry>()

        val startTime = cryptos.firstOrNull()?.timestamp ?: 0
        var maxPrice = cryptos.firstOrNull()?.price ?: 0.0
        var minPrice = cryptos.firstOrNull()?.price ?: 0.0

        cryptos.forEach {
            maxPrice = max(maxPrice, it.price)
            minPrice = min(minPrice, it.price)
            entries.add(Entry((it.timestamp - startTime).toFloat(), it.price.toFloat()))
        }

        setGraphMinAndMaxBound(minPrice, maxPrice)
        setXAxisFormat(startTime)

        Collections.sort(entries, EntryXComparator())

        val lineDataSet = LineDataSet(entries, "BTC").apply {
            lineWidth = 1.5f
            circleRadius = 4f
        }

        val lineData = LineData(lineDataSet)

        view_chart.data = lineData

    }

    private fun setGraphMinAndMaxBound(min: Double, max: Double) {
        val diff: Double = (max - min) / 3
        view_chart.axisLeft.apply {
            //            axisMinimum = 0f
            enableGridDashedLine(10f, 10f, 0f)
            axisMinimum = (min - diff).toFloat()
            axisMaximum = (max + diff).toFloat()
        }
    }

    private fun setXAxisFormat(startTime: Int) {
        view_chart.xAxis.apply {
            valueFormatter = DateAxisValueFormatter(startTime)
            enableGridDashedLine(10f, 10f, 0f)
        }
    }
}
