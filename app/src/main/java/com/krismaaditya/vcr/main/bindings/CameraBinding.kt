package com.krismaaditya.vcr.main.bindings

import com.krismaaditya.vcr.main.presentation.camera.CameraScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val cameraBinding = module {
    viewModel {
        CameraScreenViewModel(get())
    }
}