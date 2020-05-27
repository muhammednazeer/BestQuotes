package com.muhammednazeer.bestquotes

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_quote_details.*

class QuoteDetailsActivity : AppCompatActivity() {

    var quoteCategoryId = 0
    var db: SQLiteDatabase? = null
    var cursor: Cursor? = null
    var quotesAdapter: QuotesAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_details)
        quoteCategoryId = intent.extras?.get("QUOTE_CATEGORY_ID").toString().toInt()
        //Read data from database
        val myQuoteDatabaseHelper = BestQuotesSQLiteOpenHelper(this)
        db = myQuoteDatabaseHelper.readableDatabase
        cursor = db!!.query("" + "quotes", arrayOf("quote"), "category_id=?", arrayOf(quoteCategoryId.toString()),
            null, null, null)

        var listOfQuotes = mutableListOf<String>()
        while (cursor!!.moveToNext()){
            val quote = cursor!!.getString(0)
            listOfQuotes.add(quote)
        }

        //Create an adapter object
        quotesAdapter = QuotesAdapter(this, listOfQuotes){quote ->
            val shareIntent = Intent()
            shareIntent.apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, quote)
                type = "text/plain"
            }
            startActivity(shareIntent)
        }

        //use adapter and layout manager

        recyclerViewQuotes.apply {
            adapter = quotesAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        db!!.close()
        cursor!!.close()
    }
}
