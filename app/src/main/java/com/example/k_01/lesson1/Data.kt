package com.example.k_01.lesson1

import android.view.View

data class Data(val id:Int, var age:Int, val weight:Int=20)

object DataBase{
    fun getTest():String{
        if (0==0){
            return "test"
        }else{
            return "бред!"
        }

        val impl = object : View.OnClickListener{
            override fun onClick(v: View?) {
            }

        }
    }
}