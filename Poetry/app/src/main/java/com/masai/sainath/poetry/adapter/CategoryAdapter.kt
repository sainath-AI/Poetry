package com.masai.sainath.poetry.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.poetry.MainActivity
import com.masai.sainath.poetry.PoetryActivity
import com.masai.sainath.poetry.databinding.ItemCategoryBinding
import com.masai.sainath.poetry.modell.CategoryModel
import kotlin.collections.ArrayList

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.catViewHolder>(){
    class catViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catViewHolder {
       return catViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: catViewHolder, position: Int) {
holder.binding.itemtxt.text=list[position].name.toString()
        holder.binding.root.setOnClickListener {

            val intent =Intent(mainActivity,PoetryActivity::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name)
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount()=list.size

}