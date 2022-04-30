package com.idris.crptotracker.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Idris Khozema on 30/04/2022.
 */

@HiltAndroidApp
class IdrisCrptoTracker : Application() {

    override fun onCreate() { super.onCreate() }
}