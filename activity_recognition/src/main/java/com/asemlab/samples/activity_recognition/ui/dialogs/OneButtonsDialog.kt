package com.asemlab.samples.activity_recognition.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.asemlab.samples.activity_recognition.databinding.DialogTwoButtonsBinding

class OneButtonsDialog private constructor() : BaseDialog() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTwoButtonsBinding.inflate(inflater, container, false).also {
            it.titleTv.text = title
            it.messageTv.text = message
            it.okButton.text = okTitle
            it.closeButton.isVisible = false

            it.okButton.setOnClickListener { v ->
                onOkClick.onClick(v)
                dismiss()
            }
        }


        return binding.root
    }

    class Builder : BaseDialog.Builder() {
        override var dialog: BaseDialog = OneButtonsDialog()

        override fun build() = dialog

    }

}