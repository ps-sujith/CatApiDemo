package com.sujith.catapidemo.data.di

import android.app.Application
import androidx.room.Room
import com.sujith.catapidemo.data.dataSource.LocalCatListDataSource
import com.sujith.catapidemo.data.dataSource.LocalCatListDataSourceImpl
import com.sujith.catapidemo.data.database.CatDao
import com.sujith.catapidemo.data.database.CatDatabase
import org.koin.dsl.module

val dataBaseModule = module {
    single<CatDatabase> { provideCatDatabase(get()) }
    single<CatDao> { provideCatDao(get()) }
    single<LocalCatListDataSource> { provideLocalCatListDataSource(get()) }
}

fun provideLocalCatListDataSource(catDao: CatDao): LocalCatListDataSourceImpl =
    LocalCatListDataSourceImpl(catDao)

fun provideCatDao(database: CatDatabase): CatDao = database.catDao()

fun provideCatDatabase(application: Application): CatDatabase =
    Room.databaseBuilder(application, CatDatabase::class.java, "cat_db")
        .fallbackToDestructiveMigration().build()