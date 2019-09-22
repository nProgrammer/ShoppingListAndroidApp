package pl.norbertwagner.nprogrammer.shoppinglist

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

        recycler_view.layoutManager = GridLayoutManager(applicationContext, 2)
        recycler_view.adapter = ItemViewAdapter(applicationContext, db)

        saveButton.setOnClickListener{
            val product = itemUserText.text.toString()
            val dateTime = LocalDateTime.now()
            val date = dateTime.format(DateTimeFormatter.ofPattern("M/d/y"))
            if(!product.isNullOrEmpty()) {
                val value = ContentValues()
                value.put("product", product)
                value.put("date", date)
                db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
                refresh(db)
            }else{
                Toast.makeText(applicationContext, "Nie podałeś produktu", Toast.LENGTH_SHORT)
            }
        }
    }
    fun refresh(db: SQLiteDatabase){
        recycler_view.layoutManager = GridLayoutManager(applicationContext, 2)
        recycler_view.adapter = ItemViewAdapter(applicationContext, db)
    }
}