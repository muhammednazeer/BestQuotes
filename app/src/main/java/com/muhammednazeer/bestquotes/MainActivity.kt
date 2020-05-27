package com.muhammednazeer.bestquotes

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var db: SQLiteDatabase? = null
    var cursor: Cursor? = null
    var categoriesAdapter: CategoriesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Read all the categories from the database
        val myQuoteDatabaseHelper = BestQuotesSQLiteOpenHelper(this)
        db = myQuoteDatabaseHelper.readableDatabase
        cursor = db!!.query("Quote_Categories", arrayOf("_id", "image_resource_id", "name"), null, null, null, null, null)
        val listOfCategories = mutableListOf<Category>()
        while(cursor!!.moveToNext()){
            val categoryId = cursor!!.getInt(0)
            val categoryResourceId = cursor!!.getInt(1)
            val categoryName = cursor!!.getString(2)
            val category = Category(categoryId, categoryResourceId, categoryName)
            listOfCategories.add(category)
        }
        categoriesAdapter = CategoriesAdapter(this, listOfCategories){categoryId ->
            val intent = Intent(this, QuoteDetailsActivity::class.java)
            intent.putExtra("QUOTE_CATEGORY_ID", categoryId)
            startActivity(intent)
        }
        //Using a layout manager
        val categoriesLayoutManager = GridLayoutManager(this, 2)
        RecyclerViewQuoteCategories.adapter = categoriesAdapter
        RecyclerViewQuoteCategories.layoutManager = categoriesLayoutManager

    }

    override fun onDestroy() {
        super.onDestroy()
        db!!.close()
        cursor!!.close()
    }
}
