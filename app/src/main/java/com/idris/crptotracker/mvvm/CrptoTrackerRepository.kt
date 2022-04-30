package com.idris.crptotracker.mvvm

import com.idris.crptotracker.utils.IdrisCrptoTracker
import com.idris.crptotracker.R
import com.idris.crptotracker.data.CurrentPriceData
import com.idris.crptotracker.network.ApiServiceHelperImplementation
import com.idris.crptotracker.utils.DataState
import com.idris.crptotracker.utils.NetworkHelper
import java.lang.Exception
import java.net.HttpURLConnection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@Singleton
class CrptoTrackerRepository @Inject constructor(
    private val context: IdrisCrptoTracker,
    private val networkHelper: NetworkHelper,
    private val apiServiceHelper: ApiServiceHelperImplementation
){

    suspend fun fetchPrices(showProgress:Boolean) : Flow<DataState<CurrentPriceData>> = flow{

        try {

            if (!networkHelper.hasActiveInternetConnection()) {
                emit(DataState.NetworkError(context.getString(R.string.timeout_error)))
                return@flow
            }

            //Initiate
            if(showProgress)
            emit(DataState.Loading)
            val response = apiServiceHelper.fetchCurrentPrice()

            when(response.code()) {
                HttpURLConnection.HTTP_OK -> { emit(DataState.Success(response.body())) }

                HttpURLConnection.HTTP_INTERNAL_ERROR -> emit(DataState.Error(context.getString(R.string.generic_error)))
            }
        }
        catch (e: Exception)
        {
            emit(DataState.Error(context.getString(R.string.generic_error)))
            e.printStackTrace()
        }
    }
}