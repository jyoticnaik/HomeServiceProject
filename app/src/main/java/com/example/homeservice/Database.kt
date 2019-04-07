package com.example.homeservice

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log

import java.util.ArrayList

class Database(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    val cart: ArrayList<Service>
        get() {
            val db = writableDatabase
            val qb = SQLiteQueryBuilder()

            val sqlSelect = arrayOf("_serviceID", "_serviceName", "_servicePrice", "_serviceTotal")

            qb.tables = TABLE_SERVICE
            val c = qb.query(db, sqlSelect, null, null, null, null, null)

            val result = ArrayList<Service>()
            if (c.moveToFirst()) {
                do {
                    result.add(
                         Service(
                            c.getString(c.getColumnIndex("_serviceName")),
                            c.getString(c.getColumnIndex("_servicePrice")),
                            c.getString(c.getColumnIndex("_serviceTotal"))
                        )
                    )
                } while (c.moveToNext())
            }
            return result
        }

    override fun onCreate(db: SQLiteDatabase) {
       Log.d("Inside Database","Done")
        val query = "CREATE TABLE " + TABLE_SERVICE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SERVICENAME + " TEXT, " +
                COLUMN_SERVICEPRICE + " TEXT, " +
                COLUMN_SERVICETOTAL + " TEXT " + ");"
        db.execSQL(query)
    }

    fun getAllData(): Cursor {
        val db = this.writableDatabase
        return db.rawQuery("select * from $TABLE_SERVICE", null)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SERVICE")
        onCreate(db)
    }

    fun addToCart(service: Service) {
        val db = writableDatabase
        Log.d("Inside aadtocart","Done")


        val contentValues = ContentValues()
        contentValues.put(COLUMN_SERVICENAME, service.getserviceName())
        contentValues.put(COLUMN_SERVICEPRICE, service.getservicePrice())
        contentValues.put(COLUMN_SERVICETOTAL, service.getserviceTotal())
        db.insert(TABLE_SERVICE, null, contentValues)
    }

    fun cleanCart() {
        val db = writableDatabase
        val query = String.format("DELETE FROM $TABLE_SERVICE")
        db.execSQL(query)
    }

    fun cancleService(_serviceName: String?) {
        val db = writableDatabase
        db.execSQL(
            "DELETE FROM " +
                    TABLE_SERVICE + " WHERE " +
                    COLUMN_SERVICENAME + "=\"" +
                    _serviceName + "\";"
        )
        db.close()
    }

    companion object {

        val DATABASE_VERSION = 1
        private val DATABASE_NAME = "HomeServiceDatabase.db"
        private val TABLE_SERVICE = "Service"
        private val COLUMN_ID = "_serviceID"
        private val COLUMN_SERVICENAME = "_serviceName"
        private val COLUMN_SERVICEPRICE = "_servicePrice"
        private val COLUMN_SERVICETOTAL = "_serviceTotal"
    }
}
