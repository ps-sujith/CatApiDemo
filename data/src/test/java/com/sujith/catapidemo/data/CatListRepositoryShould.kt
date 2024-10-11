package com.sujith.catapidemo.data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.catapidemo.data.dataSource.RemoteCatListDataSource
import com.sujith.catapidemo.data.dto.BreedDto
import com.sujith.catapidemo.data.dto.CatListItemDto
import com.sujith.catapidemo.data.repository.CatListRepositoryImpl
import com.sujith.catapidemo.domain.model.CatListItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CatListRepositoryShould {
    private lateinit var catListRepository: CatListRepositoryImpl
    private val catListRemoteDataSource: RemoteCatListDataSource = mock()
    private val catList: List<CatListItem> = mock()
    private val exception = RuntimeException("Something went wrong")

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun `datasource  should at least invoked once when repository invoked `() = runTest {
        mockSuccessfulCase()
        catListRepository.getCatListWithBreed()
        verify(catListRemoteDataSource, times(1)).getCatListWithBreed()
    }


    @Test
    fun `emit  success result with non empty list  when  received from repository`() = runTest {
        mockSuccessfulCase()
        catListRepository.getCatListWithBreed().test {
            assertEquals(1, awaitItem().getOrNull()!!.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit error result  when empty result received from data source`() = runTest {
        mockFailureCase()
        catListRepository.getCatListWithBreed().test {
            assertEquals(exception.message, awaitItem().exceptionOrNull()!!.message)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private suspend fun mockSuccessfulCase() {
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
        whenever(catListRemoteDataSource.getCatListWithBreed()).thenReturn(dummyList)
        catListRepository = CatListRepositoryImpl(catListRemoteDataSource)
    }

    private suspend fun mockFailureCase() {
        whenever(catListRemoteDataSource.getCatListWithBreed()).thenReturn(emptyList())
        catListRepository = CatListRepositoryImpl(catListRemoteDataSource)
    }
}