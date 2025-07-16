package com.example.patiobalmax.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val contrasena: String,
    val permisos: String
) {
    companion object {
        fun isAdmin(usuario: String): Boolean {
            return usuario == "admin"
        }
    }
}
