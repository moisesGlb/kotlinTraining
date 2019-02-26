package com.moises.redditapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.moises.redditapp.service.ApiService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var adapter: PostAdapter? = null

    private val apiServiceImp = com.moises.redditapp.service.ApiServiceImp()

    private var apiService: ApiService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)

        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvPost.layoutManager = layoutManager

        rvPost!!.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        apiService = apiServiceImp.commonServiceCall()

        adapter = PostAdapter(this, apiService!!)

        rvPost.adapter = adapter




        adapter!!.getReditPosts()

        // Logica pull to refreshh
       /* swipeRefreshLayout!!.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            adapter!!.getReditPosts()

        })*/
        // FIN Logica pull to refresh.

    }


}




