package com.latihan.Capstone.Database.FormDataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity

class FormDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbformdata"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_USER =
            "CREATE TABLE ${FormEntity.UserColumns.TABLE_NAME}" +
                    " (${FormEntity.UserColumns.USERNAME} TEXT PRIMARY KEY NOT NULL," +
                    " ${FormEntity.UserColumns.DATE} TEXT NOT NULL," +
                    " ${FormEntity.UserColumns.LOCATION} TEXT NOT NULL," +
                    " ${FormEntity.UserColumns.TIME} TEXT NOT NULL," +
                    " ${FormEntity.UserColumns.TYPE} TEXT NOT NULL," +
                    " ${FormEntity.UserColumns.DETAIL} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE_TABLE_USER)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS ${DataUserEntity.UserColumns.TABLE_NAME}")
        }
        onCreate(db)
    }
}