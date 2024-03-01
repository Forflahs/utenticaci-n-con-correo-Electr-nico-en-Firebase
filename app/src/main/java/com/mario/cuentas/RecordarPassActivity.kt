package com.mario.cuentas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecordarPassActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recordar_pass)

        val txtmail: TextView = findViewById(R.id.txtemailCambio)
        val btncambiar: Button = findViewById(R.id.btnCambiar)
        val botonAtras: Button = findViewById(R.id.botonAtras)

        firebaseAuth = Firebase.auth

        botonAtras.setOnClickListener {
            // Aquí llamamos al método finish() para cerrar la actividad actual y regresar a la anterior.
            finish()
        }

        btncambiar.setOnClickListener {
            sendPasswordReset(txtmail.text.toString())
            val email = txtmail.text.toString()

            if (email.isNotEmpty()) {
                sendPasswordReset(email)
            } else {
                Toast.makeText(baseContext, "Por favor, ingresa un correo electrónico", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendPasswordReset(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Correo de cambio de contraseña enviado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Error, no se pudo completar el proceso: ${task.exception}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
