package org.d3if3084.nebula.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ZodiacEntity::class], version = 1, exportSchema = false)
abstract class ZodiacDb : RoomDatabase() {
    abstract val dao: ZodiacDao

    companion object {
        @Volatile
        private var INSTANCE: ZodiacDb? = null
        fun getInstance(context: Context): ZodiacDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ZodiacDb::class.java,
                        "zodiac.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}