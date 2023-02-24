package com.example.demoapp.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.demoapp.model.RegistrationModel;

public class RegistrationController {

    private DBConnection dbCon;
    private Context context;
    private SQLiteDatabase db;
    private Cursor ds;

    public RegistrationController(Context context) {
        this.context = context;
    }

    public RegistrationController Open()
    {
        dbCon = new DBConnection(context);
        db = dbCon.getWritableDatabase();
        return this;
    }

    public void Close()
    {
        db.close();
    }

    //CheckUser Function
    public int UserCheck(String Email)
    {
        int RegId = 0;
        Open();
        db.beginTransaction();
        try
        {
            String Param[] = new String[]{
                    Email
            };

            ds = db.rawQuery("Select * from "+dbCon.REGISTRATION_TABLE+" Where email=?",Param);
            if (ds.moveToFirst())
            {
                RegId = ds.getInt(0);
            }
            db.setTransactionSuccessful();
        }catch (Exception ex)
        {
            Log.d(ex.getMessage(),ex.getLocalizedMessage());
        }finally {

            db.endTransaction();
            Close();
            if (ds != null && !ds.isClosed())
            {
                ds.close();
            }

            return RegId;
        }
    }

    //Insert Function
    public long insert(RegistrationModel model)
    {
        long ReturnVal=0;
        Open();
        db.beginTransaction();
        try{
            ContentValues values = new ContentValues();
            values.put(dbCon.REG_NAME_COL,model.getName());
            values.put(dbCon.REG_MOBILE_COL,model.getMobile());
            values.put(dbCon.REG_EMAIL_COL,model.getEmail());
            values.put(dbCon.REG_PASS_COL,model.getPassword());

            ReturnVal = db.insert(dbCon.REGISTRATION_TABLE,null,values);
            db.setTransactionSuccessful();


        }catch (Exception ex)
        {
            Log.d(ex.getMessage(),ex.getLocalizedMessage());
        }
        finally {
            db.endTransaction();
            Close();
            return ReturnVal;
        }
    }

    //Login Function
    public int LoginFun(String Email,String Password)
    {
        int RegId = 0;
        Open();
        db.beginTransaction();
        try
        {
            String Param[] = new String[]{
                    Email,
                    Password
            };
            ds = db.rawQuery("Select * from "+dbCon.REGISTRATION_TABLE+" Where email=? and password=?",Param);
            if (ds.moveToFirst())
            {
                RegId = ds.getInt(0);
            }
            db.setTransactionSuccessful();
        }catch (Exception ex)
        {
            Log.d(ex.getMessage(),ex.getLocalizedMessage());
        }finally {
            db.endTransaction();
            Close();
            if (ds != null && !ds.isClosed())
            {
                ds.close();
            }
            return RegId;
        }
    }
}
