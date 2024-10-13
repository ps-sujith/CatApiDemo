package com.sujith.catapidemo.data.dataSource

import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.flow.Flow

interface LocalCatListDataSource {
    suspend fun addFavourite(catListItem: CatListItem)
    suspend fun removeFavourite(catListItem: CatListItem)
    suspend fun getFavouriteCatList(): Flow<List<CatListItem>>
}