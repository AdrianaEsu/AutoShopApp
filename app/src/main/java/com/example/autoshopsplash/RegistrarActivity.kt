package com.example.autoshopsplash

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.autoshopsplash.databinding.ActivityLoginBinding
import com.example.autoshopsplash.databinding.ActivityRegistrarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class RegistrarActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrarBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firestoreBd:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= Firebase.auth
        firestoreBd= FirebaseFirestore.getInstance()
       // binding.registro.setOnClickListener { guardarUsuario() }
        binding.registro.setOnClickListener { registrarFirebase() }
    }

    fun guardarUsuario() {
        val nombre: String = binding.nombre.text.toString()
        val apellido: String = binding.apellidos.text.toString()
        val documento: String = binding.documento.text.toString()
        val correo: String = binding.correo.text.toString()
        val direccion: String = binding.direccion.text.toString()
        val telefono: String = binding.telefono.text.toString()
        val password: String = binding.password.text.toString()
        var usuario= Usuario_Entidad(nombre,apellido,documento,correo,direccion,telefono,password)
        val room= Room.databaseBuilder(this,bdUsuarios::class.java,"bdAutoshop").build()


       lifecycleScope.launch {

           room.daoUsuario().agregarUsuario(usuario)
           var lista = room.daoUsuario().consultarUsuarios()
           for (usu in lista) {
               println("USUARIO: ${usu.email} -- ${usu.nombre} -- ${usu.apellido}")
           }

           var usuarioEncontrado:Usuario_Entidad = room.daoUsuario().buscarUsuario("laura@gmail.com")
           println("USUARIO ENCONTRADO: ${usuarioEncontrado.email}-- ${usuarioEncontrado.nombre} -- ${usuarioEncontrado.apellido} ")
       }


        /*var pref = getSharedPreferences(correo, Context.MODE_PRIVATE)
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
            editar.commit()*/
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }
    fun registrarFirebase(){

        val nombre: String = binding.nombre.text.toString()
        val apellido: String = binding.apellidos.text.toString()
        val documento: String = binding.documento.text.toString()
        val correo: String = binding.correo.text.toString()
        val direccion: String = binding.direccion.text.toString()
        val telefono: String = binding.telefono.text.toString()
        val password: String = binding.password.text.toString()
        var id:String

       /* if (correo.isEmpty()) {
            binding.correo.setHint("Campo vacio")
            binding.correo.setHintTextColor(Color.RED)
            Toast.makeText(this, "Digite correo", Toast.LENGTH_LONG).show()
        } else if (password.isEmpty()) {
            binding.password.setHint("Campo vacio")
            binding.password.setHintTextColor(Color.RED)
            Toast.makeText(this, "Digite contraseña", Toast.LENGTH_LONG).show()
        }*/
        if(nombre.isEmpty() || apellido.isEmpty() || documento.isEmpty() || correo.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Existen campos vacios", Toast.LENGTH_LONG).show()
        }
        else{
            firebaseAuth.createUserWithEmailAndPassword(correo,password).addOnCompleteListener(this){
                task->
                if (task.isSuccessful){
                    id = firebaseAuth.currentUser?.uid.toString()
                    var data = hashMapOf<String, String>(
                        "nombre" to nombre,
                        "apellido" to apellido,
                        "documento" to documento,
                        "direccion" to direccion,
                        "telefono" to telefono
                    )
                    //registro en base de datos firestore database
                    firestoreBd.collection("Usuarios").document(id).set(data).addOnSuccessListener {
                        task ->
                        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }.addOnFailureListener{
                        error ->
                        Toast.makeText(this, "ERROR: $error", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this, "Error...verifique datos ingresados", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}