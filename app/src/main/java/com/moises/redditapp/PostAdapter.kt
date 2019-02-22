package com.moises.redditapp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moises.redditapp.service.RedditPostData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row_post.view.*
import java.util.*


class PostAdapter(val context: Context) : RecyclerView.Adapter<PostAdapter.ViewHolder>(){

    var items: List<RedditPostData> = ArrayList<RedditPostData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_row_post,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var post = items[position].data

       holder.bindValues(post)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        var postActual: RedditPostData? = null

        init {

            itemView.setOnClickListener {

                val intent = Intent(itemView.context,PostDetailActivity::class.java)

                intent.putExtra("titulo",postActual!!.title)
                intent.putExtra("urlImagen",postActual!!.url)
                intent.putExtra("author",postActual!!.author)
                intent.putExtra("num_comments",postActual!!.num_comments.toString())
                intent.putExtra("created",postActual!!.created)

                itemView.context.startActivity(intent)
            }

        }

        fun bindValues(post: RedditPostData) {

            Picasso.get().load(post.thumbnail).into(itemView.ivImage)
            itemView.tvTitle.text = post.title
            itemView.tvAuthor.text = "Author: " + post.author
            itemView.tvComments.text = post.num_comments.toString()+" comments"

            var utils: Utils? = Utils()

            var str = utils?.lessOrMorethan24hs(utils?.convertLongToTime(post.created))

            itemView.tvDate.text = str


            postActual = post
        }
    }
}