package com.example.k_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.k_01.lesson1.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test("")

        Person(name = "anotherName", age=25)

        val data1 = Data(1,1,1)
        val data2 = data1.copy()
        data2.age = 100

        Log.d("mylogs", "${data1.age}")
        Log.d("mylogs", "${data2.age}")
        Log.d("mylogs", "${DataBase.getTest()}")
        Log.d("mylogs", "${DataBase.getTestIf()}")
        Log.d("mylogs", "${DataBase.getTestWhen(TestEnum.test1)}")

        DataBase.getTestCycle()


    }
}