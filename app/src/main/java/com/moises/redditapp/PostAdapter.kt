package com.moises.redditapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.moises.redditapp.Utils.Utils
import com.moises.redditapp.model.RedditPostData
import com.moises.redditapp.service.ApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row_post.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class PostAdapter(val context: Context, apiService: ApiService) : RecyclerView.Adapter<PostAdapterViewHolder>(){

    private var apiService: ApiService? = apiService

    private var items: List<RedditPostData>? = null

    var utils: Utils? = Utils()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapterViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_row_post,parent,false)

        return PostAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {

        return if  (items.isNullOrEmpty()) {
            0
        } else{
            items!!.size
        }
    }

    override fun onBindViewHolder(holderPostAdapter: PostAdapterViewHolder, position: Int) {
        var post = items!![position].data

        Picasso.get().load(post.thumbnail).into(holderPostAdapter.itemView.ivImage)
        holderPostAdapter.itemView.tvTitle.text = post.title
        holderPostAdapter.itemView.tvAuthor.text = "Author: " + post.author
        holderPostAdapter.itemView.tvComments.text = post.num_comments.toString()+" comments"

        var str = utils?.lessOrMorethan24hs(utils!!.convertLongToTime(post.created))

        holderPostAdapter.itemView.tvDate.text = str

        holderPostAdapter.itemView.setOnClickListener {

            val intent = Intent(holderPostAdapter.itemView.context,PostDetailActivity::class.java)

            intent.putExtra("titulo",post!!.title)
            intent.putExtra("urlImagen",post!!.url)
            intent.putExtra("author",post!!.author)
            intent.putExtra("num_comments",post!!.num_comments.toString())
            intent.putExtra("created",post!!.created)

            holderPostAdapter.itemView.context.startActivity(intent)
        }
    }


    fun getReditPosts(){
        apiService!!.getAllPost().enqueue(object: Callback<RedditPostData>{
            override fun onResponse(call: Call<RedditPostData>?, response: Response<RedditPostData>?) {
                var reditPostdata = response!!.body()
                items = reditPostdata!!.data.children
               // Log.i("RedditApp", Gson().toJson(items))
                Log.i("RedditApp", "LLAMO AL SERVICIO Y OBTUVO LOS DATOS PAPAI")
                notifyDataSetChanged()
            }
            override fun onFailure(call: Call<RedditPostData>?, t: Throwable?) {
                t?.printStackTrace()
                notifyDataSetChanged()
            }
        })

    }

}

