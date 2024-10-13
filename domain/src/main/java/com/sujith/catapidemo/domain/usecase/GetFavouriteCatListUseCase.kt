package com.sujith.catapidemo.domain.usecase

import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteCatListUseCase(private val catListRepository: CatListRepository) {
    suspend fun getFavouriteCatList() :Flow<List<CatListItem>> {
         return catListRepository.getFavouriteCatList()
    }
}