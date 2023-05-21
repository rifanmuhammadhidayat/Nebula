package org.d3if3084.nebula.findzodiac

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3084.nebula.db.ZodiacDao

class FindZodiacViewModelFactory(private val db: ZodiacDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FindZodiacViewModel::class.java)) {
            return FindZodiacViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}