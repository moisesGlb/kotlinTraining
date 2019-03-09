package com.moises.mercadolibreapp

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class ProductListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private var thumbnail: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvPrice: TextView? = null

    init {
        thumbnail = itemView.findViewById(R.id.ivImage)
        tvTitle = itemView.findViewById(R.id.tvTitle)
        tvPrice = itemView.findViewById(R.id.tvPrice)
    }

}
