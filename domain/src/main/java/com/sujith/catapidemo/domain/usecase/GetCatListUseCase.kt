package com.sujith.catapidemo.domain.usecase

import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class GetCatListUseCase {

   suspend fun getCatList(): Flow<Result<List<CatListItem>>> {
        return emptyFlow()
    }
}
