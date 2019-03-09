package com.moises.mercadolibreapp.controller

import com.moises.mercadolibreapp.model.ProductDescription
import com.moises.mercadolibreapp.model.imgSearchList

interface ProductController {

    interface View {
        fun onLoadImgSuccessful(imgSrchList : imgSearchList)
        fun onLoadImgFailed(message : String)

        fun onLoadDescriptionSuccessful(productDescription : ProductDescription)
        fun onLoadDescriptionFailed(message : String)
    }


    interface Presenter {
        suspend fun loadImg(id: String)
        suspend fun loadDescription(id: String)
    }
}