package com.sujith.catapidemo.ui.di

import com.sujith.catapidemo.ui.feature_catList.CatListViewModel
import com.sujith.catapidemo.ui.fetaure_favourite.FavouriteViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {
    viewModel { CatListViewModel(get()) }
    viewModel { FavouriteViewModel(get(), get(), get()) }
}