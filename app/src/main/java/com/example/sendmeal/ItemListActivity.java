package com.example.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sendmeal.adapters.ItemRecyclerAdapter;
import com.example.sendmeal.data.RepositoryInMemory;

public class ItemListActivity extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    RepositoryInMemory repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar_listItem);
        setSupportActionBar(toolbar);

        this.repository = new RepositoryInMemory();

        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_items);
        this.recyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.adapter = new ItemRecyclerAdapter(this.repository.list(), this);
        this.recyclerView.setAdapter(this.adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_go_back, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_goBack:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}