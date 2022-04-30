package com.idris.crptotracker.network

import com.idris.crptotracker.data.CurrentPriceData
import com.idris.crptotracker.utils.AppConstants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by Idris Khozema on 30/04/2022.
 */
interface RetrofitServiceInstance {

    //@Headers("Content-Type: application/json")
    @GET("v1/bpi/currentprice.json")
    suspend fun loadCurrentPrice(): Response<CurrentPriceData>
}