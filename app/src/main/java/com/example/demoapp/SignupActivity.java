package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demoapp.DBHelper.RegistrationController;
import com.example.demoapp.model.RegistrationModel;

public class SignupActivity extends AppCompatActivity {

    TextView lbl_signin;
    EditText txt_regName, txt_regMobile, txt_regEmail, txt_regPassword;
    Button btn_signUp;
    private RegistrationController controller;
    private RegistrationModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        lbl_signin = findViewById(R.id.lbl_signIn);
        txt_regEmail = findViewById(R.id.txt_regEmail);
        txt_regPassword = findViewById(R.id.txt_regPassword);
        txt_regName = findViewById(R.id.txt_regName);
        txt_regMobile = findViewById(R.id.txt_regPhone);
        btn_signUp = findViewById(R.id.btn_signUp);

        lbl_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    String name = txt_regName.getText().toString();
                    int mobile = Integer.parseInt(txt_regMobile.getText().toString());
                    String email = txt_regEmail.getText().toString();
                    String password = txt_regPassword.getText().toString();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(Character.toString((char) mobile)) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                        Toast.makeText(getApplicationContext(), "All Are Fields Required.", Toast.LENGTH_LONG).show();
                    } else {

                        controller = new RegistrationController(getApplicationContext());
                        int RegId = controller.UserCheck(email);
                        if (RegId > 0) {
                            Toast.makeText(getApplicationContext(), "User Already Registred.", Toast.LENGTH_SHORT).show();
                        } else {
                            model = new RegistrationModel(0, name, mobile, email, password);
                            controller = new RegistrationController(getApplicationContext());
                            long RetVal = controller.insert(model);
                            if (RetVal > 0) {
                                Toast.makeText(getApplicationContext(), "Successfully Registrated.", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(SignupActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}