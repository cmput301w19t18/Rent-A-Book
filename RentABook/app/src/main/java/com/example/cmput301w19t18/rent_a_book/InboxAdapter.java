package com.example.cmput301w19t18.rent_a_book;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.cmput301w19t18.rent_a_book.HomeActivity.ADDING;

/**
 * Created by ikramshire on 2019-03-30.
 */

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {
    private ArrayList<Book> mInboxBookList;


    public static class InboxViewHolder extends RecyclerView.ViewHolder {

        public static ArrayList<Book> BookList;

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
         * The accept button.
         */
        public FloatingActionButton accept_button;
        /**
         * The reject button.
         */
        public FloatingActionButton reject_button;

        /**
         * Instantiates a new inbox view holder.
         *
         * @param itemView the item view
         */
        public InboxViewHolder(@NonNull View itemView) {
            super(itemView);


            mOwnerPicture = itemView.findViewById(R.id.userPhoto);
            mBookTitle = itemView.findViewById(R.id.bookTitle);
            mBookAuthor = itemView.findViewById(R.id.bookAuthor);
            mOwnerName = itemView.findViewById(R.id.bookOwner);

            accept_button = itemView.findViewById(R.id.accept_fab);
            reject_button = itemView.findViewById(R.id.reject_fab);


        }
    }
    /**
     * Instantiates a new inbox adapter.
     *
     * @param InboxBookList the inbox book list
     */
    public InboxAdapter(ArrayList<Book> InboxBookList) {
        mInboxBookList = InboxBookList;
    }
    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.request_user_item, viewGroup, false);
        InboxAdapter.InboxViewHolder ivh = new InboxAdapter.InboxViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull final InboxViewHolder inboxViewHolder, int i) {

        final Context context = inboxViewHolder.itemView.getContext();
        final Book currentItem = mInboxBookList.get(i);
        final String bookCover = "http://covers.openlibrary.org/b/isbn/" + currentItem.getISBN() + "-M.jpg";

        Picasso.get().load(bookCover).into(inboxViewHolder.mOwnerPicture);

        inboxViewHolder.mBookTitle.setText(currentItem.getBtitle());
        inboxViewHolder.mBookAuthor.setText(currentItem.getAuthor());
        inboxViewHolder.mOwnerName.setText(currentItem.getbOwner());
        DatabaseReference mDatabase2;
        final FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        //This checks if the user is borrowing the book is displays it so it will be clicked
        mDatabase2 = FirebaseDatabase.getInstance().getReference("Books");
        Query query2 = mDatabase2.orderByChild("btitle");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    //Toast.makeText(context,dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Book book = snapshot.getValue(Book.class);

                        if (book.getRequestedBy().contains(mAuth.getCurrentUser().getEmail())){
                            if(book.getBstatus().contains("Borrowed")){
                                inboxViewHolder.reject_button.hide();
                                inboxViewHolder.accept_button.hide();
                                inboxViewHolder.mOwnerName.setText("Click to return this book");

                            }

                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query2.addListenerForSingleValueEvent(eventListener);



        inboxViewHolder.reject_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase;
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();

                mDatabase = FirebaseDatabase.getInstance().getReference("Books");
                Query query = mDatabase.orderByChild("btitle");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            //Toast.makeText(context,dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Book book = snapshot.getValue(Book.class);
                                if (book.getBtitle().contains(currentItem.getBtitle())){
                                    String keyer = snapshot.getKey();

                                    DatabaseReference book_ref = FirebaseDatabase.getInstance().getReference("Books").child(keyer).child("bstatus");
                                    book_ref.setValue("Available");

                                }



                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                query.addListenerForSingleValueEvent(eventListener);
                Toast.makeText(context, "rejected", Toast.LENGTH_LONG).show();
            }
        });
        // accepts the user's offer
        inboxViewHolder.accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.setBstatus("Borrowed");
                Toast.makeText(context,"accepted", Toast.LENGTH_LONG).show();

                DatabaseReference mDatabase;
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();

                mDatabase = FirebaseDatabase.getInstance().getReference("Books");
                Query query = mDatabase.orderByChild("btitle");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Book book = snapshot.getValue(Book.class);
                                if (book.getBtitle().contains(currentItem.getBtitle())){
                                    String keyer = snapshot.getKey();

                                    DatabaseReference book_ref = FirebaseDatabase.getInstance().getReference("Books").child(keyer).child("bstatus");
                                    book_ref.setValue("Borrowed");

                                }
                                if (book.getRequestedBy().contains(mAuth.getCurrentUser().getEmail())){
                                    if(book.getBstatus().contains("Borrowed")){
                                        inboxViewHolder.reject_button.hide();
                                        inboxViewHolder.accept_button.hide();

                                    }

                                }


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                query.addListenerForSingleValueEvent(eventListener);

                //goes to map activity
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("Accepted","1");
                context.startActivity(intent);


            }
        });
        //return the book once the user clicks on the owners name and changes book status to available if the book is borrowed
        inboxViewHolder.mOwnerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase3;
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();


                mDatabase3 = FirebaseDatabase.getInstance().getReference("Books");
                Query query3 = mDatabase3.orderByChild("btitle");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            //Toast.makeText(context,dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Book book = snapshot.getValue(Book.class);

                                if (book.getRequestedBy().contains(mAuth.getCurrentUser().getEmail())){
                                    if(book.getBstatus().contains("Borrowed")){
                                        String keyer = snapshot.getKey();

                                        DatabaseReference book_ref = FirebaseDatabase.getInstance().getReference("Books").child(keyer).child("bstatus");
                                        book_ref.setValue("Available");
                                        Toast.makeText(context,"Book Returned",Toast.LENGTH_LONG).show();


                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                query3.addListenerForSingleValueEvent(eventListener);



            }
        });
        inboxViewHolder.mOwnerPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mDatabase3;
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();

                mDatabase3 = FirebaseDatabase.getInstance().getReference("Books");
                Query query3 = mDatabase3.orderByChild("btitle");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            //Toast.makeText(context,dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Book book = snapshot.getValue(Book.class);

                                if (book.getRequestedBy().contains(mAuth.getCurrentUser().getEmail())){
                                    if(book.getBstatus().contains("Borrowed")){
                                        String keyer = snapshot.getKey();

                                        DatabaseReference book_ref = FirebaseDatabase.getInstance().getReference("Books").child(keyer).child("bstatus");
                                        book_ref.setValue("Available");

                                        Toast.makeText(context,"Book Returned",Toast.LENGTH_LONG).show();


                                    }

                                }


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                query3.addListenerForSingleValueEvent(eventListener);

                Intent book_loc = new Intent(context, MapsActivity.class);
                book_loc.putExtra("borrow", "2");
                context.startActivity(book_loc);

            }
        });


        inboxViewHolder.mOwnerPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference mDatabase4;
                final FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                final String[] bookEmail = {""};

                mDatabase4 = FirebaseDatabase.getInstance().getReference("Books");
                Query query4 = mDatabase4.orderByChild("btitle");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            //Toast.makeText(context,dataSnapshot.getKey(),Toast.LENGTH_LONG).show();
                            for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                                Book book = snapshot.getValue(Book.class);

                                if (book.getRequestedBy().contains(mAuth.getCurrentUser().getEmail())){
                                    if(book.getBstatus().contains("Borrowed")){
                                        String keyer = snapshot.getKey();

                                    }
                                }


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                query4.addListenerForSingleValueEvent(eventListener);

                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("Accepted","2");
                intent.putExtra("Owner", currentItem.getbOwner());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInboxBookList.size();
    }
}
