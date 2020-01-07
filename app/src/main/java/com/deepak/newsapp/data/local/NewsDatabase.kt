package com.deepak.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deepak.newsapp.data.model.NewsData

@Database(entities = [NewsData::class], version = 1, exportSchema = false)
@TypeConverters(DataConverters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}