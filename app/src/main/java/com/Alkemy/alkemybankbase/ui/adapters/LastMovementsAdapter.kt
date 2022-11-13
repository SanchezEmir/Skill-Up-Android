package com.Alkemy.alkemybankbase.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.Alkemy.alkemybankbase.R
import com.Alkemy.alkemybankbase.data.model.ListaMovimientos

class LastMovementsAdapter : RecyclerView.Adapter<LastMovementsAdapter.ViewHolder>() {
    var movimientos: MutableList<ListaMovimientos> = ArrayList()
    lateinit var context: Context
    fun LastMovementsAdapter(movimientos: MutableList<ListaMovimientos>, context: Context) {
        this.movimientos = movimientos
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movimientos.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_movimientos, parent, false))
    }

    override fun getItemCount(): Int {
        return movimientos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val concept = view.findViewById(R.id.Concepttv) as TextView
        val date = view.findViewById(R.id.Datetv) as TextView
        val type = view.findViewById(R.id.Typetv) as TextView
        val amount = view.findViewById(R.id.amounttv) as TextView
        fun bind(movimientos: ListaMovimientos, context: Context) {
            concept.text = movimientos.concept
            date.text = movimientos.date
            type.text = movimientos.type
            amount.text = movimientos.amount.toString()
            itemView.setOnClickListener {
                Toast.makeText(
                    context,
                    movimientos.concept,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}