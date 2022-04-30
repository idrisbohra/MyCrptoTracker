package com.idris.crptotracker.utils

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Idris Khozema on 30/04/2022.
 */

@HiltAndroidApp
class IdrisCrptoTracker : Application() {

    var appLifecycleObserver: AppLifeCycleObserver? = null

    override fun onCreate() {
        super.onCreate()
        initLifeCycleObserver()
    }

    fun initLifeCycleObserver() {
        appLifecycleObserver = AppLifeCycleObserver()
        ProcessLifecycleOwner.get().lifecycle.addObserver(appLifecycleObserver!!)
    }

}