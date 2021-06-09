package com.latihan.Capstone.Database.UserDataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity.UserColumns.Companion.TABLE_NAME

class UserDatabaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbuserdata"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_USER = "CREATE TABLE $TABLE_NAME" +
                " (${DataUserEntity.UserColumns.USERNAME} TEXT PRIMARY KEY NOT NULL," +
                " ${DataUserEntity.UserColumns.PASSWORD} TEXT NOT NULL," +
                " ${DataUserEntity.UserColumns.LOCATION} TEXT NOT NULL," +
                " ${DataUserEntity.UserColumns.NAME} TEXT NOT NULL," +
                " ${DataUserEntity.UserColumns.NIK} TEXT NOT NULL," +
                " ${DataUserEntity.UserColumns.NKK} TEXT NOT NULL," +
                " ${DataUserEntity.UserColumns.COUNTER} TEXT NOT NULL," +
                " ${DataUserEntity.UserColumns.NOHP} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE_TABLE_USER)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        }
        onCreate(db)
    }
}