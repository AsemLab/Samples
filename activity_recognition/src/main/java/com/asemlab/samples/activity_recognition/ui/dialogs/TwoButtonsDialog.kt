package com.asemlab.samples.activity_recognition.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asemlab.samples.activity_recognition.databinding.DialogTwoButtonsBinding

open class TwoButtonsDialog private constructor() : BaseDialog() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTwoButtonsBinding.inflate(inflater, container, false).also {
            it.titleTv.text = title
            it.messageTv.text = message
            it.okButton.text = okTitle
            it.closeButton.text = closeTitle

            it.okButton.setOnClickListener { v ->
                onOkClick.onClick(v)
                dismiss()
            }
            it.closeButton.setOnClickListener { v ->
                onCloseClick.onClick(v)
                dismiss()
            }
        }


        return binding.root
    }


    class Builder : BaseDialog.Builder() {
        override var dialog: BaseDialog = TwoButtonsDialog()

        override fun build() = dialog

    }


}