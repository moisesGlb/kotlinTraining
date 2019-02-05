package com.moises.marceapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.logging.Logger

class DemoActivity : AppCompatActivity() {


    private val REQUEST_CODE_CHANGE_PICTURE = 2
    private val REQUEST_CODE_CHANGE_ACTIVITY = 1
    private var btnGO: Button ? = null
    private var btnFOTO: Button ? = null
    private var etFRASE: EditText ? =  null
    private var tvRTA: TextView ? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

       // setContentView(R.layout.activity_demo)

        btnGO=findViewById(R.id.btnGO)
        btnFOTO=findViewById(R.id.btnFoto)
        etFRASE=findViewById(R.id.etFrase)
        tvRTA=findViewById(R.id.tvRta)

        btnGO?.setOnClickListener{
            val frase: String = etFRASE?.text.toString()

            if (frase.isBlank() || frase.isNullOrEmpty()){
                Toast.makeText(this@DemoActivity, "Ingrese un texto en el campo nombre.", Toast.LENGTH_SHORT).show()
            }
            else
            {
               // Toast.makeText(this@DemoActivity, "Hola "+frase+".", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, NombreActivity::class.java)

                intent.putExtra("nombre", frase)

                val msg: String = "iniciando la activity: NombreActivity - Request code: " + REQUEST_CODE_CHANGE_ACTIVITY.toString()

                LOG.info(msg)

                startActivityForResult(intent, REQUEST_CODE_CHANGE_ACTIVITY)
            }


        }

        btnFOTO?.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CODE_CHANGE_PICTURE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var texto: String = "el valor no se guardo ameo"
        tvRTA!!.visibility = View.VISIBLE

        LOG.info("entro al metodo: onActivityResult")

        if (requestCode == REQUEST_CODE_CHANGE_ACTIVITY){

            when (resultCode){
                1001 -> texto = data?.getStringExtra("respuesta")!!
                1002 -> texto = "el usuario es un bigote y cancelo la operacion"
                else -> {
                            Toast.makeText(this@DemoActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                            LOG.warning("Unknown ERROR - invalid ResultCode from NombreActivity - Result Code: "+resultCode.toString())
                        }
            }
        }else if (requestCode == REQUEST_CODE_CHANGE_PICTURE)
        {
            if(resultCode==0) {
                texto = "el usuario no guardo la foto :("
            }

            if (resultCode==-1){
                texto = "el usuario guardo la foto :)"
            }

        }

        tvRTA?.text = texto
    }

    companion object {
        val LOG = Logger.getLogger(DemoActivity::class.java.name)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        val userText: String  = tvRTA?.text.toString()

        outState?.putCharSequence("savedText", userText)

        LOG.info("onSaveInstanceState: "+ userText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        tvRTA!!.visibility = View.VISIBLE

        val userText = savedInstanceState?.getCharSequence("savedText")

        LOG.info("onRestoreInstanceState: "+ userText)

        tvRTA?.text =userText
    }
}


