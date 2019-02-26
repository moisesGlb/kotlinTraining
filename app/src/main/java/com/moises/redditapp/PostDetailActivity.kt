package com.moises.redditapp

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.moises.redditapp.Utils.Utils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_row_post.view.*

class PostDetailActivity : AppCompatActivity() {

    private var imageview: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvComments: TextView? = null
    private var tvDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        tvTitle = findViewById(R.id.tvTitleDetail)
        imageview = findViewById(R.id.imageViewDetail)
        tvAuthor = findViewById(R.id.tvAuthorDetail)
        tvComments = findViewById(R.id.tvCommentsDkketail)
        tvDate = findViewById(R.id.tvDateDetail)


        Log.d("TAG","EMPEZANDO A SETEAR VALORES")

        tvTitle!!.text = intent.extras!!.getString("titulo")



        if (savedInstanceState != null){
            val image = savedInstanceState.getParcelable<Bitmap>("image")
            imageview?.setImageBitmap(image)


        }else{
            Picasso.get().load(intent.extras!!.getString("urlImagen"))
                //.resize(50,50)
                .error(R.drawable.icon)
                .placeholder(R.color.placeholder_grey)
                .into(imageview)
        }



        tvAuthor!!.text = "Author: " + intent.extras!!.getString("author")
        tvComments!!.text = intent.extras!!.getString("num_comments") + " comments"

        var utils = Utils()

        tvDate!!.text = utils.lessOrMorethan24hs(utils.convertLongToTime(intent.extras!!.getLong("created")))


    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

        var btmap = (imageview?.drawable as BitmapDrawable).bitmap

        outState!!.putParcelable("image",btmap)



    }
}
