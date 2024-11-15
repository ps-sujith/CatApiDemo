package com.sujith.catapidemo.domain.repository

import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.flow.Flow

interface CatListRepository {
    suspend fun getCatListWithBreed(): Flow<Result<List<CatListItem>>>
    suspend fun addFavourite(catListItem: CatListItem)
    suspend fun removeFavourite(catListItem: CatListItem)
    suspend fun getFavouriteCatList(): Flow<List<CatListItem>>
}
