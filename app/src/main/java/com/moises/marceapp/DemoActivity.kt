package com.moises.marceapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
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

        tvRTA!!.visibility = View.VISIBLE

        LOG.info("entro al metodo: onActivityResult")

        if (requestCode == REQUEST_CODE_CHANGE_ACTIVITY){

            when (resultCode){
                1001 -> tvRTA?.text = data?.getStringExtra("respuesta")
                1002 -> tvRTA?.text = "el usuario es un bigote y cancelo la operacion"
                else -> {
                            Toast.makeText(this@DemoActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
                            LOG.warning("Unknown ERROR - invalid ResultCode from NombreActivity - Result Code: "+resultCode.toString())
                        }
            }
        }else if (requestCode == REQUEST_CODE_CHANGE_PICTURE)
        {
            if(resultCode==0) {
                tvRTA?.text = "el usuario no guardo la foto :("
            }

            if (resultCode==-1){
                tvRTA?.text = "el usuario guardo la foto :)"
            }

        }
    }

    companion object {
        val LOG = Logger.getLogger(DemoActivity::class.java.name)
    }
}


