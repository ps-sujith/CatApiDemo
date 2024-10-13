package com.sujith.catapidemo.data.repository

import com.sujith.catapidemo.data.dataSource.LocalCatListDataSource
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSource
import com.sujith.catapidemo.data.dto.CatListItemDto
import com.sujith.catapidemo.data.mapper.toDomain
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class CatListRepositoryImpl(
    private val remoteCatListDataSource: RemoteCatListDataSource,
    private val localCatListDataSource: LocalCatListDataSource
) : CatListRepository {
    override suspend fun getCatListWithBreed(): Flow<Result<List<CatListItem>>> {
        val result: List<CatListItemDto> = remoteCatListDataSource.getCatListWithBreed()
        return flow {
            if (result.isNotEmpty()) {
                val mappedResult = result.mapNotNull { it.toDomain() }
                emit(Result.success(mappedResult))
            } else {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        }
    }

    override suspend fun addFavourite(catListItem: CatListItem) {
        localCatListDataSource.addFavourite(catListItem)
    }

    override suspend fun removeFavourite(catListItem: CatListItem) {
        localCatListDataSource.removeFavourite(catListItem)
    }

    override suspend fun getFavouriteCatList(): Flow<List<CatListItem>> {
        return withContext(IO) {
            localCatListDataSource.getFavouriteCatList()
        }
    }
}