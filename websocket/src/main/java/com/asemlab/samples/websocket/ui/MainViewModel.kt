package com.asemlab.samples.websocket.ui

import android.util.Log
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.websocket.R
import com.asemlab.samples.websocket.di.NetworkModule
import com.asemlab.samples.websocket.models.Currencies
import com.asemlab.samples.websocket.remote.SocketCallbacks
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO socketCallbacks have 2 different implementations KtorSocket & OkHttpSocket
@HiltViewModel
class MainViewModel @Inject constructor(@NetworkModule.KtorSocket private val socketCallbacks: SocketCallbacks) :
    ViewModel() {

    private lateinit var menu: PopupMenu
    private lateinit var lineDataSet: LineDataSet
    val currency = MutableLiveData(Currencies.DOLLAR)
    val prices = MutableLiveData(listOf<Double>())
    val currentPrice = MutableLiveData("0.0")
    val highestPrice = MutableLiveData("0.0")
    val lowestPrice = MutableLiveData("0.0")

    init {
        socketCallbacks.init()
        viewModelScope.launch {
            socketCallbacks.prices.collectLatest {
                prices.value = prices.value?.plus(it)
            }
        }
    }

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

    fun sendCurrency() {
        socketCallbacks.sendMessage(currency.value!!.title)
    }

    fun updateLineChart(lineChart: LineChart, entries: List<Double>) {
        with(lineChart) {
            if (entries.isNotEmpty()) {
                isAutoScaleMinMaxEnabled = true

                currentPrice.value = entries.last().toString()
                highestPrice.value = entries.max().toString()
                lowestPrice.value = entries.min().toString()

                xAxis.apply {
                    axisMinimum = -1f
                    axisMaximum = entries.lastIndex.toFloat() + 1
                }

                val entry = Entry(entries.lastIndex.toFloat(), entries.last().toFloat())

                try {

                    data.addEntry(entry, 0)

                } catch (e: Exception) {

                    Log.e("MainViewModel", "${e.message}")
                    initLineData()
                    data = LineData(lineDataSet)
                    data.addEntry(entry, 0)

                } finally {
                    val hasRaised =
                        entries.size == 1 || entries.last() >= entries[entries.lastIndex - 1]

                    val colorId =
                        if (hasRaised) R.color.green else R.color.red

                    val fillColor =
                        if (hasRaised) R.drawable.line_chart_fill_green else
                            R.drawable.line_chart_fill_red

                    lineDataSet.apply {
                        color = resources.getColor(colorId)
                        fillDrawable =
                            ContextCompat.getDrawable(context, fillColor)
                    }

                    data.notifyDataChanged()
                    notifyDataSetChanged()
                    animateX(500)
                }

            } else {
                clear()
            }
        }
    }

    fun initLineChart(lineChart: LineChart) {

        with(lineChart) {

            xAxis.apply {
                setDrawGridLines(false)
                position = XAxis.XAxisPosition.BOTTOM
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
            legend.textColor = resources.getColor(R.color.black)

        }
    }

    private fun LineChart.initLineData() {

        lineDataSet = LineDataSet(
            mutableListOf(),
            "${currency.value?.title} last updates"
        ).apply {
            fillAlpha = 120
            lineWidth = 3f
            circleHoleRadius = 0.5f
            circleRadius = 2.5f
            setCircleColor(resources.getColor(R.color.black))
            valueTextSize = 0f
            setDrawFilled(true)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawHorizontalHighlightIndicator(false)
            setDrawVerticalHighlightIndicator(false)
        }
    }

}