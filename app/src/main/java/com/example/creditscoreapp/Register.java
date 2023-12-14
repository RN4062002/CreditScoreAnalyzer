package com.example.creditscoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creditscoreapp.dao.UserDao;
import com.example.creditscoreapp.data.User;
import com.example.creditscoreapp.database.AppDatabase;

import needle.Needle;
import needle.UiRelatedTask;

public class Register extends AppCompatActivity {
    Button btn1;
    EditText editText1, editText2, editText3, editText4, editText5;
    TextView txt1;
    AppDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



            db = AppDatabase.getInstance(Register.this);
            editText1 = findViewById(R.id.editText);
            editText2 = findViewById(R.id.editText2);
            editText3 = findViewById(R.id.editText3);
            editText4 = findViewById(R.id.editText4);
            editText5 = findViewById(R.id.editText5);
            btn1 = findViewById(R.id.button);
            txt1 = findViewById(R.id.txt1);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = editText1.getText().toString();
                    String lname = editText2.getText().toString();
                    String email = editText3.getText().toString();
                    String phone = editText4.getText().toString();
                    String password = editText5.getText().toString();
                    UserDao userDao = db.userDao();

                    User user = new User(username, lname, email, phone, password);

                    Test(Register.this, user);
                }
            });
            txt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            });
        }


        public void Test(Context mContext, User user) {
            Needle.onBackgroundThread().execute(new UiRelatedTask<Boolean>() {
                @Override
                protected Boolean doWork() {
                    try {
                        AppDatabase db = AppDatabase.getInstance(mContext);
                        db.userDao().insertAll(user);
                    } catch (Exception e) {
                    }
                    return true;
                }

                @Override
                protected void thenDoUiRelatedWork(Boolean result) {
                    Toast.makeText(getApplicationContext(), "data inserted successfully.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                }
            });
        }

    }
