package com.latihan.Capstone.Database.DataClass

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Form(
    var username: String? = null,
    var Tanggal: String? = null,
    var location: String? = null,
    var Waktu: String? = null,
    var Tipe: String? = null,
    var Detail_Kejahatan: String? = null,
): Parcelable