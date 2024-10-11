package com.sujith.catapidemo

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.usecase.GetCatListUseCase
import com.sujith.catapidemo.ui.catlist.CatListUiState
import com.sujith.catapidemo.ui.catlist.CatListViewModel
import com.sujith.ui.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CatListViewModelShould {
    private lateinit var catListViewModel: CatListViewModel
    private var getCatListUseCase: GetCatListUseCase = mock()
    private val catList: List<CatListItem> = mock()
    private val exceptedResult = CatListUiState(false, catList)
    private val error = CatListUiState()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()


    @Test
    fun `use case should at least invoked once when viewmodel launched `() = runTest {
        mockSuccessfulCase()
        catListViewModel.catListUiState.test {
            awaitItem()
            verify(getCatListUseCase, times(1)).getCatList()
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `emit  state with cat list  when success received from use case`() = runTest {
        mockSuccessfulCase()
        catListViewModel.catListUiState.test {
            assertEquals(exceptedResult, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun ` emit Loading state when viewmodel launched `() = runTest {
        mockSuccessfulCase()
        assertEquals(true, catListViewModel.catListUiState.value.isLoading)
    }

    @Test
    fun `emit state with empty list  when error received from use case`() = runTest {
        mockFailureCase()
        catListViewModel.catListUiState.test {
            assertEquals(error, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    private suspend fun mockSuccessfulCase() {
        whenever(getCatListUseCase.getCatList()).thenReturn(flow { emit(Result.success(catList)) })
        catListViewModel = CatListViewModel(getCatListUseCase)
    }

    private suspend fun mockFailureCase() {
        whenever(getCatListUseCase.getCatList()).thenReturn(
            flow { emit(Result.failure(RuntimeException("something went wrong"))) }
        )
        catListViewModel = CatListViewModel(getCatListUseCase)
    }
}