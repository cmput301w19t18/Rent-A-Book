package com.example.cmput301w19t18.rent_a_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * The type Search adapter.
 * Search adapter gets the arraylist to create the results inside the recyclerview in the search results activity
 * Displays all results that are pertinent to the search query
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
            mOwnerPicture = itemView.findViewById(R.id.userPhoto);
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
        Context context = searchViewHolder.itemView.getContext();
        final Book currentItem = mSearchBookList.get(i);


        final String bookCover = "http://covers.openlibrary.org/b/isbn/" + currentItem.getISBN() + "-M.jpg";
        Picasso.get().load(bookCover).into(searchViewHolder.mOwnerPicture);
//         searchViewHolder.mOwnerPicture.setImageResource(currentItem.getbPhoto());
        searchViewHolder.mBookTitle.setText(currentItem.getBtitle());
        searchViewHolder.mBookAuthor.setText(currentItem.getAuthor());
        searchViewHolder.mOwnerName.setText(currentItem.getbOwner());
        searchViewHolder.mStatus.setText(currentItem.getBstatus());

        searchViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),BookDetails.class );
                intent.putExtra("title", currentItem.getBtitle());
                intent.putExtra("owner", currentItem.getbOwner());
                intent.putExtra("author",currentItem.getAuthor());
                intent.putExtra("photo", bookCover);
                intent.putExtra("owner2", currentItem.getbOwner());
                intent.putExtra("bdescription2",currentItem.getDescription());


                intent.putExtra("mode","1");
                Activity search =  (Activity)v.getContext();


                v.getContext().startActivity(intent);
            }
        });




        if (i%2 == 1) {
            searchViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.lightOrangeSpice));

        }
        else {
            searchViewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.tanSpice));
        }
    }

    @Override
    public int getItemCount() {
        return mSearchBookList.size();
    }
}
