package com.example.patiobalmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.patiobalmax.model.Usuario

@Database(entities = [Usuario::class], version = 1, exportSchema = false)
abstract class ParkingDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: ParkingDatabase? = null

        fun getDatabase(context: Context): ParkingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParkingDatabase::class.java,
                    "patiobalmax-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
