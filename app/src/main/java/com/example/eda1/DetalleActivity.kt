package com.example.eda1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.activity_detalle.btn_search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        getAndShowData()
        btn_search.setOnClickListener { buscar() }
    }

    fun getAndShowData(){
        val bundle = intent.extras
        txt_desc_titulo.text = bundle?.getString("titulo")
        val price:String? = bundle?.getString("price")
        txt_desc_price.text = "$" + "$price"
        txt_desc_desc.text = bundle?.getString("descripcion")
        val img:String? = bundle?.getString("img")
        Picasso.get().load(img!!.replace("http", "https")).into(img_desc)

    }

    fun buscar(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("SEARCH_TERM", lbl_search.text.toString())
        startActivity(intent)
        finish()
    }


}
