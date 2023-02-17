package com.example.k_01.lesson1

import android.view.View

data class Data(val id:Int, var age:Int, val weight:Int=20)

enum class TestEnum{
    test1,
    test2,
    test3,
    test4,
}

object DataBase{
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