package com.example.autoshopsplash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.autoshopsplash.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity: AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var email=intent.getStringExtra("email")
        Toast.makeText(this,"$email BIENVENIDO A AUTOSHOP ", Toast.LENGTH_LONG).show()

        val vehiculos_F = VehiculosFragment()
        val agenda_F = AgendaFragment()
        val vendedor_F = VendedorFragment()
        firebaseAuth =Firebase.auth

        /*val bundle = Bundle()
        bundle.putString("email",email)
        vendedor_F.arguments=bundle*/

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_desplegable,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.salir -> {
            firebaseAuth.signOut()
                Toast.makeText(this, "Sesion Cerrada correctamente", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun footer(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.contenido, fragment)
            commit()
        }
    }
}