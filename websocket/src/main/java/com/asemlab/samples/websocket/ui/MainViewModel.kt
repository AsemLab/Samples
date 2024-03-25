package com.asemlab.samples.websocket.ui

import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asemlab.samples.websocket.R
import com.asemlab.samples.websocket.models.Currencies
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class MainViewModel : ViewModel() {

    private lateinit var menu: PopupMenu
    val currency = MutableLiveData(Currencies.DOLLAR)
    val prices = MutableLiveData(listOf<Double>())
    val currentPrice = MutableLiveData("0.0")
    val highestPrice = MutableLiveData("0.0")
    val lowestPrice = MutableLiveData("0.0")

    fun showMenu(view: View) {
        if (!::menu.isInitialized)
            initMenu(view)

        menu.show()
    }

    private fun initMenu(view: View) {
        menu = PopupMenu(view.context, view).also {
            it.inflate(R.menu.currency_menu)
            it.setForceShowIcon(true)

            it.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.dollar_item -> {
                        currency.value = Currencies.DOLLAR
                    }

                    R.id.euro_item -> {
                        currency.value = Currencies.EURO
                    }

                    R.id.pound_item -> {
                        currency.value = Currencies.POUND
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }

    }

    fun drawLineChart(lineChart: LineChart, entries: List<Double>) {
//        val newPrices: List<Double> = listOf(0.77, 0.756, 0.750, 0.74, 0.78, 0.73)

        with(lineChart) {
            if (entries.isNotEmpty()) {

                currentPrice.value = entries.last().toString()
                highestPrice.value = entries.max().toString()
                lowestPrice.value = entries.min().toString()


                val priceData = entries.map { price ->
                    Entry(entries.indexOf(price).toFloat(), price.toFloat())
                }

                val lineDataSet = LineDataSet(
                    priceData,
                    "${currency.value?.title} last updates"
                )

                val colorId =
                    if (entries.last() >= entries[entries.lastIndex - 1]) R.color.green else R.color.red

                val fillColor =
                    if (entries.last() >= entries[entries.lastIndex - 1]) R.drawable.line_chart_fill_green else R.drawable.line_chart_fill_red

                lineDataSet.apply {
                    fillAlpha = 120
                    color = resources.getColor(colorId)
                    lineWidth = 3f
                    setCircleColor(resources.getColor(colorId))
                    circleHoleRadius = 0f
                    circleRadius = 2.5f
                    valueTextSize = 0f
                    setDrawFilled(true)
                    fillDrawable =
                        ContextCompat.getDrawable(context, fillColor)
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                    setDrawHorizontalHighlightIndicator(false)
                    setDrawVerticalHighlightIndicator(false)
                }

                val sets = arrayListOf<ILineDataSet>(lineDataSet)
                val chartData = LineData(sets)
                data = chartData

                xAxis.apply {
                    setDrawGridLines(false)
                    position = XAxis.XAxisPosition.BOTTOM
                    axisMinimum = priceData.minOf { e: Entry ->
                        e.x
                    } - 2

                    axisMaximum = priceData.maxOf { e: Entry ->
                        e.x
                    } + 2


                    textColor = resources.getColor(R.color.black)
                }

                axisLeft.apply {
                    gridColor = resources.getColor(android.R.color.darker_gray)
                    setDrawAxisLine(false)
                    axisMinimum = 0.6f
                    axisMaximum = 1f

                    enableGridDashedLine(7f, 7f, 0f)
                    setDrawGridLines(true)
                    textColor = resources.getColor(R.color.black)
                }

                axisRight.apply {
                    setDrawLabels(false)
                    setDrawAxisLine(false)
                    setDrawGridLines(false)
                }

                val desc = Description()
                desc.text = ""
                description = desc
                legend.textColor =
                    resources.getColor(R.color.black)

            } else {
                clear()
            }
        }
    }

}