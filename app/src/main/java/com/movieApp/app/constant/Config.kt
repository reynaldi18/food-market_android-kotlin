package com.movieApp.app.constant

/** General configuration */
object Config {
    const val CURRENCY_PREFIX = "Rp "
    const val TIME_INPUT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val DATE_INPUT = "yyyy-MM-dd"
    const val DATE_OUTPUT = "d MMMM yyyy"
    const val TIME_OUTPUT = "d MMMM yyyy, HH:mm"
    const val AUTOCOMPLETE_COUNTRY = "country:id"
    const val LOG = "LOG_DEBUG"
    const val API_VERSION = "v1"
    const val BANNER_SLIDE_TIME: Long = 5000
    const val SUGGESTION_DELAY: Long = 500
    const val SUGGESTION_DELAY_LOCATION: Long = 750
    const val DEFAULT_MAP_ZOOM = 13F
    const val REQUEST_TIMEOUT = 30L
    const val DEFAULT_MAP_LATITUDE = -6.947114
    const val DEFAULT_MAP_LONGITUDE = 107.601397
    const val PERCENTAGE = 100.0
    const val REQUEST_IMAGE: Int = 101
    const val REQUEST_SAVE_FILE: Int = 102
    const val REQUEST_PICK_FILE: Int = 103
    const val DEFAULT_IMAGE_SIZE = 1280
    val RANDOM_IMAGES_STORE = arrayListOf(
        "https://images.unsplash.com/photo-1466637574441-749b8f19452f?ixlib=rb-1.2.1&ixid=MXwxMjA3" +
                "fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=2000&q=80",
        "https://images.unsplash.com/photo-1536512502957-4f9b4bb4a5ee?ixid=MXwxMjA3fDB8MHxwaG90by1wY" +
                "WdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1051&q=80",
        "https://images.unsplash.com/photo-1470549813517-2fa741d25c92?ixid=MXwxMjA3fDB8MHxwaG90" +
                "by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80"
    )
    val RANDOM_IMAGES_VOUCHER = arrayListOf(
        "https://images.unsplash.com/photo-1482049016688-2d3e1b311543?ixlib=rb-1.2.1&ixid=MXwxMjA3f" +
                "DB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=653&q=80",
        "https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixlib=rb-1.2.1&ixid=MXwxMjA3fD" +
                "B8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80",
        "https://images.unsplash.com/photo-1504674900247-0877df9cc836?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB" +
                "8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80",
        "https://images.unsplash.com/photo-1473093295043-cdd812d0e601?ixlib=rb-1.2.1&ixid=MXwxMjA3f" +
                "DB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80",
        "https://images.unsplash.com/photo-1476224203421-9ac39bcb3327?ixlib=rb-1.2.1&ixid=MXwxMjA3f" +
                "DB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80"
    )
}
