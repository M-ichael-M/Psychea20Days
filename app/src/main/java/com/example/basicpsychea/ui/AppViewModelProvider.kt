package com.example.basicpsychea.ui

import android.app.Application
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.basicpsychea.ui.screens.CwiczeniaViewModel
import com.example.basicpsychea.ui.screens.NawykiViewModel
import com.example.basicpsychea.ui.screens.WiedzaViewModel

object AppViewModelProvider {

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


