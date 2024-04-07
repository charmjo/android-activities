package com.example.crgrocery;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String DBNAME = "CRGrocery.db";
    static final int VERSION = 1;

    // User columns
    public static final String TABLE_USER_NAME = "User";
    public static final String COL_USER_USERNAME = "userName";
    public static final String COL_USER_EMAILID = "emailId";
    public static final String COL_USER_PASSWORD = "password";

    // Stock
    public static final String TABLE_STOCK_NAME = "Stock";
    public static final String COL_STOCK_ITEMCODE = "itemCode";
    public static final String COL_STOCK_ITEMNAME = "itemName";
    public static final String COL_STOCK_QTYSTOCK = "qtyStock";
    public static final String COL_STOCK_PRICE = "price";
    public  static final String COL_STOCK_TAXABLE = "taxable";

    // Sales
    public static final String TABLE_SALES_NAME = "Sales";
    public static final String COL_SALES_ORDERNUM = "orderNumber";
    public static final String COL_SALES_CUSTNAME = "customerName";
    public static final String COL_SALES_CUSTEMAIL = "customerEmail";
    public static final String COL_SALES_QTYSOLD = "qtySold";
    public static final String COL_SALES_DATEOFSALES = "dateOfSales";

    // Purchase
    public static final String TABLE_PURCHASE_NAME = "Purchase";
    public static final String COL_PURCHASE_INVOICENUM = "invoiceNumber";
    public static final String COL_PURCHASE_QTYPURCHASED = "qtyPurchased";
    public static final String COL_PURCHASE_DATEOFPUR = "dateOfPurchase";

    // Creating DB Tables
    static final String CREATE_TABLE_USER = "CREATE TABLE" + TABLE_USER_NAME + " ("
            + COL_USER_USERNAME + "TEXT PRIMARY KEY" +
            " ," + COL_USER_EMAILID + "TEXT NOT NULL" +
            " ," + COL_USER_PASSWORD + "TEXT NOT NULL" +
            ");";
    static final String CREATE_TABLE_STOCK = "CREATE TABLE" + TABLE_STOCK_NAME + " ("
            + COL_STOCK_ITEMCODE + "INTEGER PRIMARY KEY AUTOINCREMENT" +
            " ," + COL_STOCK_ITEMNAME + "TEXT NOT NULL" +
            " ," + COL_STOCK_QTYSTOCK + "INTEGER NOT NULL" +
            " ," + COL_STOCK_PRICE + "REAL NOT NULL" +
            " ," + COL_STOCK_TAXABLE + "INTEGER NOT NULL" +
            ");";
    static final String CREATE_TABLE_SALES = "CREATE TABLE" + TABLE_SALES_NAME + " ("
            + COL_SALES_ORDERNUM + "INTEGER PRIMARY KEY AUTOINCREMENT" +
            " ," + COL_STOCK_ITEMCODE + "INTEGER" +
            " ," + COL_SALES_CUSTNAME + "TEXT NOT NULL" +
            " ," + COL_SALES_CUSTEMAIL + "TEXT NOT NULL" +
            " ," + COL_SALES_QTYSOLD + "INTEGER NOT NULL" +
            " ," + COL_SALES_DATEOFSALES + "TEXT NOT NULL" +
            ");";
    static final String CREATE_TABLE_PURCHASE = "CREATE TABLE" + TABLE_PURCHASE_NAME + " ("
            + COL_PURCHASE_INVOICENUM + "INTEGER PRIMARY KEY AUTOINCREMENT" +
            " ," + COL_STOCK_ITEMCODE + "INTEGER" +
            " ," + COL_PURCHASE_QTYPURCHASED + "TEXT NOT NULL" +
            " ," + COL_PURCHASE_DATEOFPUR + "TEXT NOT NULL" +
            ");";

    // Drop DB Tables
    static final String DROP_TABLE="DROP TABLE IF EXISTS " + TABLE_USER_NAME + ";"
            + "DROP TABLE IF EXISTS " + TABLE_STOCK_NAME + ";"
            + "DROP TABLE IF EXISTS " + TABLE_SALES_NAME + ";"
            + "DROP TABLE IF EXISTS " + TABLE_PURCHASE_NAME + ";";

    public DBHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_STOCK);
        db.execSQL(CREATE_TABLE_SALES);
        db.execSQL(CREATE_TABLE_PURCHASE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
