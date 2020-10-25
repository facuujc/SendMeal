package com.example.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sendmeal.model.Item;

import java.util.Objects;

public class CreateItemActivity extends AppCompatActivity {

    EditText editText_title;
    EditText editText_description;
    EditText editText_price;
    EditText editText_calories;
    Button btn_save;
    Toolbar toolbar;

    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        editText_title = (EditText) findViewById(R.id.editText_title);
        editText_description = (EditText) findViewById(R.id.editText_description);
        editText_price = (EditText) findViewById(R.id.editText_price);
        editText_calories = (EditText) findViewById(R.id.editText_calories);
        btn_save = (Button) findViewById(R.id.btn_saveItem);
        toolbar = (Toolbar) findViewById(R.id.toolbar_createItem);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Listeners
        editText_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_title.getText().toString().isEmpty()
                        || editText_price.getText().toString().isEmpty()
                        || editText_calories.getText().toString().isEmpty())
                    btn_save.setEnabled(false);
                else btn_save.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_title.getText().toString().isEmpty()
                        || editText_price.getText().toString().isEmpty()
                        || editText_calories.getText().toString().isEmpty())
                    btn_save.setEnabled(false);
                else btn_save.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        editText_calories.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText_title.getText().toString().isEmpty()
                        || editText_price.getText().toString().isEmpty()
                        || editText_calories.getText().toString().isEmpty())
                    btn_save.setEnabled(false);
                else btn_save.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new Item(
                        editText_title.getText().toString(),
                        editText_description.getText().toString(),
                        Double.parseDouble(editText_price.getText().toString()),
                        Integer.parseInt(editText_calories.getText().toString()),
                        R.drawable.img_item);

                Toast.makeText(CreateItemActivity.this, getString(R.string.msg_item_created), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}