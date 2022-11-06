package com.example.autoshopsplash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerVendedor(var listaVendedores:MutableList<Vendedor>):RecyclerView.Adapter<RecyclerVendedor.MiHolder>() {
inner class MiHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    lateinit var  nombres:TextView
    lateinit var  ciudad:TextView
    lateinit var  telefono:TextView
    lateinit var  imagen:ImageView
    init {
        nombres = itemView.findViewById(R.id.marcaV)
        ciudad = itemView.findViewById(R.id.modeloV)
        telefono = itemView.findViewById(R.id.kilometrajeV)
        imagen= itemView.findViewById(R.id.fotoVh)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_vendedor,parent,false)
        return  MiHolder(itemView)
    }

    override fun onBindViewHolder(holder: MiHolder, position: Int) {
        var vendedor =listaVendedores[position]
        holder.nombres.text=vendedor.nombre
        holder.ciudad.text=vendedor.ciudad
        holder.telefono.text=vendedor.telefono
        holder.imagen.setImageResource(vendedor.foto)
    }

    override fun getItemCount(): Int {
        return listaVendedores.size
    }
}