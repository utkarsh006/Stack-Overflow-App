package com.tejas.stackoverflow.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tejas.stackoverflow.model.Owner
import com.tejas.stackoverflow.model.Question


@Database(
    entities = [Question::class, Owner::class],
    exportSchema = true,
    version = 1
)
@TypeConverters(ListToStringConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun getMainDao(): MainDao
}