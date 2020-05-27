package com.muhammednazeer.bestquotes


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(val context: Context, val categories: List<Category>, val onItemClick: (Int) -> Unit): RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    //This class will be used to either create or recycle views.
    inner class MyViewHolder(itemView: View, val onItemClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView){
        val categoryImage = itemView.findViewById<ImageView>(R.id.imageViewCategoryImage)
        val categoryName = itemView.findViewById<TextView>(R.id.textViewCategoryName)
        fun bindData(category: Category,  context: Context){
            categoryImage.setImageResource(category.resourceId)
            categoryName.text = category.name
            itemView.setOnClickListener {
                onItemClick(category.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false)

        return MyViewHolder(view, onItemClick)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bindData(categories[position], context)
    }

}