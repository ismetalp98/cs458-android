package com.example.cs458;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

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

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        System.out.println(dateButton.getText().toString());

    };


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println(vaccines[i].equals("Other"));
        if(vaccines[i].equals("Other")) {
            otherVaccine.setVisibility(View.VISIBLE);
        } else {
            otherVaccine.setVisibility(View.GONE);
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
        else if(dateButton.getText().toString().equals("Birth date")){
            check = false;
        }
        else if(city.getText().toString().isEmpty()){
            check = false;
        }
        else if(ase.getText().toString().isEmpty()){
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

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);

                boolean check = false;
                if(name.getText().toString().isEmpty()){
                    check = false;
                }
                else if(dateButton.getText().toString().equals("Birth date")){
                    check = false;
                }
                else if(city.getText().toString().isEmpty()){
                    check = false;
                }
                else if(ase.getText().toString().isEmpty()){
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
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
}