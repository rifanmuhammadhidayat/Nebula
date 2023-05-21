package org.d3if3084.nebula.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3084.nebula.db.ZodiacDao

class HistoryViewModel(private val db: ZodiacDao) : ViewModel() {
    val data = db.getLastZodiac()

    fun deleteData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}