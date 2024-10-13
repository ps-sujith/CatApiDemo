package com.sujith.catapidemo.ui.fetaure_favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sujith.catapidemo.domain.model.CatListItem
import com.sujith.catapidemo.domain.usecase.AddFavouriteCatUseCase
import com.sujith.catapidemo.domain.usecase.GetFavouriteCatListUseCase
import com.sujith.catapidemo.domain.usecase.RemoveFavouriteCatUseCase
import com.sujith.catapidemo.ui.feature_catList.component.FavListUiState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavouriteViewModel(
    private val addFavouriteCatUseCase: AddFavouriteCatUseCase,
    private val removeFavouriteCatUseCase: RemoveFavouriteCatUseCase,
    private val getFavouriteCatListUseCase: GetFavouriteCatListUseCase
) : ViewModel() {
    private val _favListUiState = MutableStateFlow(FavListUiState())
    val favListUiState = _favListUiState.asStateFlow()

    init {
        getFavouriteCatList()
    }

    private fun getFavouriteCatList() {
        viewModelScope.launch(IO) {
            _favListUiState.update {
                it.copy(
                    isLoading = true
                )
            }

            getFavouriteCatListUseCase.getFavouriteCatList().collectLatest { result ->
                _favListUiState.update {
                    it.copy(
                        isLoading = false, favList = result
                    )
                }
            }
        }
    }

    fun addFavouriteCat(catListItem: CatListItem) {
        viewModelScope.launch(IO) {
            addFavouriteCatUseCase.addFavourite(catListItem)
        }
    }

    fun removeFavouriteCat(catListItem: CatListItem) {
        viewModelScope.launch(IO) {
            removeFavouriteCatUseCase.removeFavourite(catListItem)
        }
    }

}