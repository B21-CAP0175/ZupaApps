package com.latihan.Capstone.Database.DataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var username: String? = null,
    var pass: String? = null,
    var location: String? = null,
    var name: String? = null,
    var nik: String? = null,
    var nkk: String? = null,
    var nohp: String? = null,
    var avatar: String? = null,
    var counter: String? = null
): Parcelable

