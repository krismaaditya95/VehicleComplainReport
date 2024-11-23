package com.krismaaditya.vcr.main.bindings

import com.krismaaditya.vcr.main.presentation.dashboard.DashboardScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dashboardBinding = module {
    viewModel {
        DashboardScreenViewModel(get(), get(), get(), get())
    }
}