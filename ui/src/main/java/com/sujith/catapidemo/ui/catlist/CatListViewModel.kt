package com.sujith.catapidemo.ui.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujith.catapidemo.domain.usecase.GetCatListUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class CatListViewModel(private val getCatListUseCase: GetCatListUseCase) : ViewModel() {
    val catListUiState = flow {
        getCatListUseCase.getCatList().collect { result ->
            emit(
                CatListUiState(
                    isLoading = false, catList = result.getOrDefault(
                        emptyList()
                    )
                )
            )

        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        initialValue = CatListUiState(isLoading = true)
    )
}



