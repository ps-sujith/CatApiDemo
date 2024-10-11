package com.sujith.catapidemo.data.dataSource

import com.sujith.catapidemo.data.dto.CatListItemDto

interface RemoteCatListDataSource {
    suspend fun getCatListWithBreed(): List<CatListItemDto>
}
