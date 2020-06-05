package com.example.eda1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {
    lateinit var articulos: List<Articulo>
    lateinit var context: Context

    fun RecyclerAdapter(articulos: List<Articulo>, context: MainActivity){ //constructor
        this.articulos = articulos
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //se fija que elemento del objeto va en que posicion del reciclerview segun su posicion
        val item = articulos.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //crea otro item de la lista de la vista
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.activity_listado, parent, false))
    }

    override fun getItemCount(): Int {
        return articulos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { //adjunta cada dato de una posicion de la lista al item de la vista
        val tituloArticulo = view.findViewById(R.id.txt_titulo) as TextView
        val price = view.findViewById(R.id.txt_price) as TextView
        val imagenArt = view.findViewById(R.id.img_foto) as ImageView

        fun bind(articulo: Articulo, context: Context){
            tituloArticulo.text = articulo.title
            price.text = articulo.price.toString()
            imagenArt.loadUrl(articulo.thumbnail)
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url.replace("http", "https")).into(this)
        }
    }
}