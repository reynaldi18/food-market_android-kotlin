package com.fMarket.app.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.fMarket.app.BuildConfig
import com.fMarket.app.R

/**
 * Created by Alvin Rusli on 1/24/2016.
 *
 * A class that handles basic universal methods.
 */
object Common {

    /** The loading progress dialog object */
    @SuppressLint("StaticFieldLeak")
    private var progressDialog: ProgressDialog? = null

    /**
     * Shows a loading progress dialog.
     * @param context the context
     * @param stringRes the dialog message string resource id
     * @param onBackPressListener the back button press listener when loading is shown
     */
    fun showProgressDialog(
        context: Context?,
        stringRes: Int = R.string.message_please_wait,
        onBackPressListener: ProgressDialog.OnBackPressListener? = null,
        cancelable: Boolean? = false
    ) {
        dismissProgressDialog()
        if (context == null) return
        progressDialog = ProgressDialog(context)
        progressDialog!!.setText(stringRes)
        if (cancelable != null)
            progressDialog!!.setCancelable(cancelable)
        progressDialog!!.backPressListener = onBackPressListener
        if (context is Activity && !context.isFinishing) progressDialog!!.show()
    }

    /** Hides the currently shown loading progress dialog */
    fun dismissProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    /**
     * Display a simple [Toast].
     * @param stringRes The message string resource id
     */
    fun showToast(stringRes: Int) {
        showToast(getString(stringRes))
    }

    /**
     * Display a simple [Toast].
     * @param message The message string
     */
    fun showToast(message: String?) {
        dismissProgressDialog()
        message?.let {
            val toast = Toast.makeText(getAppContext(), message, Toast.LENGTH_SHORT)
            val v = toast.view?.findViewById(android.R.id.message) as TextView?
            if (v != null) v.gravity = Gravity.CENTER
            toast.show()
        }
    }

    /**
     * Prints an exception's stack trace.
     * Stack traces printed via this method will only show up on debug builds.
     * @param throwable the throwable
     */
    fun printStackTrace(throwable: Throwable) {
        if (BuildConfig.DEBUG) throwable.printStackTrace()
    }

    /**
     * Display a simple [AlertDialog] with a simple OK button.
     * If the dismiss listener is specified, the dialog becomes uncancellable
     * @param context The context
     * @param title The title string
     * @param message The message string
     * @param dismissListener The dismiss listener
     */
    fun showMessageDialog(
        context: Context? = getAppContext(),
        title: String? = null,
        message: String?,
        dismissListener: DialogInterface.OnDismissListener? = null
    ) {
        if (context == null) return
        val builder = AlertDialog.Builder(context, R.style.AppTheme_Dialog_Alert)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        if (dismissListener != null)
            dialog.setOnDismissListener(dismissListener)
        dialog.show()
        val textView = dialog.findViewById<TextView>(android.R.id.message)
        textView?.textSize = 16f
    }

    fun showMessageDialog(context: Context?, message: String?) {
        if (context == null || message == null) return
        showMessageDialog(context = context, title = null, message = message)
    }
}
