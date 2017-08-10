package com.ktds.smallsumsettlement.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 2017-07-11.
 */

public class SettlementDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "SETTLEMENT";
    private static final int DB_VERSION = 1;

    public SettlementDB(Context context) {
        this(context,DB_NAME, null, DB_VERSION);
    }

    public SettlementDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer query = new StringBuffer();
        query.append(" CREATE TABLE SETTLE ( ");
        query.append(" ID TEXT PRIMARY KEY NOT NULL, ");
        query.append(" LIMIT INTEGER NOT NULL");
        query.append(" ACCUMULATE INTEGER ");
        query.append(" ) ");

        db.execSQL(query.toString());

        onUpgrade(db,1,DB_VERSION);

        Log.d("DB","TABLE 만들기 완료!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuffer query = new StringBuffer();
        query.append(" INSERT INTO SETTLE ( ");
        query.append(" LIMIT ) ");
        query.append(" VALUES ( ? ) ");

        db.execSQL(query.toString(), new Object[]{300000});

        db.close();

        db.execSQL(query.toString());
    }

    public void paySettlement(int pay){
        StringBuffer query = new StringBuffer();
        query.append(" UPDATE SETTLE SET ");
        query.append(" ACCUMULATE = ACCUMULATE - ? ");


        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query.toString(), new Object[]{ pay });

        db.close();

        db.execSQL(query.toString());
    }
}
