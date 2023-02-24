package com.example.demoapp.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {


    // DataBase Declaration Start
    static final String DB_NAME = "sqlite_db";
    static final int DB_VERSION = 1;
    // DataBase Declaration End

    //Registration Table Declaration Start
    public String REGISTRATION_TABLE = "registrationTbl";
    public String REG_ID_COL= "regId";
    public String REG_NAME_COL= "name";
    public String REG_MOBILE_COL= "mobile";
    public String REG_EMAIL_COL= "email";
    public String REG_PASS_COL="password";
    //Registration Table Declaration End

    //Data Table Declaration Start
    public String DATA_TABLE="dataTbl";
    public String DATA_ID_COL="dataId";
    public String DATA_NAME_COL="name";
    public String DATA_DESC_COL="description";
    public String DATA_IMG_COL="imgPath";
    //Data Table Declaration End

    public DBConnection(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_REGISTRATOPN_TABLE ="CREATE TABLE IF NOT EXISTS "+REGISTRATION_TABLE+"("+REG_ID_COL+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+REG_NAME_COL+" VARCHAR,"+REG_MOBILE_COL+" VARCHAR,"+REG_EMAIL_COL+" VARCHAR,"+REG_PASS_COL+" VARCHAR)";
        String CREATE_DATA_TABLE ="CREATE TABLE IF NOT EXISTS "+DATA_TABLE+"("+DATA_ID_COL+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+DATA_NAME_COL+" VARCHAR,"+DATA_DESC_COL+" VARCHAR,"+DATA_IMG_COL+" VARCHAR)";

        //Execute Create Table
        db.execSQL(CREATE_REGISTRATOPN_TABLE);
        db.execSQL(CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
