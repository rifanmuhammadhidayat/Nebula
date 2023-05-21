package org.d3if3084.nebula.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZodiacDao {
    @Insert
    fun insert(zodiac: ZodiacEntity)

    @Query("SELECT * FROM zodiac ORDER BY id DESC")
    fun getLastZodiac(): LiveData<List<ZodiacEntity>>

    @Query("DELETE FROM zodiac")
    fun clearData()

    @Query("DELETE FROM zodiac WHERE id = :id")
    fun clearDataZodiac(id: Long)
}