package com.quynhlm.dev.pnlib.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.quynhlm.dev.pnlib.Database.Db_Helper;
import com.quynhlm.dev.pnlib.Model.TheLoai;

import java.util.ArrayList;

public class loaiSachDao {
    Db_Helper dbHelper;

    public loaiSachDao(Context context) {
        dbHelper = new Db_Helper(context);
    }

    public boolean insert(TheLoai obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", obj.getTenLoai());
        long check = sqLiteDatabase.insert("TheLoai", null, contentValues);
        return check != -1;
    }

    public boolean delete(TheLoai obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String dk[] = {String.valueOf(obj.getMaLoai())};
        long check = sqLiteDatabase.delete("TheLoai", "maLoai = ?", dk);
        return check != -1;
    }

    public boolean update(TheLoai obj) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String dk[] = {String.valueOf(obj.getMaLoai())};
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", obj.getTenLoai());
        long check = sqLiteDatabase.update("TheLoai", contentValues, "maLoai = ?", dk);
        return check != -1;
    }

    public ArrayList<TheLoai> selectAll(String sql, String... selectionArgs) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ArrayList<TheLoai> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, selectionArgs);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                list.add(new TheLoai(cursor.getInt(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
