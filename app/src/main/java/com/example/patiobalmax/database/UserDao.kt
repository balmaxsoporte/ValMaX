package com.example.patiobalmax.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.patiobalmax.model.Usuario

@Dao
interface UserDao {

    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE nombre = :nombre")
    suspend fun getUserByUsername(nombre: String): Usuario?

    @Query("SELECT COUNT(*) FROM usuarios")
    suspend fun getUserCount(): Int

    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsers(): List<Usuario>
}
