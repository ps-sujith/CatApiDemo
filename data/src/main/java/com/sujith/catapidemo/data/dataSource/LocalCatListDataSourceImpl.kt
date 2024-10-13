package com.sujith.catapidemo.data.dataSource

import com.sujith.catapidemo.data.database.CatDao
import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.flow.Flow

class LocalCatListDataSourceImpl(private val catDao: CatDao) : LocalCatListDataSource {
    override suspend fun addFavourite(catListItem: CatListItem) = catDao.addFavourite(catListItem)

    override suspend fun removeFavourite(catListItem: CatListItem) =
        catDao.removeFavourite(catListItem)

    override suspend fun getFavouriteCatList(): Flow<List<CatListItem>> =
        catDao.getFavouriteCatList()
}