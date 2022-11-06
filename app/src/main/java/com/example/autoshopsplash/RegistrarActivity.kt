package com.example.autoshopsplash

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.example.autoshopsplash.databinding.ActivityLoginBinding
import com.example.autoshopsplash.databinding.ActivityRegistrarBinding

class RegistrarActivity : Activity() {
    lateinit var binding: ActivityRegistrarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registro.setOnClickListener { guardarUsuario() }
    }

    fun guardarUsuario() {
        val nombre: String = binding.nombre.text.toString()
        val apellido: String = binding.apellidos.text.toString()
        val documento: String = binding.documento.text.toString()
        val correo: String = binding.correo.text.toString()
        val direccion: String = binding.direccion.text.toString()
        val telefono: String = binding.telefono.text.toString()
        val password: String = binding.password.text.toString()
        var pref = getSharedPreferences(correo, Context.MODE_PRIVATE)
        var editar = pref.edit()
        editar.putString("email", correo)
        editar.putString("nombre", nombre)
        editar.putString("apellidos", apellido)
        editar.putString("documento", documento)
        editar.putString("direccion", direccion)
        editar.putString("telefono", telefono)
        editar.putString("password", password)


        if (correo.isEmpty()) {
            binding.correo.setHint("Campo vacio")
            binding.correo.setHintTextColor(Color.RED)
        } else if (nombre.isEmpty()) {
            binding.nombre.setHint("Campo vacio")
            binding.nombre.setHintTextColor(Color.RED)
        } else if (apellido.isEmpty()) {
            binding.apellidos.setHint("Campo vacio")
            binding.apellidos.setHintTextColor(Color.RED)
        } else if (documento.isEmpty()) {
            binding.documento.setHint("Campo vacio")
            binding.documento.setHintTextColor(Color.RED)
        } else if (direccion.isEmpty()) {
            binding.direccion.setHint("Campo vacio")
            binding.direccion.setHintTextColor(Color.RED)
        } else if (telefono.isEmpty()) {
            binding.telefono.setHint("Campo vacio")
            binding.telefono.setHintTextColor(Color.RED)
        } else if (password.isEmpty()) {
            binding.password.setHint("Campo vacio")
            binding.password.setHintTextColor(Color.RED)
        }else{
            editar.commit()
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
        }
    }
}