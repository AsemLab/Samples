package com.asemlab.activity_recognition.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.asemlab.activity_recognition.databinding.DialogTwoButtonsBinding

abstract class BaseDialog : DialogFragment() {

    protected lateinit var title: String
    protected lateinit var message: String
    protected lateinit var okTitle: String
    protected lateinit var closeTitle: String
    protected lateinit var onOkClick: View.OnClickListener
    protected lateinit var onCloseClick: View.OnClickListener
    protected lateinit var binding: DialogTwoButtonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, 0)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
    }


    abstract class Builder() {
        protected open lateinit var dialog: BaseDialog

        abstract fun build(): BaseDialog

        fun setTitle(title: String): Builder {
            dialog.title = title
            return this
        }

        fun setMessage(title: String): Builder {
            dialog.message = title
            return this
        }

        fun setOkButton(title: String, listener: View.OnClickListener): Builder {
            dialog.okTitle = title
            dialog.onOkClick = listener
            return this
        }

        fun setCloseButton(title: String, listener: View.OnClickListener): Builder {
            dialog.closeTitle = title
            dialog.onCloseClick = listener
            return this
        }


    }
}