package com.example.cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextWatcher {

    String[] genders = { "Male", "Female", "Other" };
    String[] vaccines = {"Sinovac", "Turcovac", "Biontech", "Other"};
    EditText name;
    EditText city;
    Spinner gender;
    Spinner vtype;
    EditText ase;
    EditText otherVaccine;
    CheckBox checkBox;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean[] arr = new boolean[5];
         name = findViewById(R.id.name);
         city = findViewById(R.id.city);
         gender = findViewById(R.id.gender);
         vtype = findViewById(R.id.vtype);
         ase = findViewById(R.id.ase);
         checkBox = findViewById(R.id.checkbox);
         submit = findViewById(R.id.submit);
         otherVaccine = findViewById(R.id.otherVaccine);

        gender.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(ad);

        vtype.setOnItemSelectedListener(this);
        ArrayAdapter adv = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vaccines);
        adv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vtype.setAdapter(adv);

        name.addTextChangedListener(this);
        city.addTextChangedListener(this);
        ase.addTextChangedListener(this);
        otherVaccine.addTextChangedListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecond();
            }
        });
    };

    public void openSecond(){
        Intent intent = new Intent(this, SecondPage.class);
        intent.putExtra("name",name.getText().toString());
        intent.putExtra("city",city.getText().toString());
        intent.putExtra("ase",ase.getText().toString());
        intent.putExtra("otherVaccine",otherVaccine.getText().toString());
        intent.putExtra("gender",gender.getSelectedItem().toString());
        intent.putExtra("vtype",vtype.getSelectedItem().toString());
        intent.putExtra("check",String.valueOf(checkBox.isChecked()));

        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println(vaccines[i].equals("Other"));
        if(vaccines[i].equals("Other")) {
            otherVaccine.setVisibility(View.VISIBLE);
            if(otherVaccine.getVisibility() == View.VISIBLE && otherVaccine.getText().toString().isEmpty()){
                submit.setVisibility(View.INVISIBLE);
            }
        } else {
            otherVaccine.setVisibility(View.GONE);
            boolean check = false;
            if(name.getText().toString().isEmpty()){
                check = false;
            }
            else if(city.getText().toString().isEmpty()){
                check = false;
            }
            else if(ase.getText().toString().isEmpty()){
                check = false;
            }
            else if(otherVaccine.getVisibility() == View.VISIBLE && otherVaccine.getText().toString().isEmpty()){
                check = false;
            }
            else {
                check = true;
            }

            if(check){
                submit.setVisibility(View.VISIBLE);
            } else {
                submit.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean check = false;
        if(name.getText().toString().isEmpty()){
            check = false;
        }
        else if(city.getText().toString().isEmpty()){
            check = false;
        }
        else if(ase.getText().toString().isEmpty()){
            check = false;
        }
        else if(otherVaccine.getVisibility() == View.VISIBLE && otherVaccine.getText().toString().isEmpty()){
            check = false;
        }
        else {
            check = true;
        }

        if(check){
            submit.setVisibility(View.VISIBLE);
        } else {
            submit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


}