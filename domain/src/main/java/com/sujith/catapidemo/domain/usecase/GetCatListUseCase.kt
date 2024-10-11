package com.sujith.catapidemo.domain.usecase

import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository
import kotlinx.coroutines.flow.Flow

class GetCatListUseCase(private val catListRepository: CatListRepository) {
    suspend fun getCatList(): Flow<Result<List<CatListItem>>> =  catListRepository.getCatListWithBreed()
}
