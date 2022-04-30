package com.idris.crptotracker.network

import com.idris.crptotracker.data.CurrentPriceData
import retrofit2.Response
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@Singleton
interface ApiServiceHelper {

    suspend fun fetchCurrentPrice() : Response<CurrentPriceData>
}