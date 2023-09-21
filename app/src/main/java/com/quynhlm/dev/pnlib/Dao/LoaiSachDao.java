package com.quynhlm.dev.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quynhlm.dev.pnlib.Database.Db_Helper;
import com.quynhlm.dev.pnlib.Model.TheLoai;

import java.util.ArrayList;

public class LoaiSachDao {
    private Db_Helper dbHelper;

    private static final String TABLE_NAME = "TheLoai";
    private static final String COLUMN_MA_LOAI = "maLoai";
    private static final String COLUMN_TEN_LOAI = "tenLoai";

    public LoaiSachDao(Context context) {
        dbHelper = new Db_Helper(context);
    }

    public boolean insert(TheLoai obj) {
        try (SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_TEN_LOAI, obj.getTenLoai());
            long check = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            return check != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(TheLoai obj) {
        try (SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase()) {
            String[] dk = {String.valueOf(obj.getMaLoai())};
            long check = sqLiteDatabase.delete(TABLE_NAME, COLUMN_MA_LOAI + " = ?", dk);
            return check != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(TheLoai obj) {
        try (SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase()) {
            String[] dk = {String.valueOf(obj.getMaLoai())};
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_TEN_LOAI, obj.getTenLoai());
            long check = sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_MA_LOAI + " = ?", dk);
            return check != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<TheLoai> getAll(String sql, String... selectionArgs) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ArrayList<TheLoai> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                int maLoai = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MA_LOAI));
                String tenLoai = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEN_LOAI));
                list.add(new TheLoai(maLoai, tenLoai));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public ArrayList<TheLoai> selectAll() {
        String sql = "SELECT * FROM TheLoai";
        return getAll(sql);
    }
}