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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.autoshopsplash.databinding.ActivityPublicarVehiculoBinding
import java.io.File


class PublicarVehiculoActivity:AppCompatActivity() {
    lateinit var binding: ActivityPublicarVehiculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPublicarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email=intent.getStringExtra("email")
        Toast.makeText(this," BIENVENIDO A AUTOSHOP $email ", Toast.LENGTH_LONG).show()

        binding.publicar.setOnClickListener { guardarVehiculo() }
        binding.tomarfoto.setOnClickListener{
            //abrirCamara.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                crearArchivo()
                val fotoUri=FileProvider.getUriForFile(this,BuildConfig.APPLICATION_ID+".fileprovider",file)
                it.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri)
            }
            abrirCamara.launch(intent)
        }
    }
    val abrirCamara=
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
        if(result.resultCode == RESULT_OK){
            val data = result.data!!
            val bitmap=BitmapFactory.decodeFile(file.toString())
            binding.imagenfoto.setImageBitmap(bitmap)
        }
    }
    private lateinit var file:File
    private  fun crearArchivo(){
        val dir=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        file=File.createTempFile("Foto_${System.currentTimeMillis()}_",".jpg",dir)
    }
    fun guardarVehiculo() {
        val marca: String = binding.marca.text.toString()
        val modelo: String = binding.modelo.text.toString()
        val kilometraje: String = binding.km.text.toString()
        val cilindraje: String = binding.cilindraje.text.toString()
        val precio: String = binding.precio.text.toString()
        val vendedor: String = binding.vendedor.text.toString()

        var pref = getSharedPreferences(marca,Context.MODE_PRIVATE)
        var editar = pref.edit()
        editar.putString("marca", marca)
        editar.putString("modelo", modelo)
        editar.putString("kilometraje", kilometraje)
        editar.putString("cilindraje", cilindraje)
        editar.putString("precio", precio)
        editar.putString("vendedor", vendedor)

        if (marca.isEmpty()) {
            binding.marca.setHint("Campo vacio")
            binding.marca.setHintTextColor(Color.RED)
        } else if (modelo.isEmpty()) {
            binding.modelo.setHint("Campo vacio")
            binding.modelo.setHintTextColor(Color.RED)
        } else if (kilometraje.isEmpty()) {
            binding.km.setHint("Campo vacio")
            binding.km.setHintTextColor(Color.RED)
        } else if (cilindraje.isEmpty()) {
            binding.cilindraje.setHint("Campo vacio")
            binding.cilindraje.setHintTextColor(Color.RED)
        } else if (precio.isEmpty()) {
        binding.precio.setHint("Campo vacio")
        binding.precio.setHintTextColor(Color.RED)
         } else if (vendedor.isEmpty()) {
        binding.vendedor.setHint("Campo vacio")
        binding.vendedor.setHintTextColor(Color.RED)
         }else{
            editar.commit()
            Toast.makeText(this, "Publicacion exitosa", Toast.LENGTH_LONG).show()
        }
    }
}