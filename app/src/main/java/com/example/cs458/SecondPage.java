package com.example.cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondPage extends AppCompatActivity {

    TextView name;
    TextView bdate;
    TextView city;
    TextView gender;
    TextView vaccination;
    TextView ase;
    TextView after;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        name = findViewById(R.id.name);
        bdate = findViewById(R.id.bdate);
        city = findViewById(R.id.city);
        gender = findViewById(R.id.gender);
        vaccination = findViewById(R.id.vaccination);
        ase = findViewById(R.id.ase);
        after = findViewById(R.id.after);
        edit = findViewById(R.id.edit);

        Intent intent = getIntent();

        name.setText("Name is: "+ intent.getStringExtra("name"));
        bdate.setText("Birth date is: " + intent.getStringExtra("bdate"));
        city.setText("City is: "+ intent.getStringExtra("city"));
        gender.setText("Gender is: "+ intent.getStringExtra("gender"));
        vaccination.setText("Vaccination is: "+ intent.getStringExtra("vaccination"));
        ase.setText("Side Effects are: "+ intent.getStringExtra("ase"));
        after.setText("After 3th: " + intent.getStringExtra("after"));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}