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

import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextWatcher {

    String[] genders = { "Male", "Female", "Other" };
    String[] vaccines = {"Sinovac", "Turcovac", "Biontech", "Other"};
    EditText name;
    EditText dd, mm, yyyy;
    EditText city;
    Spinner gender;
    Spinner vtype;
    EditText ase;
    EditText otherVaccine;
    CheckBox checkBox;
    Button submit;
    String cities[] = {"Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};

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
         dd = findViewById(R.id.dd);
         mm = findViewById(R.id.mm);
         yyyy = findViewById(R.id.yyyy);

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
        dd.addTextChangedListener(this);
        mm.addTextChangedListener(this);
        yyyy.addTextChangedListener(this);

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
        if(vaccines[i].equals("Other")) {
            otherVaccine.setVisibility(View.VISIBLE);
            if(otherVaccine.getVisibility() == View.VISIBLE && otherVaccine.getText().toString().isEmpty()){
                submit.setVisibility(View.INVISIBLE);
            }
        } else {
            otherVaccine.setVisibility(View.GONE);
            if(checkEmpty()){
                if(checkFields())
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
        if(checkEmpty()){
            if(checkFields())
                submit.setVisibility(View.VISIBLE);
        } else {
            submit.setVisibility(View.INVISIBLE);
        }
    }

    public boolean checkEmpty(){
        if(name.getText().toString().isEmpty()){
            return false;
        }
        else if(city.getText().toString().isEmpty()){
            return false;
        }
        else if(ase.getText().toString().isEmpty()){
            return false;
        }
        else if(otherVaccine.getVisibility() == View.VISIBLE && otherVaccine.getText().toString().isEmpty()){
            return false;
        }
        else if(mm.getText().toString().isEmpty())
            return false;
        else if(dd.getText().toString().isEmpty())
            return false;
        else if(yyyy.getText().toString().isEmpty())
            return false;
        return true;
    }

    public boolean checkFields(){
        int check = 0;
        if(name.getText().toString().matches(".*[0-9].*")){
            name.setError("No number");
            check = 1;
        }
        if(!Arrays.asList(cities).contains(city.getText().toString())){
            city.setError("City not found");
            check = 1;
        }
        if(mm.getText().toString().matches(".*[a-zA-Z].*")){
            mm.setError("No letter");
            check = 1;
        }
         else if(Integer.valueOf(mm.getText().toString()) > 12 || Integer.valueOf(mm.getText().toString()) < 1){
            mm.setError("Check boundaries");
            check = 1;
        }
        if(dd.getText().toString().matches(".*[a-zA-Z].*")){
            dd.setError("No letter");
            check = 1;
        }
        else if(Integer.valueOf(dd.getText().toString()) > checkDay(Integer.valueOf(dd.getText().toString())) || Integer.valueOf(dd.getText().toString()) < 1){
            dd.setError("Check boundaries");
            check = 1;
        }
        if(yyyy.getText().toString().matches(".*[a-zA-Z].*")){
            yyyy.setError("No letter");
            check = 1;
        }
        else if(Integer.valueOf(yyyy.getText().toString()) > 2022 || Integer.valueOf(yyyy.getText().toString()) < 1900){
            yyyy.setError("Check boundaries");
            check = 1;
        }
        if(check == 1)
            return false;
        return true;
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public int checkDay(int mm){
        int otuz[] = {4,6,9,11};
        if(mm == 2){
            if(Integer.valueOf(yyyy.getText().toString()) % 4 == 0){
                return 29;
            }
            return 28;
        }
        else if(Arrays.asList(otuz).contains(mm)){
            return 30;
        }
        return 31;
    }
}