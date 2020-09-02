package com.example.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTxt_name;
    EditText editTxt_pass;
    EditText editTxt_passRepeat;
    EditText editTxt_email;
    Spinner spinner_month;
    Spinner spinner_year;
    EditText editTxt_numCard;
    EditText editTxt_ccv;
    Switch switch_credit;
    LinearLayout layout_credit;
    CheckBox checkBox_tnc;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxt_name = (EditText) findViewById(R.id.editText_name);
        editTxt_pass = (EditText) findViewById(R.id.editText_pass);
        editTxt_passRepeat = (EditText) findViewById(R.id.editText_pass_repeat);
        editTxt_email = (EditText) findViewById(R.id.editText_email);
        editTxt_numCard = (EditText) findViewById(R.id.editText_numCard);
        editTxt_ccv = (EditText) findViewById(R.id.editText_ccv);
        switch_credit = (Switch) findViewById(R.id.switch_initCredit);
        layout_credit = (LinearLayout) findViewById(R.id.layout_initCredit);
        checkBox_tnc = (CheckBox) findViewById(R.id.checkBox_tnc);
        btn_register = (Button) findViewById(R.id.btn_register);

        //load spinner month
        List<Integer> monthsSpinner = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        spinner_month = (Spinner) findViewById(R.id.spinner_month);
        spinner_month.setAdapter(new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, monthsSpinner));
        spinner_month.setEnabled(false);
        //load spinner year
        List<Integer> yearsSpinner = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 0; i < 10; i++) yearsSpinner.add(year + i);
        spinner_year = (Spinner) findViewById(R.id.spinner_year);
        spinner_year.setAdapter(new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, yearsSpinner));
        spinner_year.setEnabled(false);

        //Enabled CCV
        editTxt_numCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTxt_numCard.getText().toString().equals("") || (editTxt_numCard.getText().toString() == null)) {
                    editTxt_ccv.setEnabled(false);
                    spinner_month.setEnabled(false);
                    spinner_year.setEnabled(false);
                } else {
                    editTxt_ccv.setEnabled(true);
                    spinner_month.setEnabled(true);
                    spinner_year.setEnabled(true);
                }
            }
        });

        //Listeners
        CompoundButton.OnCheckedChangeListener listenerCB = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switch (compoundButton.getId()) {
                    case R.id.switch_initCredit:
                        layout_credit.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
                        break;
                    case R.id.checkBox_tnc:
                        btn_register.setEnabled(b);
                        break;
                }
            }
        };
        switch_credit.setOnCheckedChangeListener(listenerCB);
        checkBox_tnc.setOnCheckedChangeListener(listenerCB);

    }
}