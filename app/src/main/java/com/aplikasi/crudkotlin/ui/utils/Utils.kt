package com.aplikasi.crudkotlin.ui.utils

object Utils {
    val PATH_COLLECTION = "users"
    val PATH_AGE = "intAge"

    fun setTimeStamp(): Long {
        val time = (-1 * System.currentTimeMillis())
        return time
    }
}