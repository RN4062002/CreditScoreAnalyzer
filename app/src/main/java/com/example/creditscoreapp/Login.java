package com.example.creditscoreapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creditscoreapp.data.User;
import com.example.creditscoreapp.database.AppDatabase;

import java.util.List;

import needle.Needle;
import needle.UiRelatedTask;

public class Login extends AppCompatActivity {
    Button button;
    EditText text1,text2;

    TextView txt1;
    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.btn1);
        text1 = findViewById(R.id.user);
        text2 = findViewById(R.id.pass);
        txt1 = findViewById(R.id.txt1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Test();
            }
        });
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }
    public void Test() {
        Needle.onBackgroundThread().execute(new UiRelatedTask<Boolean>() {
            @Override
            protected Boolean doWork() {
                try {
                    db = AppDatabase.getInstance(Login.this);
                    String username = text1.getText().toString();
                    String password = text2.getText().toString();
                    Log.d("data","data is ====>>>"+username+" "+password);
                    List<User> users = db.userDao().getAll();
                    boolean credentialsMatch = false;

                    for (User user : users) {
                        if (username.equals(user.getEmail()) && password.equals(user.getPassword())) {
                            credentialsMatch = true;
                            break;
                        }
                    }

                    if (credentialsMatch) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
                return true;
            }

            @Override
            protected void thenDoUiRelatedWork(Boolean result) {
                Toast.makeText(getApplicationContext(), "Login successfully.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
