package com.asemlab.samples

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.asemlab.samples.databinding.FragBinding

class TestFrag(private val title: String) : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("OnCreate")
    }

    private lateinit var binding: FragBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragBinding.inflate(inflater, container, false)

        with(binding) {

            val setColors = { view: TextView, color: Colors ->
                view.setBackgroundColor(Color.parseColor(color.hex))

                if (resources.configuration.uiMode == Configuration.UI_MODE_NIGHT_YES)
                    view.setTextColor(color.getDarkTextColor())
                else
                    view.setTextColor(color.getLightTextColor())
            }

            setColors(title, Colors.RED)
            setColors(title2, Colors.YELLOW)
            setColors(title3, Colors.BLUE)


        }

        log("onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")
    }


    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }


    private fun log(message: String) {
        Log.d("TestFrag", "${message}:: $title")
    }

}
