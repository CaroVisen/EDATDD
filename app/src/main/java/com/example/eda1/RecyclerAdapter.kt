package com.example.eda1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()  {
    private lateinit var articulos: List<Articulo>

    fun RecyclerAdapter(articulos: List<Articulo>){ //constructor
        this.articulos = articulos
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //se fija que elemento del objeto va en que posicion del reciclerview segun su posicion
        val item = articulos.get(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { //crea otro item de la lista de la vista
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_listado, parent, false))
    }

    override fun getItemCount(): Int {
        return articulos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { //adjunta cada dato de una posicion de la lista al item de la vista
        val tituloArticulo = view.findViewById(R.id.txt_titulo) as TextView
        val price = view.findViewById(R.id.txt_price) as TextView
        val imagenArt = view.findViewById(R.id.img_foto) as ImageView
        val irDetalle = view.findViewById(R.id.btn_irDetalle) as Button

        fun bind(articulo: Articulo){
            tituloArticulo.text = articulo.title
            price.text = "$" + articulo.price.toString()
            imagenArt.loadUrl(articulo.thumbnail)
            irDetalle.setOnClickListener {
                val intent = Intent(irDetalle.context, DetalleActivity::class.java)
                intent.putExtra("id", articulo.id)
                intent.putExtra("titulo", articulo.title)
                intent.putExtra("img", articulo.thumbnail)
                intent.putExtra("price", articulo.price.toString())
                startActivity(irDetalle.context,  intent, null)



            }
        }

        private fun ImageView.loadUrl(url: String) {
            Picasso.get().load(url.replace("http", "https")).into(this)
        }
    }
}