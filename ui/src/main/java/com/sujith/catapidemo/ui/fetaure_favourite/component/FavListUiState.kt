package com.sujith.catapidemo.ui.feature_catList.component

import com.sujith.catapidemo.domain.model.CatListItem

data class FavListUiState(
    val isLoading: Boolean = false,
    val favList: List<CatListItem> = emptyList()
)