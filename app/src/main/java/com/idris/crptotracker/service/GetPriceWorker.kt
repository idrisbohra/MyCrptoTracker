package com.idris.crptotracker.service

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.idris.crptotracker.utils.SharedPreferenceUtils
import com.idris.crptotracker.utils.Utils
import android.os.Build




class GetPriceWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        if(
            SharedPreferenceUtils.getFloatPreference(applicationContext, SharedPreferenceUtils.PREF_MIN_RATE)!=0.0f ||
            SharedPreferenceUtils.getFloatPreference(applicationContext, SharedPreferenceUtils.PREF_MAX_RATE)!=0.0f )
        {
            if(SharedPreferenceUtils.getFloatPreference(applicationContext, SharedPreferenceUtils.PREF_CURRENT_RATE) <
                SharedPreferenceUtils.getFloatPreference(applicationContext, SharedPreferenceUtils.PREF_MIN_RATE))
                if(!Utils.isAppOnForeground){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        applicationContext.startForegroundService(Intent(applicationContext, NotificationService::class.java))
                    } else {
                        applicationContext.startService(Intent(applicationContext, NotificationService::class.java))
                    }
                }
            else if(SharedPreferenceUtils.getFloatPreference(applicationContext, SharedPreferenceUtils.PREF_CURRENT_RATE) >
                    SharedPreferenceUtils.getFloatPreference(applicationContext, SharedPreferenceUtils.PREF_MAX_RATE))
                    if(!Utils.isAppOnForeground){
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            applicationContext.startForegroundService(Intent(applicationContext, NotificationService::class.java))
                        } else {
                            applicationContext.startService(Intent(applicationContext, NotificationService::class.java))
                        }
                    }
        }
        Utils.fetchData(applicationContext)
        return Result.success()
    }

}