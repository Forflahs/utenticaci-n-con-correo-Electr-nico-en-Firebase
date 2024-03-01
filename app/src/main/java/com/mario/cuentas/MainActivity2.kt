package com.mario.cuentas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private val LOGIN_REQUEST_CODE = 100 // Puedes elegir cualquier código que desees

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        firebaseAuth = FirebaseAuth.getInstance()

        val btnCerrarSesion: Button = findViewById(R.id.btnCerrarSesion)

        btnCerrarSesion.setOnClickListener {
            cerrarSesion()
        }
    }

    private fun cerrarSesion() {
        firebaseAuth.signOut()

        // Intent para retroceder a LoginActivity
        val intent = Intent(this, MainActivity::class.java)
        startActivityForResult(intent, LOGIN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // La lógica para borrar datos de la actividad anterior
            // Puedes realizar cualquier acción necesaria para limpiar los datos aquí
        }
    }

    override fun onBackPressed() {
        // Evitar que la actividad sea cerrada al presionar el botón de retroceso
        // Puedes agregar aquí cualquier lógica adicional que necesites.
        return
    }
}
