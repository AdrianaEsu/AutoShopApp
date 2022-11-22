package com.example.autoshopsplash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshopsplash.databinding.FragmentVendedorBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class VendedorFragment : Fragment(), RecyclerVendedor.detalleVendedor{

    private  var _binding:FragmentVendedorBinding? = null
    private val binding get() = _binding!!
    private  var lista:MutableList<Vendedor> = mutableListOf()
    private  lateinit var  recycler: RecyclerView
    lateinit var firestoreBD: FirebaseFirestore
    lateinit var intent: Intent

    override  fun  onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentVendedorBinding.inflate(inflater)
        var view: FrameLayout = binding.root
        firestoreBD =FirebaseFirestore.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lista.clear()
        intent=Intent(context,DetalleVendedorActivity::class.java)

        firestoreBD.collection("Vendedores").get().addOnSuccessListener {
            resultado ->
            for(doc in resultado){
                var ven = Vendedor(
                    doc.getString("nombre") as String,
                    doc.getString("ciudad") as String,
                    doc.getString("telefono") as String,
                    doc.getString("direccion") as String,
                    doc.getString("foto") as String
                )
                lista.add(ven)
            }
            binding.listaVendedor.apply {
                layoutManager=LinearLayoutManager(activity)
                adapter=RecyclerVendedor(lista,this@VendedorFragment)
        }

       /* lista.add((Vendedor("Adriana Espitia", "3233965624","Fusagasuga", "https://firebasestorage.googleapis.com/v0/b/autoshop-app-daeb7.appspot.com/o/vendedores%2Fvendedor4.png?alt=media&token=3d8be51e-8f77-42ea-8edb-c6ccb48c9070")))
        lista.add((Vendedor("Juan Morales", "3233965624","Bogota", "https://firebasestorage.googleapis.com/v0/b/autoshop-app-daeb7.appspot.com/o/vendedores%2Fvendedor3.png?alt=media&token=8bb91ba3-a0e8-4e07-9fc7-be12de5f80b9")))

        binding.listaVendedor.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=RecyclerVendedor(lista)*/
        }
    }

    override fun onItemClick(vendedor: Vendedor) {
        intent.putExtra("DnombreV",vendedor.nombre)
        intent.putExtra("DciudadV",vendedor.ciudad)
        intent.putExtra("DtelefonoV",vendedor.telefono)
        intent.putExtra("DdireccionV",vendedor.direccion)
        intent.putExtra("fotoVen",vendedor.foto)
        startActivity(intent)
    }

}