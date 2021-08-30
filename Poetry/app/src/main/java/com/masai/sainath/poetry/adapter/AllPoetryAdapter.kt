package com.masai.sainath.poetry.adapter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.masai.sainath.poetry.BuildConfig
import com.masai.sainath.poetry.PoetryActivity
import com.masai.sainath.poetry.R
import com.masai.sainath.poetry.databinding.ItemPoetryBinding
import com.masai.sainath.poetry.modell.poetryModel
import java.lang.Exception
import android.content.ClipData
import android.content.ClipboardManager

import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService




class AllPoetryAdapter(val poetryActivity: PoetryActivity, val  poetrylist: ArrayList<poetryModel>) : RecyclerView.Adapter<AllPoetryAdapter.poetryViewholder>() {
    class poetryViewholder(val binding:ItemPoetryBinding) : RecyclerView.ViewHolder(binding.root) {




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): poetryViewholder {

    return poetryViewholder(ItemPoetryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: poetryViewholder, position: Int) {
        if  (position%5==0){
            holder.binding.MainColor.setBackgroundResource(R.drawable.gradient)
        }else if(position%5==1){
            holder.binding.MainColor.setBackgroundResource(R.drawable.gradient1)
        }
        else if(position%5==2){
            holder.binding.MainColor.setBackgroundResource(R.drawable.gradient2)
        }
        else if(position%5==3){
            holder.binding.MainColor.setBackgroundResource(R.drawable.gradient3)
        }
        else if(position%5==4){
            holder.binding.MainColor.setBackgroundResource(R.drawable.gradient4)
        }

        holder.binding.btnshare.setOnClickListener {

            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${poetrylist[position].data}\n\n"
                shareMessage =
                    """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                            
                            
                            """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                    poetryActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
            true
        }
        holder.binding.btncopy.setOnClickListener {

            val clipboard: ClipboardManager? =
                poetryActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label",poetrylist[position].data.toString() )
            clipboard?.setPrimaryClip(clip)

            Toast.makeText(poetryActivity,"poetry copied successfully",Toast.LENGTH_SHORT).show()
        }
        holder.binding.btnwhatapp.setOnClickListener {

            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, poetrylist[position].data.toString())
            try {
                poetryActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {
            }

        }

        holder.binding.itemPoetry.text=poetrylist[position].data.toString()
    }

    override fun getItemCount()=poetrylist.size

}