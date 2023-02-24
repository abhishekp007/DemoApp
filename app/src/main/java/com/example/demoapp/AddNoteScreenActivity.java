package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.demoapp.DBHelper.DBConnection;
import com.example.demoapp.DBHelper.DataController;
import com.example.demoapp.model.DataModel;

public class AddNoteScreenActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    Bitmap bitmap;
    byte[] bitimg;
    private Uri imageFilePath;
    private Bitmap imageTostore;

    EditText txt_dataName, txt_dataDesc;
    ImageView Img_DataImage;
    Button btn_saveData;

    private DataController controller;
    private DataModel model;
    DBConnection DBCON;
    private int DataId = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_screen);

        getSupportActionBar().hide();
        DBCON = new DBConnection(getApplicationContext());
        controller = new DataController(getApplicationContext());

        txt_dataName = findViewById(R.id.txt_dataName);
        txt_dataDesc = findViewById(R.id.txt_dataDesc);
        Img_DataImage = findViewById(R.id.img_dataImage);
        btn_saveData = findViewById(R.id.btn_dataSave);

        btn_saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    String name = txt_dataName.getText().toString();
                    String desc = txt_dataDesc.getText().toString();
                    String imgPath = imageFilePath.getPath();
                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc) ) {
                        Toast.makeText(getApplicationContext(), "TEXTBOX IS EMPTY....!!", Toast.LENGTH_SHORT).show();
                    } else {
                        model = new DataModel(DataId, name, desc);

//                        boolean retVal = controller.insert(DataId,name,desc,imageFilePath);

                        long retVal = controller.insert(model);
                        if (retVal > 0) {
                            Toast.makeText(getApplicationContext(), "SuccessFully Insert Your Data..!!", Toast.LENGTH_SHORT).show();

                            //Clear TextBox
                            txt_dataName.setText("");
                            txt_dataDesc.setText("");
                        } else {
                            Toast.makeText(getApplicationContext(), "Somethings Wrong in Your Code.", Toast.LENGTH_SHORT).show();
                        }

                    }
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        Img_DataImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK);
//                gallery.setType("image/*");
//                gallery.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(gallery,"Select Image"));
                gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery,PICK_IMAGE);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode ,int resultCode, @NonNull Intent data)
    {
        try {
            super.onActivityResult(requestCode,resultCode,data);
            if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null)
            {
                imageFilePath=data.getData();
//                imageTostore= MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);
//                Img_DataImage.setImageBitmap(imageTostore);
                Img_DataImage.setImageURI(imageFilePath);

                Toast.makeText(this, imageFilePath.toString(), Toast.LENGTH_SHORT).show();

            }


        }catch (Exception ex)
        {
            Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            txt_dataDesc.setText(ex.toString());
            Log.d("Error",ex.toString());
        }
    }



}