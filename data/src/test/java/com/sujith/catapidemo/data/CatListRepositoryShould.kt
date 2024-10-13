package com.sujith.catapidemo.data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.catapidemo.data.dataSource.LocalCatListDataSource
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSource
import com.sujith.catapidemo.data.dto.BreedDto
import com.sujith.catapidemo.data.dto.CatListItemDto
import com.sujith.catapidemo.data.repository.CatListRepositoryImpl
import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CatListRepositoryShould {
    private lateinit var catListRepository: CatListRepositoryImpl
    private val remoteCatListDataSource: RemoteCatListDataSource = mock()
    private val localCatListDataSource: LocalCatListDataSource = mock()
    private val catList: List<CatListItem> = mock()
    private val exception = RuntimeException("Something went wrong")

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun ` remote datasource  should at least invoked once when repository invoked `() = runTest {
        mockRemoteSuccessfulCase()
        catListRepository.getCatListWithBreed()
        verify(remoteCatListDataSource, times(1)).getCatListWithBreed()
    }


    @Test
    fun `emit  success result with non empty list  when  received from  remote data source`() =
        runTest {
            mockRemoteSuccessfulCase()
            catListRepository.getCatListWithBreed().test {
                assertEquals(1, awaitItem().getOrNull()!!.size)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `emit error result  when empty result received from  remote data source`() = runTest {
        mockFailureCase()
        catListRepository.getCatListWithBreed().test {
            assertEquals(exception.message, awaitItem().exceptionOrNull()!!.message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun ` local datasource  should at least invoked once when repository invoked `() = runTest {
        mockRemoteSuccessfulCase()
        catListRepository.getFavouriteCatList()
        verify(localCatListDataSource, times(1)).getFavouriteCatList()
    }


    @Test
    fun `emit  non empty list  when  received from  local data source`() = runTest {
        mockLocalSuccessfulCase()
        catListRepository.getFavouriteCatList().test {
            assertEquals(catList, awaitItem().first())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit empty list  when empty result received from  remote data source`() = runTest {
        mockLocalFailureCase()
        catListRepository.getFavouriteCatList().test {
            assertEquals(emptyList<CatListItem>(), awaitItem().first())
            cancelAndIgnoreRemainingEvents()
        }
    }

    private suspend fun mockRemoteSuccessfulCase() {
        val dummyList = listOf(
            CatListItemDto(
                listOf(
                    BreedDto(
                        0, 0, 0, "",
                        0, 0, 0, "15", "arabian", ""
                    )
                ), "1", "https://empty"
            )
        )
        whenever(remoteCatListDataSource.getCatListWithBreed()).thenReturn(dummyList)
        catListRepository = CatListRepositoryImpl(remoteCatListDataSource, localCatListDataSource)
    }

    private suspend fun mockFailureCase() {
        whenever(remoteCatListDataSource.getCatListWithBreed()).thenReturn(emptyList())
        catListRepository = CatListRepositoryImpl(remoteCatListDataSource, localCatListDataSource)
    }

    private suspend fun mockLocalSuccessfulCase() {
        whenever(localCatListDataSource.getFavouriteCatList()).thenReturn(flow { catList })
        catListRepository = CatListRepositoryImpl(remoteCatListDataSource, localCatListDataSource)
    }

    private suspend fun mockLocalFailureCase() {
        whenever(localCatListDataSource.getFavouriteCatList()).thenReturn(flow { emptyList<CatListItem>() })
        catListRepository = CatListRepositoryImpl(remoteCatListDataSource, localCatListDataSource)
    }
}