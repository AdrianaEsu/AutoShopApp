package com.example.autoshopsplash

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.autoshopsplash.databinding.ActivityHomeBinding

class HomeActivity: AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var email=intent.getStringExtra("email")
        Toast.makeText(this,"$email BIENVENIDO A AUTOSHOP ", Toast.LENGTH_LONG).show()

        val vehiculos_F = VehiculosFragment()
        val agenda_F = AgendaFragment()
        val vendedor_F = VendedorFragment()

        val bundle = Bundle()
        bundle.putString("email",email)
        vendedor_F.arguments=bundle

        binding.footer.setOnNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.vehiculos -> {
                    footer(vehiculos_F)
                    true
                }
                R.id.agenda -> {
                    footer(agenda_F)
                    true
                }
                R.id.vendedor -> {
                    footer(vendedor_F)
                    true
                }
                else->false
            }
        }

    }

    private fun footer(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.contenido, fragment)
            commit()
        }
    }
}