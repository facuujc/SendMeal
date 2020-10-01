package com.example.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private static final int CODE_SING_IN = 101;
    private static final int CODE_CREATE_ITEM = 102;
    private static final int CODE_ITEM_LIST = 103;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_singIn:
                Intent intent_singIn = new Intent(this, SingInActivity.class);
                startActivityForResult(intent_singIn, CODE_SING_IN);
                break;
            case R.id.item_createItem:
               Intent intent_CreateItem = new Intent(this, CreateItemActivity.class);
               startActivityForResult(intent_CreateItem, CODE_CREATE_ITEM);
                break;
            case R.id.item_itemList:
                Intent intent_ItemList = new Intent(this, ItemListActivity.class);
                startActivityForResult(intent_ItemList, CODE_ITEM_LIST);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}