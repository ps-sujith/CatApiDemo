package com.sujith.catapidemo.ui.feature_catList.component

import com.sujith.catapidemo.domain.model.CatListItem

data class CatListUiState(
    val isLoading: Boolean = false,
    val catList: List<CatListItem> = emptyList()
)