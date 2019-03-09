package com.example.cmput301w19t18.rent_a_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Owner> OwnerList;

    private String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Book book1 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Available", "jakep@nypd.org", 5);
        Book book2 = new Book("Brooklyn 99 2 - Book Edition", "Daniel Goor", "Comedy", "2", "Available", "amys@nypd.org", 4);
        Book book3 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1","Requested",  "rosad@nypd.org", 5);
        Book book4 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Accepted", "charlesb@nypd.org", 5);
        Book book5 = new Book("Brooklyn 99 - Book Edition", "Daniel Goor", "Comedy", "1", "Borrowed", "raymondh@nypd.org", 5);
        Book book6 = new Book("Nothing Lasts Forever", "Roderick Thorp", "Thriller", "3", "Available", "jakep@nypd.org", 3);

        Owner owner1 = new Owner(book1, book1.getOwner(), new float[]{1.0f, 1.0f}, R.drawable.go1984);
        Owner owner2 = new Owner(book2, book2.getOwner(), new float[]{1.0f, 1.0f}, R.drawable.go1984);
        Owner owner3 = new Owner(book3, book3.getOwner(), new float[]{1.0f, 1.0f}, R.drawable.go1984);
        Owner owner4 = new Owner(book4, book4.getOwner(), new float[]{1.0f, 1.0f}, R.drawable.go1984);
        Owner owner5 = new Owner(book5, book5.getOwner(), new float[]{1.0f, 1.0f}, R.drawable.go1984);
        Owner owner6 = new Owner(book6, book6.getOwner(), new float[]{1.0f, 1.0f}, R.drawable.go1984);

        OwnerList = new ArrayList<>();
        OwnerList.add(owner1);
        OwnerList.add(owner2);
        OwnerList.add(owner3);
        OwnerList.add(owner4);
        OwnerList.add(owner5);
        OwnerList.add(owner6);

        mRecyclerView = findViewById(R.id.bookResults);
        mRecyclerView.setHasFixedSize(true); //for not, it will change size
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SearchAdapter(OwnerList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void DisplayResults() {
        // code

    }

    public void Refresh() {
        // code
    }
}
