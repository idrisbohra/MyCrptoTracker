package com.idris.crptotracker.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@Parcelize
data class CurrentPriceData(
    @SerializedName("bpi")
    @Expose
    val bpi: Bpi,
    @SerializedName("chartName")
    @Expose
    val chartName: String,
    @SerializedName("disclaimer")
    @Expose
    val disclaimer: String,
    @SerializedName("time")
    @Expose
    val time: Time
): Parcelable {
    @Parcelize
    data class Bpi(
        @SerializedName("EUR")
        @Expose
        val eUR: EUR,
        @SerializedName("GBP")
        @Expose
        val gBP: GBP,
        @SerializedName("USD")
        @Expose
        val uSD: USD
    ): Parcelable {
        @Parcelize
        data class EUR(
            @SerializedName("code")
            @Expose
            val code: String,
            @SerializedName("description")
            @Expose
            val description: String,
            @SerializedName("rate")
            @Expose
            val rate: String,
            @SerializedName("rate_float")
            @Expose
            val rateFloat: Double,
            @SerializedName("symbol")
            @Expose
            val symbol: String
        ): Parcelable

        @Parcelize
        data class GBP(
            @SerializedName("code")
            @Expose
            val code: String,
            @SerializedName("description")
            @Expose
            val description: String,
            @SerializedName("rate")
            @Expose
            val rate: String,
            @SerializedName("rate_float")
            @Expose
            val rateFloat: Double,
            @SerializedName("symbol")
            @Expose
            val symbol: String
        ): Parcelable

        @Parcelize
        data class USD(
            @SerializedName("code")
            @Expose
            val code: String,
            @SerializedName("description")
            @Expose
            val description: String,
            @SerializedName("rate")
            @Expose
            val rate: String,
            @SerializedName("rate_float")
            @Expose
            val rateFloat: Double,
            @SerializedName("symbol")
            @Expose
            val symbol: String
        ): Parcelable
    }

    @Parcelize
    data class Time(
        @SerializedName("updated")
        @Expose
        val updated: String,
        @SerializedName("updatedISO")
        @Expose
        val updatedISO: String,
        @SerializedName("updateduk")
        @Expose
        val updateduk: String
    ): Parcelable
}