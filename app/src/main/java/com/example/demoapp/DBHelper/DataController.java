package com.example.demoapp.DBHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.example.demoapp.model.DataModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataController {

    private DBConnection dbCon;
    private Context context;
    private SQLiteDatabase db;
    private Cursor ds;

    byte[] imageInbytes;
    private Object Context;
    public DataController(Context context) {
        this.context = context;
    }

    public DataController Open() {
        dbCon = new DBConnection(context);
        db = dbCon.getWritableDatabase();
        return this;
    }

    public void Close() {
        dbCon.close();
    }


    //Insert Data Function
    public long insert(DataModel model) {
        long ReturnVal = 0;
        Open();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(dbCon.DATA_NAME_COL, model.getName());
            values.put(dbCon.DATA_DESC_COL, model.getDesc());
//            values.put(dbCon.DATA_IMG_COL, model.getImg_path());

            ReturnVal = db.insert(dbCon.DATA_TABLE, null, values);
            db.setTransactionSuccessful();

        } catch (Exception ex) {
            Log.d(ex.getMessage(), ex.getLocalizedMessage());
        } finally {
            db.endTransaction();
            Close();
            return ReturnVal;
        }
    }



    // Get Id data Function
    public DataModel DataSingel(int dataId) {
        DataModel model = new DataModel(0, "", "");
        Open();
        db.beginTransaction();
        try {
            String Param[] = new String[]{
                    String.valueOf(dataId)
            };
            ds = db.rawQuery("Select * from " + dbCon.DATA_TABLE + " Where dataId=?", Param);
            if (ds.moveToFirst()) {
                @SuppressLint("Range") int DataId = ds.getInt(ds.getColumnIndex(dbCon.DATA_ID_COL));
                @SuppressLint("Range") String name = ds.getString(ds.getColumnIndex(dbCon.DATA_NAME_COL));
                @SuppressLint("Range") String desc = ds.getString(ds.getColumnIndex(dbCon.DATA_DESC_COL));
//                @SuppressLint("Range") String imgpath = ds.getString(ds.getColumnIndex(dbCon.DATA_IMG_COL));
                model = new DataModel(DataId, name, desc);

            }
            db.setTransactionSuccessful();
        } catch (Exception ex) {
            Log.d(ex.getMessage(), ex.getLocalizedMessage());
        } finally {
            db.endTransaction();
            Close();
            if (ds != null && !ds.isClosed()) {
                ds.close();
            }
            return model;
        }
    }

    //List Data
    public List<DataModel> ListAll() {
        List<DataModel> modelList = new ArrayList<>();
        Open();
        db.beginTransaction();
        try {
            ds = db.rawQuery("Select * from " + dbCon.DATA_TABLE, null);
            if (ds.moveToFirst()) {
                do {
                    @SuppressLint("Range") int DataId = ds.getInt(ds.getColumnIndex(dbCon.DATA_ID_COL));
                    @SuppressLint("Range") String name = ds.getString(ds.getColumnIndex(dbCon.DATA_NAME_COL));
                    @SuppressLint("Range") String desc = ds.getString(ds.getColumnIndex(dbCon.DATA_DESC_COL));
//                    @SuppressLint("Range") String imgpath = ds.getString(ds.getColumnIndex(dbCon.DATA_IMG_COL));
                    DataModel model = new DataModel(DataId, name, desc);
                    modelList.add(model);
                } while (ds.moveToNext());
            }

        } catch (Exception ex) {
            Log.d(ex.getMessage(), ex.getLocalizedMessage());
        } finally {
            db.endTransaction();
            Close();
            if (ds != null && !ds.isClosed()) {
                ds.close();
            }
            return modelList;
        }
    }
}
