package com.example.cmput301w19t18.rent_a_book;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The type Horizontal recycler view adapter.
 * The view adapter is able to get the arraylist to create the horizontal recyclerview in the nestedrecyclerview in the home activity
 *
 * https://stackoverflow.com/questions/35548110/setimageresource-
 */
public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalRVViewHolder> {

    /**
     * The Context.
     */
    Context context;
    /**
     * The Array list.
     */
    ArrayList<Book> arrayList;

    /**
     * Instantiates a new Horizontal recycler view adapter.
     *
     * @param context   the context
     * @param arrayList the array list
     */
    public HorizontalRecyclerViewAdapter(Context context, ArrayList<Book> arrayList) {
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
    public void onBindViewHolder(@NonNull final HorizontalRVViewHolder horizontalRVViewHolder, final int i) {
        final Book currentBook = arrayList.get(i);
        horizontalRVViewHolder.ratingBar.setRating(currentBook.getRating());

        String url = "http://covers.openlibrary.org/b/isbn/" + currentBook.getISBN() + "-M.jpg";
        final String fake_description = "This is a description test. " +
                "I realized we need to add the description in the book class, but for some reason is not there, " +
                "so we have to add that as an attribute. I can't believe the project is due in like a week, " +
                "like woah where did the time go. I am really tired, but honestly that's okay. This is definitely " +
                "not an accurate description of the current book.";

        Picasso.get().load(url).into(horizontalRVViewHolder.bookCover);

        horizontalRVViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookDetails.class);
                //arrayList.get(i)

                intent.putExtra("ratings", String.valueOf(arrayList.get(i).getRating()));
                intent.putExtra("btitle", arrayList.get(i).getBtitle());
                intent.putExtra("bookphoto","http://covers.openlibrary.org/b/isbn/" + arrayList.get(i).getISBN() + "-M.jpg");
                intent.putExtra("bstatus",arrayList.get(i).getBstatus());
                intent.putExtra("owner",arrayList.get(i).getbOwner());
                intent.putExtra("bdescription",fake_description);

//                intent.putExtra("ratings", String.valueOf(arrayList.get(i).getBookRating()));
//                intent.putExtra("btitle", arrayList.get(i).getBookTitle());
//                intent.putExtra("bookphoto",arrayList.get(i).getBookCover());

                intent.putExtra("mode", "2");
                Activity origin = (Activity) context;
                origin.startActivity(intent);

                //origin.startActivityForResult(intent,2);

            }
        });

        //horizontalRVViewHolder.bookCover.setImageResource(horizontalModel.getBookCover());
        //Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /**
     * The type Horizontal rv view holder.
     */
    public class HorizontalRVViewHolder extends RecyclerView.ViewHolder {

        /**
         * The Rating bar.
         */
        RatingBar ratingBar;
        /**
         * The Book cover.
         */
        ImageView bookCover;

        /**
         * Instantiates a new Horizontal rv view holder.
         *
         * @param itemView the item view
         */
        public HorizontalRVViewHolder(View itemView) {
            super(itemView);
            ratingBar = (RatingBar)itemView.findViewById(R.id.bookRating);
            bookCover = (ImageView)itemView.findViewById(R.id.bookCover);
        }
    }
}
