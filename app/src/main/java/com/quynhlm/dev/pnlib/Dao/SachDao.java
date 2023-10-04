package com.quynhlm.dev.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quynhlm.dev.pnlib.Database.Db_Helper;
import com.quynhlm.dev.pnlib.Model.Sach;
import com.quynhlm.dev.pnlib.Model.TheLoai;

import java.util.ArrayList;

public class SachDao {
    Db_Helper dbHelper;

    private static final String TABLE_NAME = "Sach";
    private static final String COLUMN_MA_SACH = "maSach";
    private static final String COLUMN_TEN_SACH = "tenSach";
    private static final String COLUMN_GIA_THUE = "giaSach";
    private static final String COLUMN_MA_LOAI = "maLoai";


    public SachDao(Context context) {
        dbHelper = new Db_Helper(context);
    }

    public boolean insert(Sach obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN_SACH, obj.getTenSach());
        contentValues.put(COLUMN_GIA_THUE, obj.getGiaThue());
        contentValues.put(COLUMN_MA_LOAI, obj.getMaLoai());
        long check = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        obj.setMaSach((int) check);
        return check != -1;
    }

    public boolean delete(Sach obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] dk = {String.valueOf(obj.getMaSach())};
        long check = sqLiteDatabase.delete(TABLE_NAME, COLUMN_MA_SACH + " = ?", dk);
        return check != -1;
    }

    public boolean update(Sach obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] dk = {String.valueOf(obj.getMaSach())};
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN_SACH, obj.getTenSach());
        contentValues.put(COLUMN_GIA_THUE, obj.getGiaThue());
        contentValues.put(COLUMN_MA_LOAI, obj.getMaLoai());
        long check = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_MA_SACH + " = ?", dk);
        return check != -1;
    }

    private ArrayList<Sach> getAll(String sql, String... selectionArgs) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ArrayList<Sach> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                int maSach = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MA_SACH));
                int maLoai = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MA_LOAI));
                String tenSach = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEN_SACH));
                int giaSach = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_GIA_THUE));
                list.add(new Sach(maSach, tenSach, giaSach, maLoai));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<Sach> selectAll() {
        String sql = "SELECT * FROM Sach";
        return getAll(sql);
    }

    public Sach selectID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach = ?";
        ArrayList<Sach> list = getAll(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
