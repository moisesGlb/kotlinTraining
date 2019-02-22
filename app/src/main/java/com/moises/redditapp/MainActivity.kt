package com.moises.redditapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.moises.redditapp.service.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var adapter: PostAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Log.d("Nombre clase: ", "Probando logs")

        swipeRefreshLayout = findViewById(R.id.swipePost)


        adapter = PostAdapter(this)

        rvPost.layoutManager = LinearLayoutManager(this)

        rvPost!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        rvPost.adapter = adapter



       swipeRefreshLayout!!.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            // Esto se ejecuta cada vez que se realiza el gesto
           loadData()
        })
    }

    private fun setDataInRecyclerView(it: RedditPostData?) {

        var listaChildrens: List<RedditPostData> = it!!.data.children

        adapter?.items = listaChildrens

        adapter?.notifyDataSetChanged()

    }

    private fun loadData(){

        val mAPIClient by lazy {

            APIClient.create()

        }

        mAPIClient.getPost().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe({
                it ->

            setDataInRecyclerView(it)

        }, { it ->
            Toast.makeText(this,"algo revento: ", Toast.LENGTH_SHORT).show()
            Log.d("revento", it.localizedMessage)
        })

    }

    override fun onResume() {
        super.onResume()

        loadData()
    }


}




