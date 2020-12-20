package vn.poly.storegreen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import vn.poly.storegreen.field.FuntionClass;
import vn.poly.storegreen.model.Product;
import vn.poly.storegreen.mysqlite.MySQLite;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    MySQLite mySQLite;
    SQLiteDatabase sqLiteDatabase;


    public ProductDAO(Context context) {
        mySQLite = new MySQLite(context);
        sqLiteDatabase = mySQLite.getWritableDatabase();
    }

    public static final String TABLE_NAME = "product";
    public static final String COL_ID_PRODUCT = "id";
    public static final String COL_PRODUCT_NAME = "goodName";
    public static final String COL_IMPORT_PRICES = "importPrices";
    public static final String COL_PRICES = "prices";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_IMG = "img";
    public static final String COL_DESCRIBE = "describe";
    public static final String COL_DATE_ADD = "dayAdd";
    public static final String COL_EXPIRED = "expired";
    public static final String COL_SALE = "sale";

    public static final String SQL_PRODUCT = "create table "
            + TABLE_NAME + " (" + COL_ID_PRODUCT + " text primary key , "
            + COL_PRODUCT_NAME + " text , "
            + COL_IMPORT_PRICES + " double , "
            + COL_PRICES + " double , "
            + COL_AMOUNT + " double , "
            + COL_IMG + " blob ,"
            + COL_DESCRIBE + " text ,"
            + COL_DATE_ADD + " text , "
            + COL_EXPIRED + " text ,"
            + COL_SALE + " integer )";


    public boolean insertProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID_PRODUCT, product.iD);
        contentValues.put(COL_PRODUCT_NAME, product.productName);
        contentValues.put(COL_IMPORT_PRICES, product.importPrices);
        contentValues.put(COL_PRICES, product.prices);
        contentValues.put(COL_AMOUNT, product.amount);
        contentValues.put(COL_DESCRIBE, product.describe);
        contentValues.put(COL_DATE_ADD, product.dateAdded);
        contentValues.put(COL_EXPIRED, product.dateExpiration);
        contentValues.put(COL_IMG, product.img);
        contentValues.put(COL_SALE, product.sale);
        long kq = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if (kq > 0) {

            return true;
        } else {
            return false;
        }


    }


    public long deleteProduct(String Id) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COL_ID_PRODUCT + " = ?", new String[]{Id});

    }


    public List<Product> getAllProduct() {

        List<Product> list = new ArrayList<>();

        String query = "select * from " + TABLE_NAME;

        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Product product = new Product();
                product.iD = cursor.getString(cursor.getColumnIndex(COL_ID_PRODUCT));
                product.productName = cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME));
                product.importPrices = cursor.getDouble(cursor.getColumnIndex(COL_IMPORT_PRICES));
                product.prices = cursor.getDouble(cursor.getColumnIndex(COL_PRICES));
                product.amount = cursor.getDouble(cursor.getColumnIndex(COL_AMOUNT));
                product.img = cursor.getBlob(cursor.getColumnIndex(COL_IMG));
                product.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
                product.dateAdded = cursor.getString(cursor.getColumnIndex(COL_DATE_ADD));
                product.dateExpiration = cursor.getString(cursor.getColumnIndex(COL_EXPIRED));
                product.sale = cursor.getInt(cursor.getColumnIndex(COL_SALE));

                list.add(product);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return list;
    }
    public List<Product> getAllProductForID(String ngayBatDau,String ngayKetThuc) {

        List<Product> list = new ArrayList<>();

        String query = "select * from " + TABLE_NAME +" where "+COL_DATE_ADD + " BETWEEN "+" '"+ngayBatDau+" '" +" and "+ " '"+ngayKetThuc+"'";

        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Product product = new Product();
                product.iD = cursor.getString(cursor.getColumnIndex(COL_ID_PRODUCT));
                product.productName = cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME));
                product.importPrices = cursor.getDouble(cursor.getColumnIndex(COL_IMPORT_PRICES));
                product.prices = cursor.getDouble(cursor.getColumnIndex(COL_PRICES));
                product.amount = cursor.getDouble(cursor.getColumnIndex(COL_AMOUNT));
                product.img = cursor.getBlob(cursor.getColumnIndex(COL_IMG));
                product.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
                product.dateAdded = cursor.getString(cursor.getColumnIndex(COL_DATE_ADD));
                product.dateExpiration = cursor.getString(cursor.getColumnIndex(COL_EXPIRED));
                product.sale = cursor.getInt(cursor.getColumnIndex(COL_SALE));

                list.add(product);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return list;
    }
    public List<Product> getAllProductExpired() {

        List<Product> list = new ArrayList<>();

        String query = "select * from " + TABLE_NAME +" where "+COL_EXPIRED +" < "+"'"+ FuntionClass.getNows()+"'";

        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Product product = new Product();
                product.iD = cursor.getString(cursor.getColumnIndex(COL_ID_PRODUCT));
                product.productName = cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME));
                product.importPrices = cursor.getDouble(cursor.getColumnIndex(COL_IMPORT_PRICES));
                product.prices = cursor.getDouble(cursor.getColumnIndex(COL_PRICES));
                product.amount = cursor.getDouble(cursor.getColumnIndex(COL_AMOUNT));
                product.img = cursor.getBlob(cursor.getColumnIndex(COL_IMG));
                product.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
                product.dateAdded = cursor.getString(cursor.getColumnIndex(COL_DATE_ADD));
                product.dateExpiration = cursor.getString(cursor.getColumnIndex(COL_EXPIRED));
                product.sale = cursor.getInt(cursor.getColumnIndex(COL_SALE));

                list.add(product);
                cursor.moveToNext();

            }
            cursor.close();
        }
        return list;
    }

    public List<Product> searchProduct(String search) {

        List<Product> list = new ArrayList<>();

        String query = "select * from " + TABLE_NAME + " where " + COL_PRODUCT_NAME + " LIKE '" + search + "%'";

        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Product product = new Product();
                product.iD = cursor.getString(cursor.getColumnIndex(COL_ID_PRODUCT));
                product.productName = cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME));
                product.importPrices = cursor.getDouble(cursor.getColumnIndex(COL_IMPORT_PRICES));
                product.prices = cursor.getDouble(cursor.getColumnIndex(COL_PRICES));
                product.amount = cursor.getDouble(cursor.getColumnIndex(COL_AMOUNT));
                product.img = cursor.getBlob(cursor.getColumnIndex(COL_IMG));
                product.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
                product.dateAdded = cursor.getString(cursor.getColumnIndex(COL_DATE_ADD));
                product.dateExpiration = cursor.getString(cursor.getColumnIndex(COL_EXPIRED));
                product.sale = cursor.getInt(cursor.getColumnIndex(COL_SALE));
                list.add(product);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }


    public Product getProduct(String id) {

        String query = "select * from " + TABLE_NAME + " WHERE " + COL_ID_PRODUCT + " = '" + id + "'";
        Product product = new Product();
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                product.iD = cursor.getString(cursor.getColumnIndex(COL_ID_PRODUCT));
                product.productName = cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME));
                product.importPrices = cursor.getDouble(cursor.getColumnIndex(COL_IMPORT_PRICES));
                product.prices = cursor.getDouble(cursor.getColumnIndex(COL_PRICES));
                product.amount = cursor.getDouble(cursor.getColumnIndex(COL_AMOUNT));
                product.img = cursor.getBlob(cursor.getColumnIndex(COL_IMG));
                product.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
                product.dateAdded = cursor.getString(cursor.getColumnIndex(COL_DATE_ADD));
                product.dateExpiration = cursor.getString(cursor.getColumnIndex(COL_EXPIRED));
                product.sale = cursor.getInt(cursor.getColumnIndex(COL_SALE));
                cursor.close();
                return product;

            }
            cursor.close();
        }
        return product;
    }

    public Product checkAmountProduct(String id) {


        String query = "select * from " + TABLE_NAME + " WHERE " + COL_ID_PRODUCT + " = '" + id + "'";
        Product product = new Product();
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                product.iD = cursor.getString(cursor.getColumnIndex(COL_ID_PRODUCT));
                product.productName = cursor.getString(cursor.getColumnIndex(COL_PRODUCT_NAME));
                product.importPrices = cursor.getDouble(cursor.getColumnIndex(COL_IMPORT_PRICES));
                product.prices = cursor.getDouble(cursor.getColumnIndex(COL_PRICES));
                product.amount = cursor.getDouble(cursor.getColumnIndex(COL_AMOUNT));
                product.img = cursor.getBlob(cursor.getColumnIndex(COL_IMG));
                product.describe = cursor.getString(cursor.getColumnIndex(COL_DESCRIBE));
                product.dateAdded = cursor.getString(cursor.getColumnIndex(COL_DATE_ADD));
                product.dateExpiration = cursor.getString(cursor.getColumnIndex(COL_EXPIRED));
                product.sale = cursor.getInt(cursor.getColumnIndex(COL_SALE));
                cursor.close();
                return product;

            }
            cursor.close();
        }
        return product;
    }

    public boolean updateProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PRODUCT_NAME, product.productName);
        contentValues.put(COL_PRICES, product.prices);
        contentValues.put(COL_AMOUNT, product.amount);
        contentValues.put(COL_IMPORT_PRICES, product.importPrices);
        contentValues.put(COL_DATE_ADD, product.dateAdded);
        contentValues.put(COL_DESCRIBE, product.describe);
        contentValues.put(COL_SALE, product.sale);
        long udate = sqLiteDatabase.update(TABLE_NAME, contentValues, COL_ID_PRODUCT + " = ?", new String[]{String.valueOf((product.getiD()))});

        if (udate > 0) {
            return true;
        } else {
            return false;
        }

    }


}
