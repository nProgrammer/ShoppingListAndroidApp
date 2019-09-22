package pl.norbertwagner.nprogrammer.shoppinglist

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*

class ItemViewAdapter(val context: Context, val db: SQLiteDatabase): RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView_item = layoutInflater.inflate(R.layout.item_view, parent, false)
        return MyViewHolder(itemView_item)
    }

    override fun getItemCount(): Int {
        val cursor = db.query(TableInfo.TABLE_NAME, null,
            null, null, null,null,null)
        val numberOfItems = cursor.count
        cursor.close()
        return numberOfItems
    }

    @SuppressLint("Recycle")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val itemView = holder.view.cardView
        val product = holder.view.productView
        val date = holder.view.dateView

        val cursor = db.query(TableInfo.TABLE_NAME, null,
            BaseColumns._ID + "=?", arrayOf(holder.adapterPosition.plus(1).toString()),
                null,null,null)

        if(cursor.moveToFirst()){
            if(!cursor.getString(2).isNullOrEmpty()){
                product.text = cursor.getString(2)
                date.text = cursor.getString(1)
            }
        }
    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)