package com.latihan.Capstone.Database.UserDataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity.UserColumns.Companion.TABLE_NAME
import com.latihan.Capstone.Database.UserDataBase.DataUserEntity.UserColumns.Companion.USERNAME
import java.sql.SQLException

class UserDataHelper(context:Context) {
    private var dataBaseHelper: UserDatabaseHelper = UserDatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME

        private var INSTANCE: UserDataHelper? = null
        fun getInstance(context: Context): UserDataHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserDataHelper(context)
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
            null)
    }

    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$USERNAME = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$USERNAME = ?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$USERNAME = '$id'", null)
    }
}