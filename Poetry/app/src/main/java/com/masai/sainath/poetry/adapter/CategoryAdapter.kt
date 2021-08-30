package com.masai.sainath.poetry.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.poetry.MainActivity
import com.masai.sainath.poetry.PoetryActivity
import com.masai.sainath.poetry.databinding.ItemCategoryBinding
import com.masai.sainath.poetry.modell.CategoryModel
import kotlin.collections.ArrayList

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>)
    : RecyclerView.Adapter<CategoryAdapter.catViewHolder>(){


    val colorList= arrayListOf<String>("#f0932b","#30336b","#535c68","#7ed6df","#be2edd")

    class catViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): catViewHolder {
       return catViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: catViewHolder, position: Int) {

        if  (position%5==0){
            holder.binding.itemtxt.setBackgroundColor(Color.parseColor(colorList[0]))
        }else if(position%5==1){
            holder.binding.itemtxt.setBackgroundColor(Color.parseColor(colorList[1]))
        }
        else if(position%5==2){
            holder.binding.itemtxt.setBackgroundColor(Color.parseColor(colorList[2]))
        }
        else if(position%5==3){
            holder.binding.itemtxt.setBackgroundColor(Color.parseColor(colorList[3]))
        }
        else if(position%5==4){
            holder.binding.itemtxt.setBackgroundColor(Color.parseColor(colorList[4]))
        }


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