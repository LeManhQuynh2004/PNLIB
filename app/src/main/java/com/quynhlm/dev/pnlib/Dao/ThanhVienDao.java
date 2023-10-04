package com.quynhlm.dev.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quynhlm.dev.pnlib.Database.Db_Helper;
import com.quynhlm.dev.pnlib.Model.Sach;
import com.quynhlm.dev.pnlib.Model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDao {
    Db_Helper dbHelper;
    public static final String TABLE_NAME = "ThanhVien";
    public static final String COLUMN_MA = "maTV";
    public static final String COLUMN_TEN = "hoTen";
    public static final String COLUMN_NAMSINH = "namSinh";

    public ThanhVienDao(Context context) {
        dbHelper = new Db_Helper(context);
    }

    public boolean insert(ThanhVien obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN, obj.getHoTen());
        contentValues.put(COLUMN_NAMSINH, obj.getNamSinh());
        long check = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        obj.setMaTV((int) check);
        return check != -1;
    }

    public boolean delete(ThanhVien obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] dk = {String.valueOf(obj.getMaTV())};
        long check = sqLiteDatabase.delete(TABLE_NAME, COLUMN_MA + " = ?", dk);
        return check != -1;
    }

    public boolean update(ThanhVien obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] dk = {String.valueOf(obj.getMaTV())};
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TEN, obj.getHoTen());
        contentValues.put(COLUMN_NAMSINH, obj.getNamSinh());
        long check = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_MA + " = ?", dk);
        return check != -1;
    }

    public ArrayList<ThanhVien> getAll(String sql, String... selectionArgs) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int maTT = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MA));
                String hoTen = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEN));
                String namSinh = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMSINH));
                list.add(new ThanhVien(maTT, hoTen, namSinh));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<ThanhVien> selectAll() {
        String sql = "SELECT * FROM ThanhVien";
        return getAll(sql);
    }

    public ThanhVien selectID(String id) {
        String sql = "SELECT * FROM ThanhVien WHERE maTV = ?";
        ArrayList<ThanhVien> list = getAll(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
