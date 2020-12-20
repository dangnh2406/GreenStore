package vn.poly.storegreen.mysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import vn.poly.storegreen.dao.AccountDAO;
import vn.poly.storegreen.dao.BillDAO;
import vn.poly.storegreen.dao.BillDetaisDAO;
import vn.poly.storegreen.dao.ProductDAO;


public class MySQLite extends SQLiteOpenHelper {

    public static final String DATA_NAME = "duan.db";
    public static final int VERSION = 1;


    public MySQLite(@Nullable Context context)
    {
        super(context, DATA_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductDAO.SQL_PRODUCT);
        db.execSQL(BillDAO.SQL_BILL);
        db.execSQL(BillDetaisDAO.SQL_BILL_DETAI);
        db.execSQL(AccountDAO.SQL_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
