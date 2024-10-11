package com.sujith.catapidemo.ui.catlist

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val catListModule = module {
    viewModel<CatListViewModel> { get() }
}