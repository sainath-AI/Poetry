package com.masai.sainath.poetry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class PoetryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poetry)


        val name=intent.getStringExtra("name")
        val id=intent.getStringExtra("id")

        Log.e("poetry","onCreate: name  is $name  and id $id" )
    }
}