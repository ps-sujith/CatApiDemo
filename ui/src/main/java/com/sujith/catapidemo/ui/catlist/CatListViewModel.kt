package com.sujith.catapidemo.ui.catlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujith.catapidemo.domain.usecase.GetCatListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CatListViewModel(private val getCatListUseCase: GetCatListUseCase) : ViewModel() {

    private val _catListUiState = MutableStateFlow(CatListUiState())
    val catListUiState = _catListUiState.onStart {
        getCatList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CatListUiState(true)
    )

    private suspend fun getCatList() {
        getCatListUseCase.getCatList().collect { result ->
            println("SPS_ list  vm collectiom")
            _catListUiState.update {
                it.copy(
                    isLoading = false, catList = result.getOrDefault(
                        emptyList()
                    )
                )
            }
        }
    }

}




