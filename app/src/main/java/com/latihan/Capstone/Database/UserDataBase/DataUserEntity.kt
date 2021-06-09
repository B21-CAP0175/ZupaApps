package com.latihan.Capstone.Database.UserDataBase

import android.provider.BaseColumns

object DataUserEntity {
    class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "userdata"
            const val USERNAME = "username"
            const val PASSWORD = "password"
            const val LOCATION = "location"
            const val NAME = "name"
            const val NIK = "nik"
            const val NKK = "nkk"
            const val NOHP = "nohp"
            const val COUNTER = "count"
        }
    }
}