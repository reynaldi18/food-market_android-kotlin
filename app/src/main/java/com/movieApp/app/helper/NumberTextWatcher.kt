package com.movieApp.app.helper

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

open class NumberTextWatcher(private val editText: EditText) : TextWatcher {

    override fun afterTextChanged(s: Editable) {
        editText.removeTextChangedListener(this)

        try {
            var originalString = s.toString()

            val longval: Long?
            if (originalString.contains("."))
                originalString = originalString.replace(".", "")
            if (originalString.contains(","))
                originalString = originalString.replace(",", "")

            longval = java.lang.Long.parseLong(originalString)

            var formattedString = CurrencyHelper.toCurrency(longval)
            formattedString = formattedString.replace("-", "0")
            //setting text after format to EditText
            editText.setText(formattedString)
            editText.setSelection(editText.text.length)
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }

        editText.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        TAG
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        TAG
    }

    companion object {

        private const val TAG = "NumberTextWatcher"
    }
}
