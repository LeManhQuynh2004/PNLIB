package com.quynhlm.dev.pnlib.Dao;

import android.content.Context;

import com.quynhlm.dev.pnlib.Database.Db_Helper;

public class SachDao {
    Db_Helper dbHelper;

    public SachDao(Context context) {
        dbHelper = new Db_Helper(context);
    }
}
