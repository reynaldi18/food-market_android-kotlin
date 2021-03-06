package com.fMarket.app.helper

fun <E> MutableList<E>.setAll(data: List<E>?) {
    clear()
    data?.let { addAll(it) }
}
