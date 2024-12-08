package com.example.gr2sw2024b_jspy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ACicloVida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_aciclo_vida)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_list_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mostrarSnackbar("onCreate")
    }

    override fun onStart() {
        super.onStart()
        mostrarSnackbar("OnStart")
    }
    override fun onResume() {
        super.onResume()
        mostrarSnackbar("OnResume")
    }
    override fun onRestart() {
        super.onRestart()
        mostrarSnackbar("OnRestart")
    }
    override fun onPause() {
        super.onPause()
        mostrarSnackbar("OnPause")
    }
    override fun onStop() {
        super.onStop()
        mostrarSnackbar("OnStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        mostrarSnackbar("OnDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run{
            //guardamos variables
            putString("variableTextoGuardado", textoGlobal)
        }
        super.onSaveInstanceState(outState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //recuperar variables
        val textoRecuperado: String? = savedInstanceState.getString("variableTextoGuardado")
        if (textoRecuperado != null) {
            mostrarSnackbar(textoRecuperado)
        }
    }

    var textoGlobal = ""
    fun mostrarSnackbar(texto: String) {
        textoGlobal+=texto
        var snack=Snackbar.make(
            findViewById(R.id.cl_ciclo_vida),
            textoGlobal,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}