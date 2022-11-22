package com.example.autoshopsplash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerVehiculo(var listaVehiculos:MutableList<Vehiculo>):
    RecyclerView.Adapter<RecyclerVehiculo.MiHolder>() {
inner class MiHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    lateinit var  marca:TextView
    lateinit var  modelo:TextView
    lateinit var  precio:TextView
    lateinit var  imagen:ImageView
    init {
        marca = itemView.findViewById(R.id.marcaV)
        modelo = itemView.findViewById(R.id.modeloV)
        precio = itemView.findViewById(R.id.precioV)
        imagen= itemView.findViewById(R.id.fotoV)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_vehiculo,parent,false)
        return  MiHolder(itemView)
    }

    override fun onBindViewHolder(holder: MiHolder, position: Int) {
        var vehiculo =listaVehiculos[position]
        holder.marca.text=vehiculo.marca
        holder.modelo.text=vehiculo.modelo
        holder.precio.text= vehiculo.precio
        Glide.with(holder.itemView)
            .load(vehiculo.foto)
            .into(holder.imagen);
    }
    override fun getItemCount(): Int {
        return listaVehiculos.size
    }
}