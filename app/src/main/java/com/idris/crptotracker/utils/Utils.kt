package com.idris.crptotracker.utils

import android.app.job.JobScheduler
import android.app.job.JobInfo
import android.content.ComponentName
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.idris.crptotracker.service.TestJobService
import com.idris.crptotracker.ui.fragments.CrptoTrackListener

/**
 * Created by Idris Khozema on 30/04/2022.
 */
public class Utils {
    companion object {

        var mCrptoTrackListener: CrptoTrackListener?=null
        var isAppOnForeground: Boolean = true

        // schedule the start of the service every 10 - 30 seconds
        fun scheduleJob(context: Context, crptoTrackListener: CrptoTrackListener?) {
            mCrptoTrackListener = crptoTrackListener
            val serviceComponent = ComponentName(context, TestJobService::class.java)
            val builder = JobInfo.Builder(0, serviceComponent)
            builder.setMinimumLatency((2 * 1000).toLong()) // wait at least
            builder.setOverrideDeadline((5 * 1000).toLong()) // maximum delay
            //builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED); // require unmetered network
            //builder.setRequiresDeviceIdle(true); // device should be idle
            //builder.setRequiresCharging(false); // we don't care if the device is charging or not
            val jobScheduler: JobScheduler = context.getSystemService(JobScheduler::class.java)
            jobScheduler.schedule(builder.build())
        }

        fun fetchData(applicationContext: Context) {

            //Log.e("TAG", "fetchData: working")
            //Toast.makeText(applicationContext, "Yo working", Toast.LENGTH_SHORT).show()
            mCrptoTrackListener?.fetchData()
        }

    }
}