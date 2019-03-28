package com.moises.mercadolibreapp.activities

import android.app.SearchManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.*
import com.moises.mercadolibreapp.R
import com.moises.mercadolibreapp.adapter.productListAdapter

class MainActivity: AppCompatActivity()  {


    private lateinit var searchView: SearchView
    private var toolbar: Toolbar? = null
    private lateinit var splashcontainer: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: productListAdapter? = null

    private lateinit var tvSplash1: TextView
    private lateinit var tvSplash2: TextView
    private lateinit var imgSplash: ImageView
    private lateinit var btnRetry: Button

    private lateinit var mainActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar= findViewById(R.id.toolbar)
        splashcontainer = findViewById(R.id.splashContainer)
        tvSplash1 = findViewById(R.id.tvSplash1)
        tvSplash2 = findViewById(R.id.tvSplash2)
        imgSplash = findViewById(R.id.ivSplash)
        btnRetry = findViewById(R.id.btnRetry)

        setSupportActionBar(toolbar)

        mainActivity = this

        recyclerView=findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(this)
        layoutManager!!.orientation = LinearLayoutManager.VERTICAL
        adapter = productListAdapter(this,mainActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = adapter

    }

    //menu handling
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
                showRecyclerView()
                adapter!!.getListaProductos(myQueryPe.toString())
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                return true
            }
        })
        return true
    }


    fun showStartSplash(){
        splashcontainer.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        showSplash(this.getDrawable(R.drawable.shoping_basket),
                   this.getString(R.string.splashStartText),
                   this.getString(R.string.splashStartText2),false)
    }

    fun showEmptyResultSplash(){
        recyclerView.visibility = View.GONE
        showSplash(this.getDrawable(R.drawable.search),
                   this.getString(R.string.splashNoResultText),
                   this.getString(R.string.splashNoResultText2),false)
    }

    fun showErrorSplash(){
        recyclerView.visibility = View.GONE
        showSplash(this.getDrawable(R.drawable.error),
                   this.getString(R.string.splashErrorText),
                   this.getString(R.string.splashErrorText2),true)
    }


    fun showRecyclerView(){
        splashcontainer.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }



    private fun showSplash(img: Drawable,text1: String,text2: String, showBtnRetry: Boolean){
        splashcontainer.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        imgSplash.background = img
        tvSplash1.text = text1
        tvSplash2.text=text2
        if (showBtnRetry){
            btnRetry.visibility = View.VISIBLE
        }else{
            btnRetry.visibility = View.GONE
        }
    }

}
