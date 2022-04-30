package com.idris.crptotracker.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Intent
import com.idris.crptotracker.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@AndroidEntryPoint
class TestJobService : JobService() {


    override fun onStartJob(params: JobParameters): Boolean {

        Utils.fetchData(applicationContext) // reschedule the job
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        return true
    }

    companion object {
        private const val TAG = "SyncService"
    }
}