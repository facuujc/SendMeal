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
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sendmeal.model.CuentaBancaria;
import com.example.sendmeal.model.Tarjeta;
import com.example.sendmeal.model.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTxt_name;
    EditText editTxt_pass;
    EditText editTxt_passRepeat;
    EditText editTxt_email;
    Spinner spinner_month;
    Spinner spinner_year;
    RadioButton rBtn_credit;
    RadioButton rBtn_debit;
    EditText editTxt_numCard;
    EditText editTxt_ccv;
    EditText editTxt_cbu;
    EditText editTxt_aliasCbu;
    Switch switch_credit;
    LinearLayout layout_credit;
    TextView txtView_initCredit;
    CheckBox checkBox_tnc;
    SeekBar seekBar;
    Button btn_register;

    Double initialCredit;

    CuentaBancaria cuentaBancaria;
    Tarjeta tarjeta;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTxt_name = (EditText) findViewById(R.id.editText_name);
        editTxt_pass = (EditText) findViewById(R.id.editText_pass);
        editTxt_passRepeat = (EditText) findViewById(R.id.editText_pass_repeat);
        editTxt_email = (EditText) findViewById(R.id.editText_email);
        rBtn_credit = (RadioButton) findViewById(R.id.rBtn_credit);
        rBtn_debit = (RadioButton) findViewById(R.id.rBtn_debit);
        editTxt_numCard = (EditText) findViewById(R.id.editText_numCard);
        editTxt_ccv = (EditText) findViewById(R.id.editText_ccv);
        editTxt_cbu = (EditText) findViewById(R.id.editText_cbu);
        editTxt_aliasCbu = (EditText) findViewById(R.id.editText_aliasCbu);
        switch_credit = (Switch) findViewById(R.id.switch_initCredit);
        layout_credit = (LinearLayout) findViewById(R.id.layout_initCredit);
        txtView_initCredit = (TextView) findViewById(R.id.textView_initCredit);
        checkBox_tnc = (CheckBox) findViewById(R.id.checkBox_tnc);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        btn_register = (Button) findViewById(R.id.btn_register);

        initialCredit = 0.0;

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

        //Enabled CCV and Date
        editTxt_numCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTxt_numCard.getText().toString().equals("")) {
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

        //SeekBar Initial credit
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                initialCredit = i + 0.0;
                String str_credit = getString(R.string.txt_initial_credit) + ": " + initialCredit;
                txtView_initCredit.setText(str_credit);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Button Register
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = getString(R.string.msg_registered);
                Date dueDate = new GregorianCalendar(
                        Integer.parseInt(spinner_year.getSelectedItem().toString()),
                        Integer.parseInt(spinner_month.getSelectedItem().toString()) - 1, 1).getTime();
                if (editTxt_pass.getText().length() < 4 || editTxt_pass.getText().length() > 15) {
                    message = getString(R.string.err_pass);
                } else if (!editTxt_passRepeat.getText().toString().equals(editTxt_pass.getText().toString())) {
                    message = getString(R.string.err_repeat_pass);
                } else if (!validateEmail(editTxt_email.getText().toString())) {
                    message = getString(R.string.err_email);
                } else if (!rBtn_credit.isChecked() && !rBtn_debit.isChecked()) {
                    message = getString(R.string.err_type_card);
                } else if (editTxt_numCard.getText().toString().equals("")) {
                    message = getString(R.string.err_num_card);
                } else if (!editTxt_numCard.getText().toString().equals("")) {
                    if (editTxt_ccv.getText().toString().equals("")) {
                        message = getString(R.string.err_ccv);
                    } else if (!validateDueDate(dueDate)) {
                        message = getString(R.string.err_due_card);
                    }
                } else {
                    cuentaBancaria = new CuentaBancaria(editTxt_cbu.getText().toString(), editTxt_aliasCbu.getText().toString());
                    tarjeta = new Tarjeta(editTxt_numCard.getText().toString(), editTxt_ccv.getText().toString(), dueDate, rBtn_credit.isSelected());
                    usuario = new Usuario(hashCode(), //TODO
                            editTxt_name.getText().toString(),
                            editTxt_pass.getText().toString(),
                            editTxt_email.getText().toString(),
                            switch_credit.isSelected() ? Double.parseDouble(seekBar.getContext().toString()) : 0.0,
                            tarjeta,
                            cuentaBancaria);
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateEmail(String email) {
        for (int i = 0; i < email.length(); i++)
            if (email.charAt(i) == '@') {
                if (email.length() - i > 3 && Character.isLetter(email.charAt(i + 1))
                        && Character.isLetter(email.charAt(i + 2))
                        && Character.isLetter(email.charAt(i + 3)))
                    return true;
                else return false;
            }
        return false;
    }

    private boolean validateDueDate(Date date) {
        Calendar dueDate = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dueDate.setTime(date);
        dueDate.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH));
        today.add(Calendar.MONTH, 3);
        return dueDate.after(today);
    }
}