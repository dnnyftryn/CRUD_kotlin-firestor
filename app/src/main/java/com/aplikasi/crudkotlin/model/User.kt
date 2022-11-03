package com.aplikasi.crudkotlin.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    var strId : String ?= "0",
    var strName : String ?= null,
    var strAddress : String? = null,
    var intAge : Int? = null
)