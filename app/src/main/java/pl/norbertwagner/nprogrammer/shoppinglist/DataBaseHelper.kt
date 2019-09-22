package pl.norbertwagner.nprogrammer.shoppinglist

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

object TableInfo: BaseColumns{
    const val TABLE_NAME = "Products"
    const val TABLE_COLUMN_DATE = "date"
    const val TABLE_COLUMN_PRODUCT = "product"
}

object BasicCommands{
    const val SQL_CREATE_TABLE =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_DATE} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_PRODUCT} TEXT NOT NULL)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"
}

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, TableInfo.TABLE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommands.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(BasicCommands.SQL_DELETE_TABLE)
        onCreate(db)
    }
}