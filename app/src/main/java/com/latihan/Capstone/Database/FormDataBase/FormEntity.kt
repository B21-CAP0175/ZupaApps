package com.latihan.Capstone.Database.FormDataBase

import android.provider.BaseColumns

object FormEntity {
    class UserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "formdata"
            const val USERNAME = "username"
            const val DATE = "date"
            const val LOCATION = "location"
            const val TIME = "time"
            const val TYPE = "Type"
            const val DETAIL = "detail"
        }
    }
}