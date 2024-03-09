package com.example.basicpsychea

import android.app.Application
import com.example.basicpsychea.data.AppContainer
import com.example.basicpsychea.data.AppDataContainer

class PsycheaApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}