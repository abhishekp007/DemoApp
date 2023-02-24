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

import com.example.demoapp.DBHelper.DBConnection;
import com.example.demoapp.DBHelper.RegistrationController;

public class LoginActivity extends AppCompatActivity {

    TextView lbl_signup;
    EditText txt_loginemail,txt_loginPassword;
    Button btn_login;
    private RegistrationController controller;
    DBConnection DBCON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        DBCON = new DBConnection(getApplicationContext());

        lbl_signup = findViewById(R.id.lbl_signUp);
        txt_loginemail = findViewById(R.id.txt_loginEmail);
        txt_loginPassword = findViewById(R.id.txt_loginPassword);
        btn_login = findViewById(R.id.btn_login);

        lbl_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txt_loginemail.getText().toString();
                String password = txt_loginPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this, "All Are Fields Required.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    controller = new RegistrationController(getApplicationContext());
                    int RegId = controller.LoginFun(email,password);
                    if (RegId > 0)
                    {
                        Toast.makeText(LoginActivity.this, "SuccessFully Login.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Eamil Or PassWord is Worng.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}