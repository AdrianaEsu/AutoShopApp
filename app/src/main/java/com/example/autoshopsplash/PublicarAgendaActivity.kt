package com.example.autoshopsplash

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.autoshopsplash.databinding.ActivityPublicarAgendaBinding
import com.example.autoshopsplash.databinding.ActivityPublicarVehiculoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.File


class PublicarAgendaActivity:AppCompatActivity() {
    lateinit var binding: ActivityPublicarAgendaBinding
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPublicarAgendaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        val email = firebaseAuth.currentUser?.email.toString()
        Toast.makeText(this," BIENVENIDO A AUTOSHOP $email ", Toast.LENGTH_LONG).show()

       // var email=intent.getStringExtra("email")
        //Toast.makeText(this," BIENVENIDO A AUTOSHOP $email ", Toast.LENGTH_LONG).show()

        binding.cita.setOnClickListener { guardarAgenda() }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.salir -> {
                firebaseAuth.signOut()
                Toast.makeText(this, "Sesion Cerrada correctamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun guardarAgenda() {
        val comprador: String = binding.comprador.text.toString()
        val telefono: String = binding.telefonoC.text.toString()
        val email: String = binding.emailC.text.toString()
        val vendedor: String = binding.vendedorA.text.toString()
        val fecha: String = binding.fecha.text.toString()
        val hora: String = binding.hora.text.toString()

        var pref = getSharedPreferences(comprador,Context.MODE_PRIVATE)
        var editar = pref.edit()
        editar.putString("comprador", comprador)
        editar.putString("telefono", telefono)
        editar.putString("email", email)
        editar.putString("vendedor", vendedor)
        editar.putString("fecha", fecha)
        editar.putString("hora", hora)

        if (comprador.isEmpty()) {
            binding.comprador.setHint("Campo vacio")
            binding.comprador.setHintTextColor(Color.RED)
        } else if (telefono.isEmpty()) {
            binding.telefonoC.setHint("Campo vacio")
            binding.telefonoC.setHintTextColor(Color.RED)
        } else if (email.isEmpty()) {
            binding.emailC.setHint("Campo vacio")
            binding.emailC.setHintTextColor(Color.RED)
        } else if (vendedor.isEmpty()) {
            binding.vendedorA.setHint("Campo vacio")
            binding.vendedorA.setHintTextColor(Color.RED)
        } else if (fecha.isEmpty()) {
        binding.fecha.setHint("Campo vacio")
        binding.fecha.setHintTextColor(Color.RED)
         } else if (hora.isEmpty()) {
        binding.hora.setHint("Campo vacio")
        binding.hora.setHintTextColor(Color.RED)
         }else{
            editar.commit()
            Toast.makeText(this, "Agendamiento exitoso", Toast.LENGTH_LONG).show()
        }
    }
}