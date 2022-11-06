package com.example.autoshopsplash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshopsplash.databinding.FragmentVendedorBinding

class VendedorFragment : Fragment() {

    private  var _binding:FragmentVendedorBinding? = null
    private val binding get() = _binding!!
    private  var lista:MutableList<Vendedor> = mutableListOf()
    private  lateinit var  recycler: RecyclerView

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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lista.add((Vendedor("Adriana Espitia", "3233965624","Fusagasuga", R.drawable.info_vendedor)))
        lista.add((Vendedor("Juan Morales", "3233965624","Bogota", R.drawable.dir)))
        lista.add((Vendedor("Ricardo Rojas", "3233965624","Medellin",R.drawable.calendario)))
        lista.add((Vendedor("Miguel Fuentes", "3233965624","Cartagena", R.drawable.email)))
        lista.add((Vendedor("Lorena Castro", "3233965624","Bogota", R.drawable.acceso)))
        lista.add((Vendedor("karol Meneses", "3233965624","Pereira", R.drawable.doc)))
        lista.add((Vendedor("Jeronimo Caipa", "3233965624","Cali", R.drawable.telefono)))
        binding.listaVendedor.apply {
            layoutManager=LinearLayoutManager(activity)
            adapter=RecyclerVendedor(lista)
        }
    }

}