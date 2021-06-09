package com.latihan.Capstone.Database

import android.database.Cursor
import com.latihan.Capstone.Database.DataClass.User
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity

object MappingHelper {
    fun mapCursorToObject(notesCursor: Cursor?): User {
        var user = User()
        notesCursor?.apply {
            moveToFirst()
            val usernames = getString(getColumnIndexOrThrow(DataUserEntity.UserColumns.USERNAME))
            val password = getString(getColumnIndexOrThrow(DataUserEntity.UserColumns.PASSWORD))
            val names = getString(getColumnIndexOrThrow(DataUserEntity.UserColumns.NAME))
            val locations = getString(getColumnIndexOrThrow(DataUserEntity.UserColumns.LOCATION))
            val counters = getString(getColumnIndexOrThrow(DataUserEntity.UserColumns.COUNTER))
            user = User(username = usernames, pass = password , name = names, location = locations , counter = counters )
        }
        return user
    }
}