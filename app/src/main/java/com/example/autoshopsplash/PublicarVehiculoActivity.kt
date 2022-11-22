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
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.autoshopsplash.databinding.ActivityPublicarVehiculoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.File


class PublicarVehiculoActivity:AppCompatActivity() {
    lateinit var binding: ActivityPublicarVehiculoBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firestoreBd: FirebaseFirestore
    lateinit var imagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPublicarVehiculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = Firebase.auth
        firestoreBd= FirebaseFirestore.getInstance()

        binding.publicar.setOnClickListener { registroFirebase() }
        val email = firebaseAuth.currentUser?.email.toString()


       // var email=intent.getStringExtra("email")
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
    fun registroFirebase(){

        val marca: String = binding.marca.text.toString()
        val modelo: String = binding.modelo.text.toString()
        val kilometraje: String = binding.km.text.toString()
        val cilindraje: String = binding.cilindraje.text.toString()
        val precio: String = binding.precio.text.toString()
        val vendedor: String = binding.vendedor.text.toString()



        var id:String
        if(marca.isEmpty() || modelo.isEmpty() || kilometraje.isEmpty() || cilindraje.isEmpty() || precio.isEmpty() || vendedor.isEmpty()){
            Toast.makeText(this, "Existen campos vacios", Toast.LENGTH_LONG).show()
        }
        else{
            id = firebaseAuth.currentUser?.uid.toString()
            var data = hashMapOf<String, String>(
                "marca" to marca,
                "modelo" to modelo,
                "kilometraje" to kilometraje,
                "cilindraje" to cilindraje,
                "precio" to precio,
                "vendedor" to vendedor,
            )
                    //registro en base de datos firestore database
                    firestoreBd.collection("Vehiculos").document(id).set(data).addOnSuccessListener {
                            task ->
                        Toast.makeText(this, "Registro Existoso", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                    }.addOnFailureListener{
                            error ->
                        Toast.makeText(this, "ERROR: $error", Toast.LENGTH_LONG).show()
                    }
                }
            }


}