package com.example.sendmeal.adapters;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sendmeal.R;
import com.example.sendmeal.model.Item;

import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {

    private static final String _path_imgs = "../../../../../res/drawable/";

    private List<Item> items;
    private AppCompatActivity activity;

    public ItemRecyclerAdapter(List<Item> items, AppCompatActivity activity) {
        this.items = items;
        this.activity = activity;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imageView;
        TextView textView_price;
        TextView textView_link;

        public ItemViewHolder(View v) {
            super(v);
            card = (CardView)v.findViewById(R.id.card_item);
            imageView = (ImageView) v.findViewById(R.id.img_item);
            textView_price = (TextView) v.findViewById(R.id.textView_price);
        }
    }

    @Override
    public ItemRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_plato, parent, false);
        //...
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemHolder, final int position) {
        itemHolder.imageView.setTag(position);
        Item item = items.get(position);
        Uri uri = Uri.parse( _path_imgs + item.getImage());
        itemHolder.imageView.setImageURI(uri);

        itemHolder.imageView.setContentDescription(item.getTitle());
        itemHolder.imageView.setImageResource(R.drawable.img_item);
        itemHolder.textView_price.setText("$ " + item.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
