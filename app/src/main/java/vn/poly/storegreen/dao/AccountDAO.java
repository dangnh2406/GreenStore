package vn.poly.storegreen.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import vn.poly.storegreen.model.User;
import vn.poly.storegreen.mysqlite.MySQLite;

import java.util.ArrayList;
import java.util.List;


public class AccountDAO {
    MySQLite mySQLite;
    SQLiteDatabase sqLiteDatabase;

    public AccountDAO(Context context) {
        mySQLite = new MySQLite(context);
        sqLiteDatabase = mySQLite.getWritableDatabase();
    }

    public static final String TABLE_ACCOUNT = "account";
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";

    public static final String SQL_ACCOUNT = "create table "
            + TABLE_ACCOUNT + " (" + COL_USERNAME + " text primary key  , "
            + COL_PASSWORD + " text , "
            + COL_EMAIL + " text )";

    public boolean insertAccount(User user) {
        SQLiteDatabase sqLiteDatabase = mySQLite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USERNAME, user.account);
        contentValues.put(COL_PASSWORD, user.passWord);
        contentValues.put(COL_EMAIL, user.email);

        long kq = sqLiteDatabase.insert(TABLE_ACCOUNT, null, contentValues);
        if (kq > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllAcount() {
        List<User> list = new ArrayList<>();
        String query = "select * from " + TABLE_ACCOUNT;
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = new User();
                user.account = cursor.getString(cursor.getColumnIndex(COL_USERNAME));
                user.passWord = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));
                user.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                list.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }

    public List<User> getAcc(String id) {
        List<User> list = new ArrayList<>();
        String query = "select * from " + TABLE_ACCOUNT +" where "+COL_USERNAME +" = "+"' "+id+" '";
        Cursor cursor = mySQLite.getReadableDatabase().rawQuery(query, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = new User();
                user.account = cursor.getString(cursor.getColumnIndex(COL_USERNAME));
                user.passWord = cursor.getString(cursor.getColumnIndex(COL_PASSWORD));
                user.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL));
                list.add(user);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return list;
    }


    public Boolean checkUserPass(String timUsername, String timMk) {

        String sql = "SELECT * FROM " + TABLE_ACCOUNT + " WHERE " + COL_USERNAME + " = '" + timUsername + "' AND " +
                COL_PASSWORD + " = '" + timMk + "'";

        Cursor cursor = mySQLite.getWritableDatabase().rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }


}
