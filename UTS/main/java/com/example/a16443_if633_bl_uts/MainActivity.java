 package com.example.a16443_if633_bl_uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


 public class MainActivity extends AppCompatActivity{
    Button b1;
    EditText ed1,ed2;
    TextView tx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1 = (EditText)findViewById(R.id.username);
        ed2 = (EditText)findViewById(R.id.password);
        tx1.setVisibility(View.GONE);
        b1 = (Button)findViewById(R.id.confirm);
        b1.setOnClickListener(view -> {
            Intent menu = new Intent(MainActivity.this, menu1.class);
            startActivity(menu);

            if (ed1.getText().toString().equals("uasmobile") &&
            ed2.getText().toString().equals("uasmobilegenap")){
                Toast.makeText(getApplicationContext(),
                        "Redirecting....", Toast.LENGTH_SHORT).show();
            }
                tx1.setBackgroundColor(Color.RED);


            });
            }
   /* public boolean onOptionItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            startActivity(new Intent(this, about.class));
        } else if (item.getItemId() == R.id.logout) {
            finish();
            System.exit(0);
        }
        return true;*/


 };