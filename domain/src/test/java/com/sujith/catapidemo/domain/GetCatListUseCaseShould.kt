package com.sujith.catapidemo.domain

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.repository.CatListRepository
import com.sujith.catapidemo.domain.usecase.GetCatListUseCase
import com.sujith.ui.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class GetCatListUseCaseShould {
    private lateinit var getCatListUseCase: GetCatListUseCase
    private val catListRepository: CatListRepository = mock()
    private val catList: List<CatListItem> = mock()
    private val exceptedResult = Result.success(catList)
    private val exception = RuntimeException("Something went wrong")

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()

    @Test
    fun `repository  should at least invoked once when use case invoked `() = runTest {
        mockSuccessfulCase()
        getCatListUseCase.getCatList().test {
            awaitItem()
            verify(catListRepository, times(1)).getCatListWithBreed()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit  cat list  when success received from repository`() = runTest {
        mockSuccessfulCase()
        getCatListUseCase.getCatList().test {
            assertEquals(exceptedResult, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit exception when error received from repository`() = runTest {
        mockFailureCase()
        getCatListUseCase.getCatList().test {
            assertEquals(exception.message, awaitItem().exceptionOrNull())
            cancelAndIgnoreRemainingEvents()
        }
    }

    private suspend fun mockSuccessfulCase() {
        whenever(catListRepository.getCatListWithBreed()).thenReturn(flow {
            emit(
                Result.success(
                    catList
                )
            )
        })
        getCatListUseCase = GetCatListUseCase(catListRepository)
    }

    private suspend fun mockFailureCase() {
        whenever(catListRepository.getCatListWithBreed()).thenReturn(
            flow { emit(Result.failure(exception)) }
        )
        getCatListUseCase = GetCatListUseCase(catListRepository)
    }

}