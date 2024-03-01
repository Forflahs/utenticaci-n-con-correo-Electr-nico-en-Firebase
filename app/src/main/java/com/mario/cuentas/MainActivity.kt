package com.mario.cuentas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListenet: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btningersar : Button = findViewById(R.id.btnIngresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val txtpass : TextView = findViewById(R.id.edtPassword)
        val btnCrear_CuentaNueva : TextView = findViewById(R.id.btnCrearCuenta)
        val btnRecordar: TextView = findViewById(R.id.btnOlvidar)
        firebaseAuth= Firebase.auth
        btningersar.setOnClickListener()
        {
         singIn(txtemail.text.toString(),txtpass.text.toString())
        }
        btnCrear_CuentaNueva.setOnClickListener()
        {
            val i =Intent (this,CrearCuentaActivity::class.java)
            startActivity(i)
        }
        btnRecordar.setOnClickListener()
        {
            val i =Intent(this,RecordarPassActivity::class.java )
            startActivity(i)
        }
    }
    private fun singIn(email: String, password: String)
    {
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->

                if (task.isSuccessful){
                    val user =firebaseAuth.currentUser
                    val verifica=user?.isEmailVerified
                    if (verifica==true) {
                        Toast.makeText(baseContext, "Atentificacion Exitosa", Toast.LENGTH_SHORT)
                            .show()
                        //aqui vamos a ir a la segunda activity
                        val i = Intent(this, MainActivity2::class.java)
                        startActivity(i)
                    }
                    else
                    {
                        Toast.makeText(baseContext,"No ha verificado su correo",Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(baseContext,"Error de Email y/o Contraseña", Toast.LENGTH_SHORT).show()

                }


            }
    }
}