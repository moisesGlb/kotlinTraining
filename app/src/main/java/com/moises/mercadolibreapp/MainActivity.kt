package com.moises.mercadolibreapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.moises.mercadolibreapp.model.Product

class MainActivity : AppCompatActivity() {

    private var entrada: EditText? = null
    private var btnBuscar: Button? = null
    private var btnBorrar: Button? = null

    private lateinit var textoBusqueda: String

    private var resultadoBusqueda: ArrayList<Product>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        entrada = findViewById(R.id.etEntrada)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnBorrar = findViewById(R.id.btnBorrar)



        btnBorrar!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                textoBusqueda = entrada!!.text.toString()
                if(!textoBusqueda.isNullOrEmpty()){
                    entrada!!.text.clear()
                }
              }
            })

        btnBuscar!!.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                textoBusqueda = entrada!!.text.toString()

                if(!textoBusqueda.isNullOrEmpty()) {
                    entrada!!.text.clear()

                    val intent = Intent(this@MainActivity,ProductListActivity::class.java)

                    intent.putExtra("filtroBusqueda",textoBusqueda)

                    startActivity(intent)

                }

            }
        })


    }
}
