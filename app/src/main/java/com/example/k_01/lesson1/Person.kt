package com.example.k_01.lesson1

import android.content.Context
import android.util.AttributeSet
import android.view.View

open class Person constructor(val name:String="defaultName", var age:Int=20) {
    lateinit var  testParam2:String

    fun test(testParam:String){
        testParam2 ="weee"
        val testParam3=testParam2

        val data1 = Data(1,1,1)
        val data2 = data1
        data2.age = 100
    }
    var p:String = ""
    get() {
        return field
    }
    set(p0:String) {
        field = p0
    }

    constructor(name: String, are: Int, descr:String):this(name, are){
        testParam2 = "qwerty"
    }
}

class Student(name: String, are: Int) : Person(name, are), View.OnClickListener, Testable1{
    override fun onClick(v: View?) {

    }

}

class Button @JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, defStyleAttr:Int?=null, defStyleRes: Int?=null){

}