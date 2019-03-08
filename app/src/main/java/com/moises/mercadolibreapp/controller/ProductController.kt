package com.moises.mercadolibreapp.controller

import com.moises.mercadolibreapp.model.imgSearchList

interface ProductController {

    interface View {
        fun onLoadImgSuccessful(imgSrchList : imgSearchList)
        fun onLoadImgFailed(message : String)
    }


    interface Presenter {
        suspend fun loadImg(id: String)
    }
}