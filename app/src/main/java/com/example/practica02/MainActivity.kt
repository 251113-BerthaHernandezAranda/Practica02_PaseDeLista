package com.example.practica02

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvMensaje: TextView
    private lateinit var etxtNombre: EditText
    private lateinit var etxtMatricula: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var pbCarga: ProgressBar
    private var contadorAsistentes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
            insets
        }

        tvMensaje = findViewById(R.id.tvMensaje)
        etxtNombre = findViewById(R.id.etxtNombre)
        etxtMatricula = findViewById(R.id.etxtMatricula)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        pbCarga = findViewById(R.id.pbCarga)

        btnRegistrar.setOnClickListener {
            registrarse()
        }
        btnLimpiar.setOnClickListener { limpiar() }

    }

    private fun registrarse() {
        val nombre = etxtNombre.text.toString().trim()
        val matricula = etxtMatricula.text.toString().trim()

        if (nombre.isEmpty()) {
            Toast.makeText(this, "Escribe tu nombre. ", Toast.LENGTH_SHORT).show()
            return
        } else if (nombre.length < 3) {
            Toast.makeText(this, "El nombre es demasiado corto.", Toast.LENGTH_SHORT).show()
            return
        }
        if (matricula.isEmpty()) {
            Toast.makeText(this, "Escribe tu matrícula. ", Toast.LENGTH_SHORT).show()
            return
        } else if (matricula.length != 7) {
            Toast.makeText(this, "La matrícula debe tener 7 dígitos. ", Toast.LENGTH_SHORT).show()
            return
        }

        contadorAsistentes++
        pbCarga.visibility = View.VISIBLE
        tvMensaje.text = ""
        btnRegistrar.isEnabled = false
        btnLimpiar.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            pbCarga.visibility = View.GONE
            //contadorAsistentes++
            //tvMensaje.text = "Hola, $nombre, BIENVENIDO AL CAMPUS VIRTUAL DE LA UTXJ. "
            tvMensaje.text =
                "Registrado: $nombre ($matricula)\n" + "Asistente número $contadorAsistentes"
            btnRegistrar.isEnabled = true
            btnLimpiar.isEnabled = true
            btnRegistrar.text = "Registrar otro"
        }, 1500)
    }

    //POSTURA Y JUSTIFICACIÓN (PASO 5):
    /*
      El botón limpiar no debe reinicar el contador de asistentes,
      porque su propósito es vaciar los campos, permitiendo nuevo registro.
      si el contador reiniciara entonces se perdería la cuenta acumulada de alumnos.
     */
    private fun limpiar() {
        etxtNombre.setText("")
        etxtMatricula.setText("")
        pbCarga.visibility = View.GONE
        tvMensaje.text = "Escribe tus datos para registrarte."
        btnRegistrar.text = "Registrar"
        btnRegistrar.isEnabled = true
        etxtNombre.requestFocus()
    }
}
