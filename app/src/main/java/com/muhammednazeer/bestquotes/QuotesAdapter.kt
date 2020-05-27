package com.muhammednazeer.bestquotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuotesAdapter (val context : Context, val quotes : List<String>, val onItemLongClick: (String) -> Unit) : RecyclerView.Adapter<QuotesAdapter.QuoteHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quote_item, parent, false)
        return QuoteHolder(view, onItemLongClick)
    }

    override fun getItemCount(): Int {
        return quotes.count()
    }

    override fun onBindViewHolder(holder: QuoteHolder, position: Int) {
        holder.bindQuotes(quotes[position])
    }

    inner class QuoteHolder(itemView: View?, onItemLongClick: (String) -> Unit) : RecyclerView.ViewHolder(itemView!!){
        val quotesText = itemView?.findViewById<TextView>(R.id.textViewQuote)

        fun bindQuotes(quote: String){
            quotesText?.text = quote
            itemView.setOnLongClickListener {
                onItemLongClick(quote)
                true
            }
        }

    }
}