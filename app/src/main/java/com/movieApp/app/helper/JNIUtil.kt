package com.movieApp.app.helper

import com.movieApp.app.constant.Config

object JNIUtil {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiEndpoint(): String

    fun getMassUpdateProductUrl() =
        if (isMock()) "https://file-examples-com.github.io/uploads/2017/02/file_example_XLS_10.xls"
        else apiEndpoint() + "api/${Config.API_VERSION}/mass_update"
}
