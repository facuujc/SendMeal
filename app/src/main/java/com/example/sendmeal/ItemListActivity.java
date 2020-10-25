package com.example.sendmeal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.sendmeal.adapters.ItemRecyclerAdapter;
import com.example.sendmeal.data.RepositoryInMemory;

import java.util.Objects;

public class ItemListActivity extends AppCompatActivity {

    Toolbar toolbar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    RepositoryInMemory repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar_listItem);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        this.repository = new RepositoryInMemory();

        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_items);
        this.recyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);

        this.adapter = new ItemRecyclerAdapter(this.repository.list(), this);
        this.recyclerView.setAdapter(this.adapter);
        Log.d("tituloA: ", this.repository.list().get(1).getTitle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}