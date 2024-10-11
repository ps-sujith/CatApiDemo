package com.sujith.catapidemo.ui.catlist.di

import com.sujith.catapidemo.ui.catlist.CatListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val uiModule = module {
    viewModel { CatListViewModel(get()) }
}