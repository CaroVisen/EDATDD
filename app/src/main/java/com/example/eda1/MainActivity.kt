package com.example.eda1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var mRecyclerview: RecyclerView
    var mAdapter:RecyclerAdapter=RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search.setOnClickListener { buscar() }
        if(intent.hasExtra("SEARCH_TERM")){
            lbl_search.setText(intent.extras!!.getString("SEARCH_TERM"))
            buscar()
        }
    }

    fun buscar(){

        try {

            Api().getArticle(lbl_search.text.toString(), object: Callback<ListaArticulos> {
                override fun onFailure(call: Call<ListaArticulos>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<ListaArticulos>, response: Response<ListaArticulos>) {
                    if (response.isSuccessful) {
                        var articulos = response.body()!!.results
                        setUpRecyclerView(articulos)
                    }
                }
            })

        }catch (e : Exception){
        }
    }

    fun setUpRecyclerView(art: List<Articulo>){
        mRecyclerview = findViewById(R.id.rvListaArticulos)
        mRecyclerview.setHasFixedSize(true)
        mRecyclerview.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(art)
        mRecyclerview.adapter = mAdapter
    }
}
