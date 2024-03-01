package com.mario.cuentas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class CrearCuentaActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        val txtnombre_nuevo: TextView = findViewById(R.id.edtNombre)
        val txtemail_nuevo: TextView = findViewById(R.id.edtEmailNuevo)
        val txtpassword1: TextView = findViewById(R.id.edtpasswordNuevo)
        val txtpassword2: TextView = findViewById(R.id.edtPasswordNuevo2)
        val btnCrear: Button = findViewById(R.id.btnCrearCuentanuevo)
        val botonAtras: Button = findViewById(R.id.botonAtras)

        firebaseAuth = FirebaseAuth.getInstance()

        btnCrear.setOnClickListener {
            val pass1 = txtpassword1.text.toString()
            val pass2 = txtpassword2.text.toString()

            if (pass1 == pass2) {
                createAccount(txtemail_nuevo.text.toString(), txtpassword1.text.toString())
            } else {
                Toast.makeText(baseContext, "Error: las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                txtpassword1.requestFocus()
            }
        }

        botonAtras.setOnClickListener {
            // Aquí llamamos al método finish() para cerrar la actividad actual y regresar a la anterior.
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                sendEmailVerification()
                Toast.makeText(baseContext, "Cuenta creada correctamente, se requiere verificacion", Toast.LENGTH_SHORT).show()
            } else {
                // Manejar errores específicos de Firebase Auth
                when (task.exception) {
                    is FirebaseAuthUserCollisionException -> {
                        Toast.makeText(baseContext, "La cuenta ya existe para este correo electrónico", Toast.LENGTH_SHORT).show()
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        Toast.makeText(baseContext, "Correo electrónico o contraseña no válidos", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(baseContext, "Algo salió mal. Error: ${task.exception}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
    private fun sendEmailVerification()
    {
        val user = firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this){task ->
         if (task.isSuccessful)
         {

         }
            else
         {

         }
        }
    }
}
