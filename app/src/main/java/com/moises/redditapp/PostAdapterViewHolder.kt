package com.moises.redditapp

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PostAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


  var ivImage: ImageView? = null
  var tvTitle: TextView? = null
  var tvComments: TextView? = null
  var tvAuthor: TextView? = null
  var tvDate: TextView? = null

    init {
        ivImage = itemView.findViewById(R.id.ivImage)
        tvTitle = itemView.findViewById(R.id.tvTitle)
        tvComments = itemView.findViewById(R.id.tvComments)
        tvAuthor = itemView.findViewById(R.id.tvAuthor)
        tvDate = itemView.findViewById(R.id.tvDate)
    }

}