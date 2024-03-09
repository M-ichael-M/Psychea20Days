package com.example.basicpsychea.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.basicpsychea.PsycheaApplication
import com.example.basicpsychea.ui.screens.CwiczeniaViewModel
import com.example.basicpsychea.ui.screens.NawykiViewModel
import com.example.basicpsychea.ui.screens.WiedzaViewModel
import retrofit2.Converter.Factory

object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            CwiczeniaViewModel()
        }
        initializer {
            CwiczeniaViewModel()
        }
        initializer {
            NawykiViewModel()
        }
        initializer {
            WiedzaViewModel()
        }
    }
}

fun CreationExtras.psycheaApplication(): PsycheaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as PsycheaApplication)