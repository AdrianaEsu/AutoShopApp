package com.example.autoshopsplash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerVehiculo(var listaVehiculos:MutableList<Vehiculo>):RecyclerView.Adapter<RecyclerVehiculo.MiHolder>() {
inner class MiHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    lateinit var  marca:TextView
    lateinit var  modelo:TextView
    lateinit var  kilometraje:TextView
    lateinit var  cilindraje:TextView
    lateinit var  vendedor:TextView
    lateinit var  imagen:ImageView
    init {
        marca = itemView.findViewById(R.id.marca)
        modelo = itemView.findViewById(R.id.modelo)
        kilometraje = itemView.findViewById(R.id.km)
        cilindraje = itemView.findViewById(R.id.cilindraje)
        vendedor = itemView.findViewById(R.id.vendedorV)
        imagen= itemView.findViewById(R.id.fotoVh)
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
        holder.kilometraje.text=vehiculo.kilometraje
        holder.cilindraje.text= vehiculo.cilindraje
        holder.vendedor.text= vehiculo.vendedor
        holder.imagen.setImageResource(vehiculo.foto)
    }

    override fun getItemCount(): Int {
        return listaVehiculos.size
    }
}