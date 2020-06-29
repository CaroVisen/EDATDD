package com.example.eda1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.activity_detalle.btn_search
import kotlinx.android.synthetic.main.activity_detalle.lbl_search
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetalleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)
        btn_search.setOnClickListener { buscar() }
        if(intent.hasExtra("id")){
            var idArticulo = intent.extras!!.getString("id").toString()
            var tituloArticulo = intent.extras!!.getString("titulo").toString()
            var imgArticulo = intent.extras!!.getString("img")
            var precioArticulo = intent.extras!!.getString("price").toString()
            getDetalle(idArticulo,tituloArticulo,imgArticulo,precioArticulo)
        }
    }

    private fun getAndShowData(detalleDeArticulo:String){
        val bundle = intent.extras
        txt_desc_titulo.text = bundle?.getString("titulo")
        val price:String? = bundle?.getString("price")
        txt_desc_price.text = "$" + "$price"
        txt_desc_desc.text = detalleDeArticulo
        val img:String? = bundle?.getString("img")
        Picasso.get().load(img!!.replace("http", "https")).into(img_desc)

    }

    private fun buscar(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("SEARCH_TERM", lbl_search.text.toString())
        startActivity(intent)
        finish()
    }

    private fun getDetalle(idArticulo:String, tituloArticulo:String,imgArticulo:String?,precioArticulo:String){
        toggleLoading()
        Api().getOneArticleDetalle(idArticulo, object: Callback<ArticuloDetalle> {
            override fun onFailure(call: Call<ArticuloDetalle>, t: Throwable) {
                Toast.makeText(this@DetalleActivity, "Error", Toast.LENGTH_LONG).show()
                toggleLoading()
            }
            override fun onResponse(call: Call<ArticuloDetalle>, response: Response<ArticuloDetalle>) {
                if (response.isSuccessful) {
                    var detalleDeArticulo : String = response.body()!!.plain_text
                    getAndShowData(detalleDeArticulo)
                    toggleLoading()
                }
            }
        })

    }

    private fun toggleLoading() {
        if (progressBarD.isVisible()) {
            cl_detalle.show()
            progressBarD.hide()
        } else {
            cl_detalle.hide()
            progressBarD.show()
        }
    }

}
