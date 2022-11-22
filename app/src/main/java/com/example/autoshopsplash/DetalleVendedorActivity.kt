package com.example.autoshopsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.autoshopsplash.databinding.ActivityDetalleVendedorBinding

class DetalleVendedorActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetalleVendedorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetalleVendedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre =intent.getStringExtra("DnombreV")
        var ciudad =intent.getStringExtra("DciudadV")
        var telefono =intent.getStringExtra("DtelefonoV")
        var direccion =intent.getStringExtra("DdireccionV")
        var foto =intent.getStringExtra("fotoVen")

        binding.DnombreV.text=nombre
        binding.DciudadV.text=ciudad
        binding.DtelefonoV.text=telefono
        binding.DdireccionV.text=direccion
        Glide.with(this)
            .load(foto)
            .into(binding.fotoVen);

    }
}