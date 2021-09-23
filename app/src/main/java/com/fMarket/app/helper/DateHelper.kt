package com.fMarket.app.helper

import com.fMarket.app.constant.Config
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by Alvin Rusli on 10/7/2016.
 *
 * A helper class for date/time format.
 */
object DateHelper {
    fun parseDate(time: Long, outputPattern: String): String {
        val localeId = Locale("in", "ID")
        val outputFormat = SimpleDateFormat(outputPattern, localeId)
        val date = Date()
        date.time = time
        var str: String? = null
        try {
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.localizedMessage
        }
        return str ?: ""
    }

    private fun parseDate(time: String?, inputPattern: String, outputPattern: String): String {
        if (time.isNullOrEmpty()) return ""
        val localeId = Locale("in", "ID")
        val inputFormat = SimpleDateFormat(inputPattern, localeId)
        val outputFormat = SimpleDateFormat(outputPattern, localeId)
        val date: Date?
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date ?: Date())
        } catch (e: ParseException) {
            e.localizedMessage
        }
        return str ?: ""
    }

    fun reformat(
        dateText: String?,
        inputPattern: String? = null,
        outputPattern: String? = null,
    ): String {
        return parseDate(
            dateText,
            inputPattern ?: Config.TIME_INPUT,
            outputPattern ?: Config.TIME_OUTPUT
        )
    }
}
