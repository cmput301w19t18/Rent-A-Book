package com.example.cmput301w19t18.rent_a_book;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<Owner> mOwnerList;

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        public ImageView mOwnerPicture;
        public TextView mBookTitle;
        public TextView mBookAuthor;
        public TextView mOwnerName;
        public TextView mStatus;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mOwnerPicture = itemView.findViewById(R.id.bookPhoto);
            mBookTitle = itemView.findViewById(R.id.bookTitle);
            mBookAuthor = itemView.findViewById(R.id.bookAuthor);
            mOwnerPicture = itemView.findViewById(R.id.bookOwner);
            mStatus = itemView.findViewById(R.id.bookStatus);
        }
    }

    public SearchAdapter(ArrayList<Owner> OwnerList) {
        mOwnerList = OwnerList;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        SearchViewHolder svh = new SearchViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
         Owner currentItem = mOwnerList.get(i);

         searchViewHolder.mOwnerPicture.setImageResource(currentItem.getImg());
         searchViewHolder.mBookTitle.setText(currentItem.getMyBook().getBtitle());
         searchViewHolder.mBookAuthor.setText(currentItem.getMyBook().getAuthor());
         searchViewHolder.mOwnerName.setText(currentItem.getUser_id());
         searchViewHolder.mStatus.setText(currentItem.getMyBook().getBstatus());
    }

    @Override
    public int getItemCount() {
        return mOwnerList.size();
    }
}
