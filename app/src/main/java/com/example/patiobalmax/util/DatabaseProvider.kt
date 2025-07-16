package com.example.patiobalmax.util

import android.content.Context
import com.example.patiobalmax.database.ParkingDatabase
import com.example.patiobalmax.database.UserDao

object DatabaseProvider {

    private var database: ParkingDatabase? = null

    fun getDatabase(context: Context): ParkingDatabase {
        if (database == null) {
            synchronized(ParkingDatabase::class.java) {
                if (database == null) {
                    database = ParkingDatabase.getDatabase(context)
                }
            }
        }
        return database!!
    }

    fun userDao(): UserDao {
        return getDatabase(App.context).userDao()
    }
}
