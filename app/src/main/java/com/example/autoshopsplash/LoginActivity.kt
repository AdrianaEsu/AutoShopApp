package com.example.autoshopsplash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.example.autoshopsplash.databinding.ActivityLoginBinding

class LoginActivity: Activity(){

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ingresar.setOnClickListener{validar()}
        binding.registrar.setOnClickListener{enlace_registro()}
        binding.restablecer.setOnClickListener{enlace_restablecer()}
    }
    fun validar(){
        val correo:String=binding.email.text.toString()
        val password:String=binding.pass.text.toString()
        var pref=getSharedPreferences(correo, Context.MODE_PRIVATE)
        var email_bd=pref.getString("email","")
        var pass_bd=pref.getString("password","")
        var nombre_bd=pref.getString("nombre","")
        var apellido_bd=pref.getString("apellidos","")
        if(correo.isEmpty()){
            binding.email.setHint("Campo vacio")
            binding.email.setHintTextColor(Color.RED)
        }else if(password.isEmpty()){
            binding.pass.setHint("Campo vacio")
            binding.pass.setHintTextColor(Color.RED)
        }else if (correo==email_bd)
            if (password==pass_bd){
                //Toast.makeText(this,"$nombre_bd $apellido_bd BIENVENIDO A AUTOSHOP ",Toast.LENGTH_LONG).show()
                var intent=Intent(this, HomeActivity::class.java)
                intent.putExtra("email",email_bd)
                startActivity(intent)
            }else{
                Toast.makeText(this,"contraseña incorrecta",Toast.LENGTH_LONG).show()
            }else{
            Toast.makeText(this,"Usuario incorrecto o no exixte",Toast.LENGTH_LONG).show()
        }
    }

    fun enlace_registro(){
        startActivity(Intent(this, RegistrarActivity::class.java))
    }

    fun enlace_restablecer(){
        startActivity(Intent(this, RestablecerActivity::class.java))
    }


}