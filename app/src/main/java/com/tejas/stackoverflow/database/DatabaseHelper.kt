package com.tejas.stackoverflow.database

import android.content.Context
import androidx.room.Room


class DatabaseHelper constructor(context: Context) {
    private val db = Room.databaseBuilder(context, Database::class.java, "StackOverflowDB")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    fun getDatabase() = db

}