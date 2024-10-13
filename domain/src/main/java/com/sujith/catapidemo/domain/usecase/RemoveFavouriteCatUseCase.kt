package com.sujith.catapidemo.domain.usecase

import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository

class RemoveFavouriteCatUseCase(private val catListRepository: CatListRepository) {
    suspend fun removeFavourite(catListItem: CatListItem) = catListRepository.removeFavourite(catListItem)
}