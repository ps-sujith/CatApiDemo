package com.sujith.catapidemo.data.api

import com.sujith.catapidemo.data.BuildConfig
import com.sujith.catapidemo.data.dto.CatListItemDto
import retrofit2.http.GET

interface CatListApiService {

    @GET("v1/images/search?size=med&mime_types=jpg&format=json&has_breeds=true&page=0&limit=20&api_key=${BuildConfig.API_KEY}")
    suspend fun getCatListWithBreed() :List<CatListItemDto>
}