package com.example.stunningweather.cache

import android.content.Context
import androidx.room.*

@Database(entities = [(CacheWeatherData::class)], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDataDatabase: RoomDatabase() {

    abstract fun weatherDataDao(): WeatherDataDao

    companion object {
        private var INSTANCE: WeatherDataDatabase? = null

        fun getInstance(context: Context): WeatherDataDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDataDatabase::class.java,
                        "user_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}