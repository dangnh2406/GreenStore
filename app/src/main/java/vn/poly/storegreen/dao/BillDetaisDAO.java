package vn.poly.storegreen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import vn.poly.storegreen.model.BillDetai;
import vn.poly.storegreen.mysqlite.MySQLite;

import java.util.ArrayList;
import java.util.List;

public class BillDetaisDAO {
    MySQLite mySQLite;
    SQLiteDatabase sqLiteDatabase;


    public BillDetaisDAO(Context context) {
        mySQLite = new MySQLite(context);
        sqLiteDatabase = mySQLite.getWritableDatabase();
    }

    public static final String TABLE_BILL_DETAIS = "billdetais";
    public static final String COL_ID_BILL = "idBill";
    public static final String COL_ID_PRODUCT = "idProducct";
    public static final String COL_QUANTITY_PURCHASED = "quantityPurchased";
    public static final String COL_PRICE_PRODUCT = "priceProduct";
    public static final String COL_TOTAL = "total";


    public static final String SQL_BILL_DETAI = "create table " + TABLE_BILL_DETAIS + " (" + COL_ID_BILL + " text , "
            + COL_ID_PRODUCT + " text , "
            + COL_QUANTITY_PURCHASED + " double , "
            + COL_PRICE_PRODUCT + " double , "
            + COL_TOTAL + " double ) ";


    public boolean insertBildetai(BillDetai billDetai) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_PRODUCT, billDetai.IdProduct);
        contentValues.put(COL_ID_BILL, billDetai.idBill);
        contentValues.put(COL_PRICE_PRODUCT, billDetai.importPrices);
        contentValues.put(COL_QUANTITY_PURCHASED, billDetai.quantityPurchased);
        contentValues.put(COL_TOTAL, billDetai.total);
        long kq = sqLiteDatabase.insert(TABLE_BILL_DETAIS, null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<BillDetai> getAlList(String id) {
        List<BillDetai> billDetais = new ArrayList<>();
        String query = "select " + BillDAO.TABLE_BILL + "." + BillDAO.COL_ID_BILL + " , " + ProductDAO.TABLE_NAME + "."
                + ProductDAO.COL_PRODUCT_NAME +
                " , " + BillDetaisDAO.TABLE_BILL_DETAIS + "." + BillDetaisDAO.COL_QUANTITY_PURCHASED + " , " + BillDetaisDAO.TABLE_BILL_DETAIS + "."
                + BillDetaisDAO.COL_PRICE_PRODUCT +
                " , " + BillDAO.TABLE_BILL + "." + BillDAO.COL_TOTAL + " from " + BillDetaisDAO.TABLE_BILL_DETAIS +
                " inner join " + BillDAO.TABLE_BILL + " on " + BillDetaisDAO.TABLE_BILL_DETAIS + "." + BillDetaisDAO.COL_ID_BILL + " = " + BillDAO.TABLE_BILL + "." + BillDAO.COL_ID_BILL + " inner join "
                + ProductDAO.TABLE_NAME + " on " + ProductDAO.TABLE_NAME + "."
                + ProductDAO.COL_PRODUCT_NAME + " = " + BillDetaisDAO.TABLE_BILL_DETAIS + "." + BillDetaisDAO.COL_ID_PRODUCT + " where " + BillDAO.TABLE_BILL + "." + BillDAO.COL_ID_BILL + " = '" + id + "'";
        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                BillDetai billDetai = new BillDetai();
                billDetai.setIdBill(cursor.getString(cursor.getColumnIndex(BillDAO.COL_ID_BILL)));
                billDetai.setIdProduct(cursor.getString(cursor.getColumnIndex(ProductDAO.COL_PRODUCT_NAME)));
                billDetai.setImportPrices(cursor.getDouble(cursor.getColumnIndex(BillDetaisDAO.COL_PRICE_PRODUCT)));
                billDetai.setTotal(cursor.getDouble(cursor.getColumnIndex(BillDAO.COL_TOTAL)));
                billDetai.setQuantityPurchased(cursor.getDouble(cursor.getColumnIndex(BillDetaisDAO.COL_QUANTITY_PURCHASED)));
                billDetais.add(billDetai);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return billDetais;
    }


}
