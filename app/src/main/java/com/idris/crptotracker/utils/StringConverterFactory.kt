package com.idris.crptotracker.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit

/**
 * Created by Idris Khozema on 30/04/2022.
 */
class StringConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: java.lang.reflect.Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return null
    }

    companion object {
        fun create(): StringConverterFactory {
            return StringConverterFactory()
        }
    }
}