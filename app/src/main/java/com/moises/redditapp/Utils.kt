package com.moises.redditapp

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*



 class Utils {


    fun convertLongToTime(time: Long): Date {
        val date = Date(time)
        return date
    }

     fun convertdateToString(date: Date): String{
         val format = SimpleDateFormat("yyyy.MM.dd HH:mm")

         return format.format(date)
     }

     fun lessOrMorethan24hs(fechaPublicacion: Date): String{
         var flag = false
         var ret = ""
         val fechaActual: Date = Date()

         Log.d("Utils", "fechaPublicacion : $fechaPublicacion")
         Log.d("Utils", "fechaActual : $fechaActual")

         var diferencia = fechaActual.time - fechaPublicacion.time


         val segsMilli: Long = 1000
         val minsMilli = segsMilli * 60
         val horasMilli = minsMilli * 60
         val diasMilli = horasMilli * 24

         val diasTranscurridos = diferencia / diasMilli
         diferencia = diferencia % diasMilli

         val horasTranscurridos = diferencia / horasMilli
         diferencia = diferencia % horasMilli


         ret = if (horasTranscurridos < 24) "hace $horasTranscurridos horas"
               else "hace $diasTranscurridos dias"



         return ret
     }
}