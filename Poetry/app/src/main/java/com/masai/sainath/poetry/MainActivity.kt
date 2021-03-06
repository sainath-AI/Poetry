package com.masai.sainath.poetry

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.masai.sainath.poetry.databinding.ActivityMainBinding
import com.masai.sainath.poetry.adapter.CategoryAdapter
import com.masai.sainath.poetry.modell.CategoryModel
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db:FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= FirebaseFirestore.getInstance()
        db.collection("poetry").addSnapshotListener { value, error ->

            val list = arrayListOf<CategoryModel>()

            val data=value?.toObjects(CategoryModel::class.java)
            list.addAll(data!!)

            binding.recyclerviewCategory.layoutManager=LinearLayoutManager(this)
            binding.recyclerviewCategory.adapter=CategoryAdapter(this,list)

        }


       // val list = arrayListOf<String>("love","romantic","sad","Attitude")

        binding.btnmenu.setOnClickListener {
            if (binding.drawerlayout.isDrawerOpen(Gravity.LEFT)){
                binding.drawerlayout.closeDrawer(Gravity.LEFT)
            }else
            {
                binding.drawerlayout.openDrawer(Gravity.LEFT)
            }
        }




        binding.navigationView.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.share->{
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nInstall this application for poetry\n\n"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=$packageName
                            
                            
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    true
                }
                R.id.more->{
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }
                    true
                }
                R.id.rate->{
                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket = Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                else->{
                    false
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.drawerlayout.isDrawerOpen(Gravity.LEFT)){
            binding.drawerlayout.closeDrawer(Gravity.LEFT)
        }else
        {
            super.onBackPressed()

        }
    }
}