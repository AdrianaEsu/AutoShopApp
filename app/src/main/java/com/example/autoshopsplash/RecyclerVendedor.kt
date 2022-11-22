package com.example.autoshopsplash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerVendedor(var listaVendedores:MutableList<Vendedor>,private val itemClickListener: detalleVendedor):
    RecyclerView.Adapter<RecyclerVendedor.MiHolder>() {

    interface detalleVendedor{
        fun onItemClick(vendedor: Vendedor)
    }
inner class MiHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    lateinit var nombres: TextView
    lateinit var ciudad: TextView
    lateinit var telefono: TextView
    lateinit var imagen: ImageView

    init {
        nombres = itemView.findViewById(R.id.nombreV)
        ciudad = itemView.findViewById(R.id.ciudadV)
        telefono = itemView.findViewById(R.id.telefonoV)
        imagen = itemView.findViewById(R.id.fotoV)
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
        //holder.imagen.setImageResource(vendedor.foto)
        Glide.with(holder.itemView)
            .load(vendedor.foto)
            .into(holder.imagen);

        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(vendedor)
            Toast.makeText(holder.itemView.context, "${vendedor.ciudad}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return listaVendedores.size
    }
}