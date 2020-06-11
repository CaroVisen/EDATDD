package com.example.eda1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.activity_detalle.btn_search
import kotlinx.android.synthetic.main.activity_main.*
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
        txt_desc_desc.text = "TODO: hacer llamada a la api de descripcion y api de imagenes"
        val img:String? = bundle?.getString("img")
        Picasso.get().load(img!!.replace("http", "https")).into(img_desc)
    }

    fun buscar(){
        val intent = Intent(btn_search.context, MainActivity::class.java)
        startActivity(intent)
    }
}
