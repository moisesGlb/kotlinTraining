package com.moises.marceapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.logging.Logger

class NombreActivity : AppCompatActivity() {

    private var tvNombre: TextView ? = null
    private var etRespuesta: EditText ? = null
    private var btnCancelar: Button ? = null
    private var btnAceptar: Button ? = null
    private val RESULT_CODE_BTN_ACEPTAR = 1001
    private val RESULT_CODE_CHANGE_ACTIVITY_BTN_CANCELAR = 1002


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nombre)

        com.moises.marceapp.NombreActivity.Companion.LOG.info("NombreActivity iniciada")

        tvNombre = findViewById(R.id.tvNombreRecibido)
        etRespuesta = findViewById(R.id.etRta)
        btnAceptar = findViewById(R.id.btnAceptar)
        btnCancelar = findViewById(R.id.btnCancelar)

        val str: String = tvNombre?.text.toString()

        tvNombre?.text= "Hola "+ intent.getStringExtra("nombre")

        btnAceptar?.setOnClickListener{

            val rta: String = etRespuesta?.text.toString()

                val intent = Intent(this, DemoActivity::class.java)

                if (rta.toString().isEmpty() || rta.toString().isBlank())

                {
                    setResult(RESULT_CODE_CHANGE_ACTIVITY_BTN_CANCELAR,intent)
                }else
                {

                    intent.putExtra("respuesta", etRespuesta?.text.toString())

                    setResult(RESULT_CODE_BTN_ACEPTAR,intent)

                }

                finish()
        }

        btnCancelar?.setOnClickListener{

            val intent = Intent(this, DemoActivity::class.java)

            setResult(RESULT_CODE_CHANGE_ACTIVITY_BTN_CANCELAR,intent)

            NombreActivity.LOG.info("boton aceptar presionado. Iniciando Demo activity con result: "+RESULT_CODE_CHANGE_ACTIVITY_BTN_CANCELAR.toString())

            finish()
        }

    }

    companion object {
        val LOG = Logger.getLogger(NombreActivity::class.java.name)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        val userText: String  = tvNombre?.text.toString()

        outState?.putCharSequence("savedText", userText)

        NombreActivity.LOG.info("onSaveInstanceState: "+ userText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        val userText = savedInstanceState?.getCharSequence("savedText")

        NombreActivity.LOG.info("onRestoreInstanceState: "+ userText)

        tvNombre?.text =userText
    }
}
