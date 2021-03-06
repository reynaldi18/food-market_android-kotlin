package com.fMarket.app.helper

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fMarket.app.R
import kotlinx.android.synthetic.main.dialog_progress.view.*

/**
 * Created by Alvin Rusli on 4/6/2016.
 *
 * A customizable loading dialog.
 *
 * For global usage (that overrides onBackPressed() to do nothing),
 *
 * For customized usage that can handle back press,
 * use (Context, OnBackPressListener)}.
 */
class ProgressDialog(context: Context) {

    /** The dialog */
    private val dialog: Dialog

    /** The view binding */
    private var view: View? = null

    /** The custom back press listener when progress dialog is showing */
    var backPressListener: OnBackPressListener? = null

    /** Generate a [dialog] with back press listener passed in it */
    private fun dialog(context: Context, themeResId: Int): Dialog {
        return object : Dialog(context, themeResId) {
            override fun onBackPressed() {
                backPressListener?.onProgressBackPressed()
            }
        }
    }

    init {
        val nullParent: ViewGroup? = null
        view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, nullParent)
        dialog = dialog(context, R.style.AppTheme_Dialog_Normal)
        dialog.setCanceledOnTouchOutside(false)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        view?.let { dialog.setContentView(it) }
    }

    /**
     * Sets the text for the progress dialog.
     * @param stringRes the string resource ID
     */
    fun setText(stringRes: Int) {
        try {
            val text = getString(stringRes)
            setText(text)
        } catch (e: Resources.NotFoundException) {
            setText(null)
        }
    }

    /**
     * Sets the text for the progress dialog.
     * @param message the string message
     */
    private fun setText(message: String?) {
        view?.txt_loading?.text = message
        if (message.isNullOrEmpty()) view?.txt_loading?.visibility = View.GONE
        else view?.txt_loading?.visibility = View.VISIBLE
    }

    /** Show the progress dialog */
    fun show() {
        dialog.show()
    }

    /** Dismisses the progress dialog */
    fun dismiss() {
        try {
            dialog.dismiss()
        } catch (e: Exception) {
            e.localizedMessage
        }
    }

    /** Determines if the progress dialog is currently showing */
    val isShowing: Boolean
        get() = dialog.isShowing

    /** The interface to handle back button press while loading is showing */
    interface OnBackPressListener {

        /** Called when back button is pressed when loading is showing */
        fun onProgressBackPressed()
    }

    fun setCancelable(cancelable: Boolean) {
        dialog.setCancelable(cancelable)
        dialog.setCanceledOnTouchOutside(cancelable)
    }
}
