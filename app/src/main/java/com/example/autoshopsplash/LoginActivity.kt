package com.example.autoshopsplash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.autoshopsplash.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class LoginActivity: AppCompatActivity(){

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth:FirebaseAuth

    var email_bd: String? = ""
    var pass_bd: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= Firebase.auth

        binding.ingresar.setOnClickListener {

       /*val room = Room.databaseBuilder(this, bdUsuarios::class.java, "bdAutoshop").build()
            val correo: String = binding.email.text.toString()
            lifecycleScope.launch {
                var usuRes = room.daoUsuario().buscarUsuario(correo)
                if(usuRes!=null) {
                    email_bd = usuRes.email
                    pass_bd = usuRes.password
                    println("--> ${usuRes.email} -- ${usuRes.nombre} -- ${usuRes.apellido}")
                }else{
                    email_bd=""
                    pass_bd=""
                }
                validarRoom(email_bd!!, pass_bd!!)
            }*/
        validarFirebase()
        }
       // binding.ingresar.setOnClickListener{validar()}
        binding.registrar.setOnClickListener{enlace_registro()}
        binding.restablecer.setOnClickListener{enlace_restablecer()}
    }

        fun validar(){//funcion para validar desde el dispositivo
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

    fun validarRoom(email:String, pass:String) {//funcion para validar desde la memoria con room
        val correo: String = binding.email.text.toString()
        val password: String = binding.pass.text.toString()

        if (correo.isEmpty()) {
            binding.email.setHint("Campo vacio")
            binding.email.setHintTextColor(Color.RED)
            Toast.makeText(this, "Digite correo", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty()) {
            binding.pass.setHint("Campo vacio")
            binding.pass.setHintTextColor(Color.RED)
            Toast.makeText(this, "Digite contraseña", Toast.LENGTH_LONG).show()
        } else if (correo == email)
                if (password == pass) {
                    Toast.makeText(this,"BIENVENIDO A AUTOSHOP $correo" , Toast.LENGTH_LONG).show()
                    var intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            else {
                    Toast.makeText(this, "contraseña incorrecta", Toast.LENGTH_LONG).show()
                }
            else {
                Toast.makeText(this, "Usuario incorrecto o no exixte", Toast.LENGTH_LONG).show()
            }
        }

    fun validarFirebase(){
        val correo:String=binding.email.text.toString()
        val password:String=binding.pass.text.toString()

        if (correo.isEmpty()) {
            binding.email.setHint("Campo vacio")
            binding.email.setHintTextColor(Color.RED)
            Toast.makeText(this, "Digite correo", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty()) {
            binding.pass.setHint("Campo vacio")
            binding.pass.setHintTextColor(Color.RED)
            Toast.makeText(this, "Digite contraseña", Toast.LENGTH_LONG).show()
        }else{
            firebaseAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    val user =firebaseAuth.currentUser
                    if (user != null) {
                        Toast.makeText(
                            this,
                            "BIENVENIDO A AUTOSHOP ${user.email}",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
                }else{
                    Toast.makeText(this, "Datos incorrectos, Verifique sus datos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    fun enlace_registro(){
        startActivity(Intent(this, RegistrarActivity::class.java))
    }

    fun enlace_restablecer(){
        startActivity(Intent(this, RestablecerActivity::class.java))
    }


}