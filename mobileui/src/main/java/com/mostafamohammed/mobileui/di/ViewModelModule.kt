package com.mostafamohammed.mobileui.di

import com.mostafamohammed.presentation.viewModel.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeViewModel(get(),get())
    }
}