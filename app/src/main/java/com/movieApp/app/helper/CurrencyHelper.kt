package com.movieApp.app.helper

import com.movieApp.app.constant.Config
import java.text.NumberFormat
import java.util.Locale

/**
 * Utilities to convert string / double value to currency format
 * Created by MuhammadLucky on 20/02/2018.
 */

object CurrencyHelper {
    private const val DEFAULT_VALUE = "0"

    private fun toCurrencyWithPrefix(value: Double): String {
        val tempValue = round(value)
        //Last option
        var str = NumberFormat.getNumberInstance(Locale.US).format(tempValue)
        if (str.contains(".00") && str.length > 3) str = str.substring(0, str.length - 3)
        str = str.replace(".", "#")
        str = str.replace(",", ".")
        str = str.replace("#", ",")
        return Config.CURRENCY_PREFIX.plus(str)
    }

    fun toCurrencyWithPrefix(text: String?): String {
        if (text == null || text.isEmpty())
            return DEFAULT_VALUE

        return try {
            val value = java.lang.Double.valueOf(text)
            toCurrencyWithPrefix(round(value))
        } catch (e: Exception) {
            DEFAULT_VALUE
        }
    }

    fun toCurrencyWithPrefix(value: Long?): String {
        if (value == null || value == 0L) return Config.CURRENCY_PREFIX.plus("0")
        return toCurrencyWithPrefix(value.toDouble())
    }

    fun toCurrencyWithPrefix(value: Int?): String {
        if (value == null || value == 0) return Config.CURRENCY_PREFIX.plus("0")
        return toCurrencyWithPrefix(value.toDouble())
    }

    fun toCurrency(value: Long?): String {
        if (value == null || value == 0L) return DEFAULT_VALUE

        val tempValue = round(value.toDouble())
        //Last option
        var str = NumberFormat.getNumberInstance(Locale.US).format(tempValue)
        if (str.contains(".00") && str.length > 3) str = str.substring(0, str.length - 3)
        str = str.replace(".", "#")
        str = str.replace(",", ".")
        str = str.replace("#", ",")
        return str
    }

    fun getValue(currency: String?): Long {
        if (currency.isNullOrEmpty()) return 0

        var value = currency
        value = value.replace(".", "")
        value = value.replace(",", ".")
        return value.toLong()
    }

    fun toCurrency(currency: String?): String {
        val value = if (!currency.isNullOrEmpty()) currency else DEFAULT_VALUE
        return try {
            toCurrency(value.toLong())
        } catch (e: NumberFormatException) {
            DEFAULT_VALUE
        }
    }

    private fun round(d: Double): Long {
        return d.toLong()
    }
}
