package com.example.cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SecondPage extends AppCompatActivity {

    TextView name;
    TextView city;
    TextView gender;
    TextView vtype;
    TextView ase;
    TextView otherVaccine;
    TextView checkBox;
    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        main = findViewById(R.id.main);

        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        gender = findViewById(R.id.gender);
        vtype = findViewById(R.id.vtype);
        ase = findViewById(R.id.ase);
        otherVaccine = findViewById(R.id.otherVaccine);
        checkBox = findViewById(R.id.checkBox);

        Intent intent = getIntent();

        name.setText("Name is: "+ intent.getStringExtra("name"));
        city.setText("City is: "+ intent.getStringExtra("city"));
        gender.setText("Gender is: "+ intent.getStringExtra("gender"));
        vtype.setText("Vaccination Type is: "+ intent.getStringExtra("vtype"));
        ase.setText("Side Effects are: "+ intent.getStringExtra("ase"));
        if(intent.getStringExtra("otherVaccine").length() > 0)
            otherVaccine.setText("VT is: "+ intent.getStringExtra("otherVaccine"));
        else
            otherVaccine.setVisibility(View.GONE);
        checkBox.setText("AS is: "+ intent.getStringExtra("checkBox"));


    }
}