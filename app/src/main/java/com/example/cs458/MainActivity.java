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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements TextWatcher, RadioGroup.OnCheckedChangeListener, CompoundButton.OnCheckedChangeListener {

    EditText name;
    EditText dd, mm, yyyy;
    EditText city, ase;
    EditText otherVaccine;
    RadioGroup groupGender, groupVaccination, groupPositive;
    CheckBox checkase;
    Button submit;
    String genderSelected, vaccinationSelected, positiveSelected;
    String cities[] = {"adana", "adıyaman", "afyon", "ağrı", "amasya", "ankara", "antalya", "artvin",
            "aydın", "balıkesir", "bilecik", "bingöl", "bitlis", "bolu", "burdur", "bursa", "çanakkale",
            "çankırı", "çorum", "denizli", "diyarbakır", "edirne", "elazığ", "erzincan", "erzurum", "eskişehir",
            "gaziantep", "giresun", "gümüşhane", "hakkari", "hatay", "ısparta", "mersin", "istanbul", "izmir",
            "kars", "kastamonu", "kayseri", "kırklareli", "kırşehir", "kocaeli", "konya", "kütahya", "malatya",
            "manisa", "kahramanmaraş", "mardin", "muğla", "muş", "nevşehir", "niğde", "ordu", "rize", "sakarya",
            "samsun", "siirt", "sinop", "sivas", "tekirdağ", "tokat", "trabzon", "tunceli", "şanlıurfa", "uşak",
            "van", "yozgat", "zonguldak", "aksaray", "bayburt", "karaman", "kırıkkale", "batman", "şırnak",
            "bartın", "ardahan", "ığdır", "yalova", "karabük", "kilis", "osmaniye", "düzce"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        dd = findViewById(R.id.dd);
        mm = findViewById(R.id.mm);
        yyyy = findViewById(R.id.yyyy);
        city = findViewById(R.id.city);
        groupGender = findViewById(R.id.groupGender);
        groupVaccination = findViewById(R.id.groupVaccination);
        otherVaccine = findViewById(R.id.otherVaccine);
        checkase = findViewById(R.id.checkase);
        ase = findViewById(R.id.ase);
        groupPositive = findViewById(R.id.groupPositive);
        submit = findViewById(R.id.submit);


        name.addTextChangedListener(this);
        city.addTextChangedListener(this);
        ase.addTextChangedListener(this);
        otherVaccine.addTextChangedListener(this);
        dd.addTextChangedListener(this);
        mm.addTextChangedListener(this);
        yyyy.addTextChangedListener(this);
        groupGender.setOnCheckedChangeListener(this);
        groupVaccination.setOnCheckedChangeListener(this);
        checkase.setOnCheckedChangeListener(this);
        groupPositive.setOnCheckedChangeListener(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecond();
            }
        });
    }

    ;

    public void openSecond() {
        Intent intent = new Intent(this, SecondPage.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("bdate", dd.getText().toString() + "-" + mm.getText().toString() + "-" + yyyy.getText().toString());
        intent.putExtra("city", city.getText().toString());
        intent.putExtra("gender", genderSelected);
        intent.putExtra("vaccination", vaccinationSelected.equals("Other") ? otherVaccine.getText().toString() : vaccinationSelected);
        intent.putExtra("ase", checkase.isChecked() ? ase.getText().toString() : "No side effect");
        intent.putExtra("after", positiveSelected);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        RadioButton checkedRadioButton = radioGroup.findViewById(i);
        if (getResources().getResourceEntryName(radioGroup.getId()).equals("groupVaccination")) {
            vaccinationSelected = checkedRadioButton.getText().toString();
            if (checkedRadioButton.getText().toString().equals("Other"))
                otherVaccine.setVisibility(View.VISIBLE);
            else {
                otherVaccine.setVisibility(View.GONE);
            }
        } else if (getResources().getResourceEntryName(radioGroup.getId()).equals("groupGender")) {
            genderSelected = checkedRadioButton.getText().toString();
        } else {
            positiveSelected = checkedRadioButton.getText().toString();
        }
        if (checkEmpty()) {
            if (checkFields())
                submit.setVisibility(View.VISIBLE);
        } else {
            submit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            ase.setVisibility(View.VISIBLE);
        } else {
            ase.setVisibility(View.GONE);
        }
        if (checkEmpty()) {
            if (checkFields())
                submit.setVisibility(View.VISIBLE);
        } else {
            submit.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (checkEmpty()) {
            if (checkFields())
                submit.setVisibility(View.VISIBLE);
        } else {
            submit.setVisibility(View.INVISIBLE);
        }
    }

    public boolean checkEmpty() {
        if (name.getText().toString().isEmpty()) {
            return false;
        } else if (city.getText().toString().isEmpty()) {
            return false;
        } else if (ase.getVisibility() == View.VISIBLE && ase.getText().toString().isEmpty()) {
            return false;
        } else if (otherVaccine.getVisibility() == View.VISIBLE && otherVaccine.getText().toString().isEmpty()) {
            return false;
        } else if (mm.getText().toString().isEmpty())
            return false;
        else if (dd.getText().toString().isEmpty())
            return false;
        else if (yyyy.getText().toString().isEmpty())
            return false;
        else if (groupGender.getCheckedRadioButtonId() == -1)
            return false;
        else if (groupVaccination.getCheckedRadioButtonId() == -1)
            return false;
        else if (groupPositive.getCheckedRadioButtonId() == -1)
            return false;
        return true;
    }

    public boolean checkFields() {
        int check = 0;

        if (name.getText().toString().matches(".*[0-9].*")) {
            name.setError("No number");
            check = 1;
        }
        if (!Arrays.asList(cities).contains(city.getText().toString().toLowerCase())) {
            city.setError("City not found");
            check = 1;
        }
        if (mm.getText().toString().matches(".*[a-zA-Z].*")) {
            mm.setError("No letter");
            check = 1;
        } else {
            if (Integer.valueOf(mm.getText().toString()) > 12 || Integer.valueOf(mm.getText().toString()) < 1) {
                mm.setError("Check boundaries");
                check = 1;
            }
        }
        if (dd.getText().toString().matches(".*[a-zA-Z].*")) {
            dd.setError("No letter");
            check = 1;
        } else {
            if (!mm.getText().toString().matches(".*[a-zA-Z].*") && Integer.valueOf(dd.getText().toString()) > checkDay(Integer.valueOf(mm.getText().toString())) || Integer.valueOf(dd.getText().toString()) < 1) {
                dd.setError("Check boundaries");
                check = 1;
            }
        }
        if (yyyy.getText().toString().matches(".*[a-zA-Z].*")) {
            yyyy.setError("No letter");
            check = 1;
        } else {
            if (Integer.valueOf(yyyy.getText().toString()) > 2022 || Integer.valueOf(yyyy.getText().toString()) < 1900) {
                yyyy.setError("Check boundaries");
                check = 1;
            }
        }
        if (check == 1)
            return false;
        else {
            Calendar c = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            c.set(Integer.valueOf(yyyy.getText().toString()), Integer.valueOf(mm.getText().toString()) - 1, Integer.valueOf(dd.getText().toString()), 0, 0);
            if (c.after(now)) {
                mm.setError("Check boundaries");
                dd.setError("Check boundaries");
                yyyy.setError("Check boundaries");
                return false;
            }
            dd.setError(null);
            mm.setError(null);
            yyyy.setError(null);
            return true;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public int checkDay(int mm) {
        int otuz[] = {4, 6, 9, 11};
        if (mm == 2) {
            if (!yyyy.getText().toString().matches(".*[a-zA-Z].*") && Integer.valueOf(yyyy.getText().toString()) % 4 == 0) {
                return 29;
            }
            return 28;
        } else if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            return 30;
        }
        return 31;
    }
}