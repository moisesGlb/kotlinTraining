package com.moises.mercadolibreapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.moises.mercadolibreapp.R

class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var thumbnail: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvPrice: TextView? = null

    init {
        thumbnail=itemView.findViewById(R.id.ivThumbnail)
        tvTitle=itemView.findViewById(R.id.tvTitle)
        tvPrice=itemView.findViewById(R.id.tvPrice)
    }
}