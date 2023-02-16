package com.example.k_01.lesson1

import android.content.Context
import android.util.AttributeSet
import android.view.View

open class Person constructor(val name:String="defaultName", var age:Int=20) {
    lateinit var  testParam2:String

    fun test(testParam:String){
        val testParam3=testParam2
    }

    constructor(name: String, are: Int, descr:String):this(name, are){
        testParam2 = "qwerty"
    }
}

fun test(testParam:String){

}

class Student(name: String, are: Int) : Person(name, are), View.OnClickListener, Testable1{
    override fun onClick(v: View?) {

    }

}

class Button @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr:Int?=null, defStyleRes: Int?=null){

}