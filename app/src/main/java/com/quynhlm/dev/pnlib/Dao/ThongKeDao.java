package com.quynhlm.dev.pnlib.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quynhlm.dev.pnlib.Database.Db_Helper;
import com.quynhlm.dev.pnlib.Model.Sach;
import com.quynhlm.dev.pnlib.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ThongKeDao {
    Db_Helper dbHelper;
    Context context;

    public ThongKeDao(Context context) {
        this.context = context;
        dbHelper = new Db_Helper(context);
    }

    //Thong ke Top 10
    public ArrayList<Top> getTop() {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String sql = "SELECT maSach , Count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        ArrayList<Top> list = new ArrayList<>();
        SachDao sachDao = new SachDao(context);
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            Top top = new Top();
            Sach sach = sachDao.selectID(cursor.getString(cursor.getColumnIndexOrThrow("maSach")));
            top.tenSach = sach.getTenSach();
            top.soLuong = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("soLuong")));
            list.add(top);
        }
        return list;
    }

    public int TkDoanhThu(String tuNgay, String denNgay) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String sql = "SELECT SUM(tienThue) as doanhthu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        String dk[] = {tuNgay, denNgay};
        ArrayList<Integer> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, dk);
        if (cursor.getCount() > 0) {
            try {
                list.add(Integer.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }
}
