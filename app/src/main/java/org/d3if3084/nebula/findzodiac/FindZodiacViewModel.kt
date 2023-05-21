package org.d3if3084.nebula.findzodiac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3084.nebula.db.ZodiacDao
import org.d3if3084.nebula.db.ZodiacEntity
import org.d3if3084.nebula.model.ZodiacSign
import org.d3if3084.nebula.model.ZodiacType
import org.d3if3084.nebula.model.getZodiacSign

class FindZodiacViewModel(private val db: ZodiacDao) : ViewModel() {
    private val zodiacResult = MutableLiveData<ZodiacType?>()
    private val navigation = MutableLiveData<ZodiacSign?>()

    fun setZodiacResults(zodiacType: ZodiacType, date: Int, month: Int) {
        val zodiacData = ZodiacEntity(
            date = date,
            month = month
        )
        zodiacResult.value = zodiacData.getZodiacSign()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(zodiacData)
            }
        }
    }


    fun startNavigation() {
        navigation.value = zodiacResult.value?.zodiacSign
    }

    fun finishNavigation() {
        navigation.value = null
    }

    fun getNavigation(): LiveData<ZodiacSign?> = navigation

    fun getZodiacSign(date: Int, month: Int): ZodiacType {
        val zodiacType = ZodiacType(ZodiacSign.INVALID, "")
        return zodiacType
    }

    fun getZodiacResults(): LiveData<ZodiacType?> = zodiacResult
}