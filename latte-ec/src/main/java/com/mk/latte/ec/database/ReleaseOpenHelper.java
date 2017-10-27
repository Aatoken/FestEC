package com.mk.latte.ec.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * @author lenovo
 * @data 2017/10/27
 */

public class ReleaseOpenHelper  extends  DaoMaster.OpenHelper{


    public ReleaseOpenHelper(Context context, String name) {
        super(context, name);
    }

    public ReleaseOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }
}
