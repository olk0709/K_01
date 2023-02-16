package com.example.k_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.k_01.lesson1.Person
import com.example.k_01.lesson1.test

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test("")

        Person(name = "anotherName", age=25)
    }
}