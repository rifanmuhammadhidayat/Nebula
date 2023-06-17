package org.d3if3084.nebula.ui.zodiak

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3084.nebula.model.Zodiak
import org.d3if3084.nebula.network.ApiStatus
import org.d3if3084.nebula.network.ServiceAPI
import org.d3if3084.nebula.network.UpdateWorker
import java.util.concurrent.TimeUnit

class ZodiakViewModel : ViewModel() {
    private val data = MutableLiveData<List<Zodiak>>()
    private val status = MutableLiveData<ApiStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.postValue(ApiStatus.LOADING)
            try {
                data.postValue(ServiceAPI.zodiakService.getSuku())
                status.postValue(ApiStatus.SUCCES)
            } catch (e: Exception) {
                Log.d("SukuViewModel", "Failure: ${e.message}")
                status.postValue(ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<Zodiak>> = data

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    fun getStatus(): LiveData<ApiStatus> = status
}