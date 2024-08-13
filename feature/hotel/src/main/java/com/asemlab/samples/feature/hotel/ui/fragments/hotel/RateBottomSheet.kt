package com.asemlab.samples.feature.hotel.ui.fragments.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.asemlab.samples.feature.hotel.databinding.FramentRateBinding
import com.asemlab.samples.feature.hotel.model.Rate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RateBottomSheet private constructor(
    private val hotelId: String,
    private val onClick: (Rate) -> Unit
) :
    BottomSheetDialogFragment() {

    lateinit var binding: FramentRateBinding
    private var score = 1
    private var comment = ""
    private var username = "Anonymous"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FramentRateBinding.inflate(inflater, container, false)

        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            rateButton.setOnClickListener {
                onClick(
                    Rate(
                        this@RateBottomSheet.score,
                        this@RateBottomSheet.comment,
                        this@RateBottomSheet.username,
                        hotelId
                    )
                )
                dismiss()
            }

            comment.editText?.doOnTextChanged { text, _, _, _ ->
                this@RateBottomSheet.comment = text.toString()
                binding.rateButton.isEnabled = text!!.isNotEmpty()
            }
            username.editText?.doOnTextChanged { text, _, _, _ ->
                this@RateBottomSheet.username = text.toString().ifEmpty { "Anonymous" }
            }
            ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                this@RateBottomSheet.score = if (rating.toInt() > 0) rating.toInt() else 1
            }

        }


        return binding.root
    }

    companion object {
        fun getInstance(hotelId: String, onClick: (Rate) -> Unit) =
            RateBottomSheet(hotelId, onClick)
    }
}