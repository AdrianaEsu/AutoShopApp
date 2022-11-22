package com.example.autoshopsplash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.autoshopsplash.databinding.ActivityRestablecerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RestablecerActivity: AppCompatActivity() {
    lateinit var binding: ActivityRestablecerBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestablecerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth

        binding.restablece.setOnClickListener {
          var correo=binding.email.text.toString()
          enviarcorreo(correo)
        }
    }
    fun enviarcorreo(correo:String){
        firebaseAuth.sendPasswordResetEmail(correo).addOnCompleteListener(){
            task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Se envio enlace de restablecimiento a su correo", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }else{
                Toast.makeText(this, "Verifique el correo ingresado", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
}