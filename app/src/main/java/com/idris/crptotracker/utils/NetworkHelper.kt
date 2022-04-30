package com.idris.crptotracker.utils

import androidx.multidex.BuildConfig
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 30/04/2022.
 */
@Singleton
class NetworkHelper @Inject constructor(
    private val context: IdrisCrptoTracker
) {

    fun hasActiveInternetConnection(): Boolean {
        return try {
            val sock = Socket()
            val sockaddr = InetSocketAddress("8.8.8.8", 53)

            sock.connect(sockaddr, AppConstants.CONNECT_TIMEOUT)
            sock.close()

            true
        } catch (e: IOException) {
            false
        }
    }

    fun isConnectedToLocalNetwork(): Boolean {
        if (!BuildConfig.DEBUG) return false
        return try {
            val sock = Socket()
            val sockaddr = InetSocketAddress("10.12.244.42", 8080)

            sock.connect(sockaddr, AppConstants.CONNECT_TIMEOUT)
            sock.close()

            true
        } catch (e: IOException) {
            false
        }
    }

}