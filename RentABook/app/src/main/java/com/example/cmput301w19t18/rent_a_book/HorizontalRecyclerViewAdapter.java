package com.example.cmput301w19t18.rent_a_book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRVViewHolder> {

    Context context;
    ArrayList<HorizontalModel> arrayList;

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HorizontalRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_book_item, viewGroup, false);
        return new HorizontalRVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalRVViewHolder horizontalRVViewHolder, int i) {
        final HorizontalModel horizontalModel = arrayList.get(i);
        horizontalRVViewHolder.ratingBar.setRating(horizontalModel.getBookRating());
        Picasso.get().load(horizontalModel.getBookCover()).into(horizontalRVViewHolder.bookCover);

        //horizontalRVViewHolder.bookCover.setImageResource(horizontalModel.getBookCover());
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HorizontalRVViewHolder extends RecyclerView.ViewHolder {

        RatingBar ratingBar;
        ImageView bookCover;

        public HorizontalRVViewHolder(View itemView) {
            super(itemView);
            ratingBar = (RatingBar)itemView.findViewById(R.id.bookRating);
            bookCover = (ImageView)itemView.findViewById(R.id.bookCover);
        }
    }
}
