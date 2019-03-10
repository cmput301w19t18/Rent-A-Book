package com.example.cmput301w19t18.rent_a_book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeVerticalRecyclerView extends RecyclerView.Adapter<HomeVerticalRecyclerView.VerticalRVViewHolder> {

    Context context;
    ArrayList<Category> arrayList;

    public HomeVerticalRecyclerView(Context context, ArrayList<Category> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VerticalRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_home_vertical, viewGroup, false);
        return new VerticalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRVViewHolder verticalRVViewHolder, int i) {
        Category category = arrayList.get(i);
        String categoryTitle = category.getCategoryTitle();
        ArrayList<HorizontalModel> singleItem = category.getArrayList();

        verticalRVViewHolder.categoryTitle.setText(categoryTitle);
        HorizontalRecyclerViewAdapter horizontalRecyclerViewAdapter = new HorizontalRecyclerViewAdapter(context, singleItem);

        verticalRVViewHolder.recyclerView.setHasFixedSize(true);
        verticalRVViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        verticalRVViewHolder.recyclerView.setAdapter(horizontalRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VerticalRVViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView categoryTitle;
        Button addButton;

        public VerticalRVViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView)itemView.findViewById(R.id.recyclerHorizontalView);
            categoryTitle = (TextView)itemView.findViewById(R.id.CategoryTitle);
            addButton = (Button)itemView.findViewById(R.id.addButton);
        }
    }
}
