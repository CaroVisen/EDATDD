package com.example.eda1

import android.widget.ProgressBar
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detalle.*
import kotlinx.android.synthetic.main.activity_main.btn_search
import kotlinx.android.synthetic.main.activity_main.lbl_search

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerview: RecyclerView
    private var mAdapter:RecyclerAdapter=RecyclerAdapter()
    private lateinit var textoBuscador : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_search.setOnClickListener {
            textoBuscador = lbl_search.text.toString()
            buscar() }
        if(intent.hasExtra("SEARCH_TERM")){
            textoBuscador = intent.extras!!.getString("SEARCH_TERM").toString()
            buscar()
        }

    }

    private fun buscar(){
        hideKeyboard()
        toggleLoading()
        Api().getArticle(textoBuscador, object: Callback<ListaArticulos> {
            override fun onFailure(call: Call<ListaArticulos>, t: Throwable) {
                Snackbar.make(mainContainer,"No hay conexion a internet", Snackbar.LENGTH_LONG).show()
                toggleLoading()
            }
            override fun onResponse(call: Call<ListaArticulos>, response: Response<ListaArticulos>) {
                if (response.isSuccessful) {
                    var articulos = response.body()!!.results
                    setUpRecyclerView(articulos)
                }
                toggleLoading()
            }
        })


    }

    private fun setUpRecyclerView(art: List<Articulo>){
        mRecyclerview = findViewById(R.id.rvListaArticulos)
        mRecyclerview.setHasFixedSize(true)
        mRecyclerview.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapter(art)
        mRecyclerview.adapter = mAdapter
    }

    private fun toggleLoading() {
        if (progressBar.isVisible()) {
            rvListaArticulos.show()
            progressBar.hide()
        } else {
            rvListaArticulos.hide()
            progressBar.show()
        }
    }

}
