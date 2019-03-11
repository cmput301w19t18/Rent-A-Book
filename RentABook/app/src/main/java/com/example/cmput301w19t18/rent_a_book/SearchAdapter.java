package com.example.cmput301w19t18.rent_a_book;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * The type Search adapter.
 * Search adapter gets the arraylist to create the results inside the recyclerview in the search results activity
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<Book> mSearchBookList;

    /**
     * The type Search view holder.
     */
    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        /**
         * The M owner picture.
         */
        public ImageView mOwnerPicture;
        /**
         * The M book title.
         */
        public TextView mBookTitle;
        /**
         * The M book author.
         */
        public TextView mBookAuthor;
        /**
         * The M owner name.
         */
        public TextView mOwnerName;
        /**
         * The M status.
         */
        public TextView mStatus;

        /**
         * Instantiates a new Search view holder.
         *
         * @param itemView the item view
         */
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            mOwnerPicture = itemView.findViewById(R.id.bookPhoto);
            mBookTitle = itemView.findViewById(R.id.bookTitle);
            mBookAuthor = itemView.findViewById(R.id.bookAuthor);
            mOwnerName = itemView.findViewById(R.id.bookOwner);
            mStatus = itemView.findViewById(R.id.bookStatus);
        }
    }

    /**
     * Instantiates a new Search adapter.
     *
     * @param SearchBookList the search book list
     */
    public SearchAdapter(ArrayList<Book> SearchBookList) {
        mSearchBookList = SearchBookList;
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
         Book currentItem = mSearchBookList.get(i);

//         searchViewHolder.mOwnerPicture.setImageResource(currentItem.getbPhoto());
//         searchViewHolder.mBookTitle.setText(currentItem.getBtitle());
//         searchViewHolder.mBookAuthor.setText(currentItem.getAuthor());
//         searchViewHolder.mOwnerName.setText(currentItem.getOwner());
//         searchViewHolder.mStatus.setText(currentItem.getBstatus());
    }

    @Override
    public int getItemCount() {
        return mSearchBookList.size();
    }
}
