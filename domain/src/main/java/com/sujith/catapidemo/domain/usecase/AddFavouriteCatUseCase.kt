package com.sujith.catapidemo.domain.usecase

import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository

class AddFavouriteCatUseCase(private val catListRepository: CatListRepository) {
    suspend fun addFavourite(catListItem: CatListItem) = catListRepository.addFavourite(catListItem)
}