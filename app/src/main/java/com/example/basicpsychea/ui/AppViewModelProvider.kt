package com.example.basicpsychea.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.basicpsychea.ui.screens.CiekawostkiViewModel
import com.example.basicpsychea.ui.screens.CwiczeniaViewModel
import com.example.basicpsychea.ui.screens.NawykiViewModel
import com.example.basicpsychea.ui.screens.WiedzaViewModel

object AppViewModelProvider{
    val Factory = viewModelFactory {
        initializer {
            CwiczeniaViewModel()
        }
        initializer {
            CiekawostkiViewModel()
        }
        initializer {
            NawykiViewModel()
        }
        initializer {
            WiedzaViewModel()
        }
    }
}
