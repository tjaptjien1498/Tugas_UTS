package com.example.a16443_if633_bl_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class awal extends AppCompatActivity {

    Button about, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awal);
        login = (Button)findViewById(R.id.login);
       login.setOnClickListener(view -> {
            Intent MainActivity = new Intent(awal.this, MainActivity.class);
            startActivity(MainActivity);



       });

        /*about = (Button)findViewById(R.id.about);
        about.setOnClickListener(view -> {
            Intent about = new Intent(awal.this, about.class);
            startActivity(about);

        });
*/


    }
}