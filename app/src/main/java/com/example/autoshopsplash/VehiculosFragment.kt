package com.example.autoshopsplash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshopsplash.databinding.FragmentVehiculosBinding
import com.example.autoshopsplash.databinding.FragmentVendedorBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class VehiculosFragment : Fragment() {


    private var _binding: FragmentVehiculosBinding? = null
    private val binding get() = _binding!!
    private var lista: MutableList<Vehiculo> = mutableListOf()
    private var fab: FloatingActionButton? = null
    private lateinit var recycler: RecyclerView
    lateinit var firestoreBD: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVehiculosBinding.inflate(inflater)
        var view: ConstraintLayout = binding.root
        firestoreBD = FirebaseFirestore.getInstance()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var intent = Intent(context, PublicarVehiculoActivity::class.java)
        fab = binding.agregar
        fab!!.setOnClickListener {
            startActivity(intent)
        }
        firestoreBD.collection("Vehiculos").get().addOnSuccessListener {
                resultado ->
            for (doc in resultado) {
                var veh = Vehiculo(
                    doc.getString("marca") as String,
                    doc.getString("modelo") as String,
                    doc.getString("precio") as String,
                    doc.getString("foto") as String
                )
                lista.add(veh)
            }
            binding.listaVehiculo.apply {
                layoutManager=LinearLayoutManager(activity)
                adapter=RecyclerVehiculo(lista)
            }
        }
    }
}
