package com.idris.crptotracker.network

import com.idris.crptotracker.data.CurrentPriceData
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@Singleton
class ApiServiceHelperImplementation @Inject constructor(
    @Named("APIServiceJSON") private val apiServiceJSON: RetrofitServiceInstance
) : ApiServiceHelper {


    override suspend fun fetchCurrentPrice(): Response<CurrentPriceData> = apiServiceJSON.loadCurrentPrice()

}