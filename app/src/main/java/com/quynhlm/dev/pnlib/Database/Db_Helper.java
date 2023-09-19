package com.quynhlm.dev.pnlib.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Db_Helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PNLIB.db";
    private static final int DATABASE_VERSION = 1;

    public Db_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tạo bảng thủ thư
        String createTableThuThu = "CREATE TABLE IF NOT EXISTS ThuThu (" +
                "maTT TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        // Tạo bảng thành viên
        String createTableThanhVien = "CREATE TABLE IF NOT EXISTS ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen TEXT NOT NULL, " +
                "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        // Tạo bảng thể loại sách
        String createTableTheLoai = "CREATE TABLE IF NOT EXISTS TheLoai(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(createTableTheLoai);

        // Tạo bảng sách
        String createTableSach = "CREATE TABLE IF NOT EXISTS Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenSach TEXT NOT NULL, " +
                "giaSach INTEGER NOT NULL, " +
                "maLoai INTEGER NOT NULL, " +
                "FOREIGN KEY(maLoai) REFERENCES TheLoai(maLoai))";
        db.execSQL(createTableSach);

        // Tạo bảng phiếu mượn
        String createTablePhieuMuon = "CREATE TABLE IF NOT EXISTS PhieuMuon(" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maTT TEXT NOT NULL, " +
                "maTV INTEGER NOT NULL, " +
                "maSach INTEGER NOT NULL, " +
                "tienThue INTEGER NOT NULL," +
                "ngay DATE NOT NULL," +
                "traSach INTEGER NOT NULL, " +
                "FOREIGN KEY(maTT) REFERENCES ThuThu(maTT), " +
                "FOREIGN KEY(maTV) REFERENCES ThanhVien(maTV), " +
                "FOREIGN KEY(maSach) REFERENCES Sach(maSach))";
        db.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS ThuThu");
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            db.execSQL("DROP TABLE IF EXISTS TheLoai");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            onCreate(db);
        }
    }

}
