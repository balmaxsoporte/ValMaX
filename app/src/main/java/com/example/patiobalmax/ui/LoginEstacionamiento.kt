package com.example.patiobalmax.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.patiobalmax.databinding.ActivityLoginEstacionamientoBinding
import com.example.patiobalmax.database.DatabaseProvider
import com.example.patiobalmax.model.Usuario

class LoginEstacionamiento : AppCompatActivity() {

    private lateinit var binding: ActivityLoginEstacionamientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginEstacionamientoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupInitialData()

        binding.buttonLogin.setOnClickListener {
            val username = binding.editTextUsername.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (validateUser(username, password)) {
                val user = DatabaseProvider.usuarioDao().getUserByUsername(username)
                if (user != null && user.contrasena == password) {
                    // Inicio de sesión exitoso
                    startActivity(Intent(this, MapaEstacionamiento::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingrese nombre de usuario y contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textViewForgotPassword.setOnClickListener {
            // Aquí puedes implementar funcionalidad para recuperar contraseña.
            Toast.makeText(this, "Función no implementada aún.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateUser(usuario: String, contrasena: String): Boolean {
        return usuario.isNotBlank() && contrasena.isNotBlank()
    }

    private fun setupInitialData() {
        val userDao = DatabaseProvider.usuarioDao()
        if (userDao.getUserCount() == 0) {
            userDao.insert(Usuario("admin", "admin", "Administrador"))
            userDao.insert(Usuario("user1", "12345", "Editar Patentes de Patios"))
            userDao.insert(Usuario("user2", "12345", "Validar Patios y Puestos"))
        }
    }
}
