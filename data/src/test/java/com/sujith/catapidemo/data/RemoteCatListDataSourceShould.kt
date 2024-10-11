package com.sujith.catapidemo.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.catapidemo.data.api.CatListApiService
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSource
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSourceImpl
import com.sujith.catapidemo.data.dto.CatListItemDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

class RemoteCatListDataSourceShould {
    private lateinit var remoteDataSource: RemoteCatListDataSource
    private var catListApiService: CatListApiService = mock()
    private val expectedSuccess: List<CatListItemDto> = mock()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun `fetch all the character list from api service`() = runTest {
        mockSuccessfulCase()
        remoteDataSource.getCatListWithBreed()
        verify(catListApiService, times(1)).getCatListWithBreed()
    }

    @Test
    fun `return character list when when api is success`() = runTest {
        mockSuccessfulCase()
        assertEquals(expectedSuccess, remoteDataSource.getCatListWithBreed())
    }

    @Test
    fun `return exception  when api fails `() = runTest {
        mockFailureCase()
        assertEquals(emptyList<CatListItemDto>(), remoteDataSource.getCatListWithBreed())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(catListApiService.getCatListWithBreed()).thenReturn(expectedSuccess)
        remoteDataSource = RemoteCatListDataSourceImpl(catListApiService)
    }

    private suspend fun mockFailureCase() {
        whenever(catListApiService.getCatListWithBreed()).thenReturn(null)
        remoteDataSource = RemoteCatListDataSourceImpl(catListApiService)
    }
}