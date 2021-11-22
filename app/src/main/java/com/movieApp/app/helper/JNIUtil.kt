package com.movieApp.app.helper

import com.movieApp.app.constant.Config

object JNIUtil {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiEndpoint(): String
}
