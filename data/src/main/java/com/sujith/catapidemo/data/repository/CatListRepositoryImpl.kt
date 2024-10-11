package com.sujith.catapidemo.data.repository

import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSource
import com.sujith.catapidemo.data.dto.CatListItemDto
import com.sujith.catapidemo.data.mapper.toDomain
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatListRepositoryImpl(private val catListRemoteDataSource: RemoteCatListDataSource) : CatListRepository {

    override suspend fun getCatListWithBreed(): Flow<Result<List<CatListItem>>> {
        val result: List<CatListItemDto> = catListRemoteDataSource.getCatListWithBreed()
        return flow {
            if (result.isNotEmpty()) {
                val mappedResult = result.map { it.toDomain() }
                emit(Result.success(mappedResult))
            } else {
                emit(Result.failure(RuntimeException("Something went wrong")))
            }
        }
    }

}