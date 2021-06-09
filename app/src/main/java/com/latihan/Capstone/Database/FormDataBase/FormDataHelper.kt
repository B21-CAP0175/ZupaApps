package com.latihan.Capstone.Database.FormDataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


import java.sql.SQLException

class FormDataHelper(context: Context) {
    private var dataBaseHelper: FormDatabaseHelper = FormDatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = FormEntity.UserColumns.TABLE_NAME

        private var INSTANCE: FormDataHelper? = null
        fun getInstance(context: Context): FormDataHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: FormDataHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }

    fun close() {
        dataBaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "${FormEntity.UserColumns.USERNAME} = ?",
            arrayOf(id),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(
            DATABASE_TABLE,
            values,
            "${FormEntity.UserColumns.USERNAME} = ?",
            arrayOf(id)
        )
    }
}