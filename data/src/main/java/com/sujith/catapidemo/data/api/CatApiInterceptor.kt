package com.sujith.catapidemo.data.api

import com.sujith.catapidemo.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class CatApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        request.addHeader("x-api-key", BuildConfig.API_KEY)

        return chain.proceed(request.build())
    }
}