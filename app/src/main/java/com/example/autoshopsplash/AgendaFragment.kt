package com.example.autoshopsplash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.autoshopsplash.databinding.FragmentAgendaBinding
import com.example.autoshopsplash.databinding.FragmentVehiculosBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AgendaFragment : Fragment() {
    private var _binding: FragmentAgendaBinding? = null
    private val binding get() = _binding!!
    private var lista: MutableList<Agenda> = mutableListOf()
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
        _binding = FragmentAgendaBinding.inflate(inflater)
        var view: ConstraintLayout = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var intent = Intent(context,PublicarAgendaActivity::class.java)
        if(arguments != null){
            val email = requireArguments().getString("email")
            intent.putExtra("email",email)
        }

        fab = binding.addcita
        fab!!.setOnClickListener {
            startActivity(intent)
        }
    }
}
