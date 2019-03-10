package com.example.cmput301w19t18.rent_a_book;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView verticalRecyclerView;
    HomeVerticalRecyclerView adapter;
    ArrayList<Category> arrayListVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        arrayListVertical = new ArrayList<>();

        verticalRecyclerView = (RecyclerView)findViewById(R.id.homeRecyclerView);
        verticalRecyclerView.setHasFixedSize(true);

        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

        adapter = new HomeVerticalRecyclerView(this, arrayListVertical);

        verticalRecyclerView.setAdapter(adapter);

        setData();
        // make vertical adapter for recycler view

    }

    private void setData() {
        for(int i=1; i<=5; i++) {
            Category category = new Category();
            category.setCategoryTitle("Brooklynn Nine-Nine Season " +i);

            ArrayList<HorizontalModel> arrayListHorizontal = new ArrayList<>();

            for (int j=0; j<10; j++) {
                HorizontalModel horizontalModel = new HorizontalModel();
                if (i%2 == 1) {
                    horizontalModel.setBookRating(5);
                }
                else {
                    horizontalModel.setBookRating(4);
                }
                horizontalModel.setBookCover(R.drawable.brook99);
                arrayListHorizontal.add(horizontalModel);
            }

            category.setArrayList(arrayListHorizontal);

            arrayListVertical.add(category);
        }

        adapter.notifyDataSetChanged();
    }
}
