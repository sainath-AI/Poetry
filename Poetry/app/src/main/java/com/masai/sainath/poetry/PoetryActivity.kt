package com.masai.sainath.poetry

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.poetry.adapter.AllPoetryAdapter
import com.masai.sainath.poetry.databinding.ActivityPoetryBinding
import com.masai.sainath.poetry.modell.poetryModel

class PoetryActivity : AppCompatActivity() {

    lateinit var binding: ActivityPoetryBinding

    lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPoetryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name=intent.getStringExtra("name")
        val id=intent.getStringExtra("id")

        db = FirebaseFirestore.getInstance()


        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.CatName.text=name.toString()


        db.collection("poetry").document(id!!).collection("all").addSnapshotListener { value, error ->
            val poetrylist= arrayListOf<poetryModel>()
            val data=value?.toObjects(poetryModel::class.java)
            poetrylist.addAll(data!!)

            binding.rcvAllpoetry.layoutManager=LinearLayoutManager(this)
            binding.rcvAllpoetry.adapter=AllPoetryAdapter(this,poetrylist)

        }




    }
}