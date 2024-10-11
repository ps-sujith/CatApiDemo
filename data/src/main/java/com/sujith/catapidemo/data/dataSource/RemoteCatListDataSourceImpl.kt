package com.sujith.catapidemo.data.dataSource

import com.sujith.catapidemo.data.api.CatListApiService
import com.sujith.catapidemo.data.dto.CatListItemDto

class RemoteCatListDataSourceImpl(private val catListApiService: CatListApiService) :
    RemoteCatListDataSource {
    override suspend fun getCatListWithBreed(): List<CatListItemDto> {
        return try {
            catListApiService.getCatListWithBreed().ifEmpty { emptyList() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}