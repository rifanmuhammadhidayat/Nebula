package org.d3if3084.nebula.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "zodiac")
data class ZodiacEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var dateHistory: Long = System.currentTimeMillis(),
    var date: Int,
    var month: Int
)