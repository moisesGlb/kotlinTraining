package com.moises.mercadolibreapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class ProductListActivity : AppCompatActivity() {

    private var layoutManager: LinearLayoutManager? = null

    private var adapter: ProductListAdapter? = null

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_productos)


        recyclerView= findViewById(R.id.rvListaProductos)

        layoutManager = LinearLayoutManager(this)

        layoutManager!!.orientation = LinearLayoutManager.VERTICAL

        adapter = ProductListAdapter(this)

        recyclerView.layoutManager = layoutManager

        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        recyclerView.adapter = adapter

        var string = intent.getStringExtra("filtroBusqueda")

        adapter!!.getListaProductos(string)
    }
}
