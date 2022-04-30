package com.idris.crptotracker.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.idris.crptotracker.R
import com.idris.crptotracker.data.CurrentPriceData
import com.idris.crptotracker.mvvm.CrptoTrackerViewModel
import com.idris.crptotracker.utils.DataState
import kotlinx.android.synthetic.main.fragment_crpto_tracker.*
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.idris.crptotracker.service.GetPriceWorker
import com.idris.crptotracker.ui.activities.MainActivity
import com.idris.crptotracker.utils.SharedPreferenceUtils
import com.idris.crptotracker.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@AndroidEntryPoint
class CrptoTrackerFragment : Fragment(R.layout.fragment_crpto_tracker), CrptoTrackListener {

    internal val viewModel: CrptoTrackerViewModel by activityViewModels()
    var crptoTrackListener: CrptoTrackListener?=null
    var mShowProgress:Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindViews()
        getPrices()
        subscribeObserver()
    }


    private fun bindViews() {
        crptoTrackListener = this

        btnMinValue.setOnClickListener {
            if(etMinValue.length()!=0){
                SharedPreferenceUtils.saveFloatPreference(requireContext(), SharedPreferenceUtils.PREF_MIN_RATE, etMinValue.text.toString().toFloat())
            }else{
                Toast.makeText(requireContext(), getString(R.string.enter_value), Toast.LENGTH_SHORT).show()
            }
        }

        btnMaxValue.setOnClickListener {
            if(etMaxValue.length()!=0){
                SharedPreferenceUtils.saveFloatPreference(requireContext(), SharedPreferenceUtils.PREF_MAX_RATE, etMinValue.text.toString().toFloat())
            }else{
                Toast.makeText(requireContext(), getString(R.string.enter_value), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getPrices(showProgress: Boolean = true) {

        mShowProgress = showProgress
        if(showProgress)
        containerPrice.visibility = View.INVISIBLE
        viewModel.fetchPrice(showProgress)
    }

    private fun subscribeObserver() {

        viewModel.liveData.observe(viewLifecycleOwner,{ dataState->

            when(dataState) {

                DataState.Loading -> {
                   progressBar.visibility = View.VISIBLE

                }

                is DataState.Success -> {
                    progressBar.visibility = View.GONE
                    val response = dataState.data
                    response?.let { data->
                        processResponse(data)
                    }

                }
                is DataState.Error -> {
                    progressBar.visibility = View.GONE
                    showAlert(dataState.message)

                }
                else -> {
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

    private fun showAlert(message: String) {
        MaterialAlertDialogBuilder(activity as MainActivity)
            .setMessage(message)
            .setNegativeButton("OK") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun processResponse(data: CurrentPriceData?) {
        try{
            containerPrice.visibility = View.VISIBLE
            data?.let { data ->
                currentTime.text = "Last updated: ${data.time.updated}"
                tvPriceValueDollar.text = data.bpi.uSD.rate
                tvPriceValueEuro.text = data.bpi.eUR.rate
                SharedPreferenceUtils.saveFloatPreference(requireContext(), SharedPreferenceUtils.PREF_CURRENT_RATE, data.bpi.uSD.rate.replace(",", "").toFloat())
                if(mShowProgress){
                    val periodicWorkRequest = PeriodicWorkRequestBuilder<GetPriceWorker>(5, TimeUnit.SECONDS).build()
                    WorkManager.getInstance(requireContext()).enqueue(periodicWorkRequest)
                }
            Utils.scheduleJob(requireContext(), crptoTrackListener)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun fetchData() {
        getPrices(false)
    }


}