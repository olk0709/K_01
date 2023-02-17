package com.example.k_01.lesson1

import android.util.Log
import android.view.View

data class Data(val id:Int, var age:Int, val weight:Int=20)

enum class TestEnum{
    test1,
    test2,
    test3,
    test4,
}

object DataBase{

    fun getTestCycle(){
        val list = listOf(1,2,3,4,5,6,7)
        for (one in list){
            Log.d("mylogs","$one getTestCycle")
        }
        list.forEach{
            Log.d("mylogs","$it getTestCycle")
        }

        list.forEach{ one: Int ->
            Log.d("mylogs","$one getTestCycle")
        }

        repeat(10){
            Log.d("mylogs","$it getTestCycle")
        }

        val test = 1..10
        for(i in 1..10){
            Log.d("mylogs","$i getTestCycle")
        }

        for(i in 1 until 10){
            Log.d("mylogs","$i getTestCycle")
        }

        for(i in 10 downTo  1){
            Log.d("mylogs","$i getTestCycle")
        }

        for(i in 1 until 10 step 2){
            Log.d("mylogs","$i getTestCycle")
        }

        for(i in 10 downTo  1 step 3){
            Log.d("mylogs","$i getTestCycle")
        }

        var counter = 10
        while (counter -->0){
            Log.d("mylogs","$counter getTestCycle")
        }

        counter = 10
        do {
            Log.d("mylogs","$counter getTestCycle")
        }while (counter -->0)

    }

    fun getTest():String{
        val result = if (0==0){
            return "test"
        }else{
             return "бред!"
        }

        val impl = object : View.OnClickListener{
            override fun onClick(v: View?) {
            }

        }
    }

    fun getTestIf():String{
        val result = if (0==0){
            val f1 = 1 +123
            "test"
        }else{
            "бред!"
        }
        try{

        }catch (e:Throwable){

        }finally {
            //всегда вызывается
        }


        // Всегда возвращает последнюю строку условия
        return result
    }

    fun getTestWhen(input:TestEnum):String{
        val result = when (input){


            //1 -> "1"
            //2 -> "2"
            //else -> "default"
            TestEnum.test1 -> "1"
            TestEnum.test2 -> "2"
            TestEnum.test3 -> "3"
            TestEnum.test4 -> "4"
        }
        return result
    }

}