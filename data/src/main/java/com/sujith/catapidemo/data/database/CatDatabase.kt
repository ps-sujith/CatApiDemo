package com.sujith.catapidemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sujith.catapidemo.domain.model.CatListItem

@Database(entities = [CatListItem::class], version = 2, exportSchema = false)
abstract class CatDatabase : RoomDatabase() {
  abstract fun catDao(): CatDao
}