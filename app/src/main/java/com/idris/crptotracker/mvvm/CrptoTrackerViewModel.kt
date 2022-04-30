package com.idris.crptotracker.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idris.crptotracker.data.CurrentPriceData
import com.idris.crptotracker.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Idris Khozema on 30/04/2022.
 */

@HiltViewModel
class CrptoTrackerViewModel @Inject constructor(
    private val repository: CrptoTrackerRepository
) :ViewModel() {


    private var showProgress: Boolean = true
    private val _mutableData = MutableLiveData<DataState<CurrentPriceData>>()
    val liveData: LiveData<DataState<CurrentPriceData>>
        get() = _mutableData


    fun fetchPrice(showProgress: Boolean) {
        viewModelScope.launch {
            fetchPrices {
                repository.fetchPrices(showProgress)
                    .flowOn(Dispatchers.IO)
            }
        }
    }

    private fun fetchPrices(block: suspend () -> Flow<DataState<CurrentPriceData>>) {
        viewModelScope.launch {
            try {
                val data = block()
                data.map {
                    _mutableData.value = it
                }.launchIn(viewModelScope)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelScope.cancel()
    }
}