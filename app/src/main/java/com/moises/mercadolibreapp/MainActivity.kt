package com.moises.mercadolibreapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.moises.mercadolibreapp.model.Product

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView

    private var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar= findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        // Inflate menu to add items to action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu)
        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(myQueryPe: String): Boolean {
                search(myQueryPe.toString())
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                if (TextUtils.isEmpty(s)) {
                } else {

                }
                return true
            }
        })
        return true
    }

    private fun search(query: String){

        if (!query.isNullOrEmpty()){

            val intent = Intent(this@MainActivity,ProductListActivity::class.java)

            intent.putExtra("filtroBusqueda",query)

            startActivity(intent)
        }else{
            Toast.makeText(this, "Debe Ingresar un texto para buscar",Toast.LENGTH_SHORT).show()
        }

    }
}
