package com.sujith.catapidemo.data.di

import com.sujith.catapidemo.data.BuildConfig
import com.sujith.catapidemo.data.api.CatListApiService
import com.sujith.catapidemo.data.dataSource.LocalCatListDataSource
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSource
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSourceImpl
import com.sujith.catapidemo.data.repository.CatListRepositoryImpl
import com.sujith.catapidemo.domain.repository.CatListRepository
import com.sujith.catapidemo.domain.usecase.AddFavouriteCatUseCase
import com.sujith.catapidemo.domain.usecase.GetCatListUseCase
import com.sujith.catapidemo.domain.usecase.GetFavouriteCatListUseCase
import com.sujith.catapidemo.domain.usecase.RemoveFavouriteCatUseCase
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<CatListApiService> { provideCatListApiService(get()) }
    single<RemoteCatListDataSource> { provideRemoteCatListDataSource(get()) }
    single<CatListRepository> { provideCatListRepository(get(), get()) }
    single<GetCatListUseCase> { provideGetCatListUseCase(get()) }
    single<AddFavouriteCatUseCase> { provideAddFavouriteUseCase(get()) }
    single<RemoveFavouriteCatUseCase> { provideRemoveFavouriteUseCase(get()) }
    single<GetFavouriteCatListUseCase> { provideGetFavouriteCatListUseCase(get()) }
}


fun provideGetCatListUseCase(repository: CatListRepository) = GetCatListUseCase(repository)

fun provideAddFavouriteUseCase(repository: CatListRepository) = AddFavouriteCatUseCase(repository)

fun provideRemoveFavouriteUseCase(repository: CatListRepository) =
    RemoveFavouriteCatUseCase(repository)

fun provideGetFavouriteCatListUseCase(repository: CatListRepository) =
    GetFavouriteCatListUseCase(repository)

fun provideCatListApiService(retrofit: Retrofit): CatListApiService =
    retrofit.create(CatListApiService::class.java)


fun provideRemoteCatListDataSource(apiService: CatListApiService): RemoteCatListDataSourceImpl =
    RemoteCatListDataSourceImpl(apiService)


fun provideCatListRepository(
    remoteCatListDataSource: RemoteCatListDataSource,
    localCatListDataSource: LocalCatListDataSource
): CatListRepositoryImpl =
    CatListRepositoryImpl(remoteCatListDataSource, localCatListDataSource)