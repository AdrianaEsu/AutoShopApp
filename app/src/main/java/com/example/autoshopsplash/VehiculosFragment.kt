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
import com.google.android.material.floatingactionbutton.FloatingActionButton



class VehiculosFragment : Fragment() {

    private var _binding: FragmentVehiculosBinding? = null
    private val binding get() = _binding!!
    private var lista: MutableList<Vehiculo> = mutableListOf()
    private var fab: FloatingActionButton? = null
    private lateinit var recycler: RecyclerView

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var intent =Intent(context,PublicarVehiculoActivity::class.java)
        if(arguments != null){
            val email = requireArguments().getString("email")
            intent.putExtra("email",email)
        }

       // lista.add((Vehiculo("chevrolet", "2020", "12000 Km", "1600 CC","Adriana Espitia", R.drawable.vehiculos)))
        //lista.add((Vehiculo("susuki", "2020", "12000 Km", "1200 CC","Juan Morales", R.drawable.km)))

       // binding.listaVendedor.apply {
          //  layoutManager = LinearLayoutManager(activity)
          //  adapter = RecyclerVehiculo(lista)

            fab = binding.agregar
            fab!!.setOnClickListener {
                startActivity(intent)
            }
        }
    }
