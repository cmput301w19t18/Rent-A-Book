package com.example.cmput301w19t18.rent_a_book;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {
    Context context;
    private ArrayList<Book> booksRequestedList;
    private ArrayList<String> requesters;
    private DatabaseReference mDatabaseUsers;
    private RecyclerViewClickListener listener;
    private DatabaseReference mDatabase;

    public class InboxViewHolder extends RecyclerView.ViewHolder {
        public ImageView pPic;
        public TextView bookTitle, bookAuthor, requester;
        public ImageButton accept;
        public ImageButton reject;

        public InboxViewHolder(View view) {
            super(view);
            pPic = (ImageView) view.findViewById(R.id.requesterPic);
            bookTitle = (TextView) view.findViewById(R.id.bookTitle);
            bookAuthor = (TextView) view.findViewById(R.id.bookAuthor);
            requester = (TextView) view.findViewById(R.id.requestedBy);
            accept = (ImageButton) view.findViewById(R.id.accept);
            reject = (ImageButton) view.findViewById(R.id.reject);

            mDatabase = FirebaseDatabase.getInstance().getReference().child("Books");
        }
    }

    public InboxAdapter(Context context, ArrayList<Book> booksRequestedList, ArrayList<String> requesters) {
        this.context = context;
        this.booksRequestedList = booksRequestedList;
        this.requesters = requesters;
    }

    @Override
    public InboxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inbox_item, parent, false);
        return new InboxViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InboxViewHolder holder, final int position) {
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("Users");

        holder.bookTitle.setText(booksRequestedList.get(position).getBtitle());
        holder.bookAuthor.setText(booksRequestedList.get(position).getAuthor());
        holder.requester.setText(booksRequestedList.get(position).getRequestedBy());
        String cover = "http://covers.openlibrary.org/b/isbn/" + booksRequestedList.get(position).getISBN() + "-M.jpg";

        Picasso.get().load(cover).into(holder.pPic);
        /*
        if (requesters.get(position).equals(mDatabaseUsers.child("email"))) {
            String uri = mDatabaseUsers.child("email").child("uri").toString();
            Picasso.get().load(uri).into(holder.pPic);
        }
        */

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //booksRequestedList.get(position).setBorrowedBy(requesters.get(position));
                Toast.makeText(context,requesters.get(position) + " borrowed " + booksRequestedList.get(position).getBtitle(),Toast.LENGTH_LONG).show();
                //booksRequestedList.clear();
                // update firebase
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            Book b = snapshot.getValue(Book.class);
                            if (b.getBtitle().contains(booksRequestedList.get(position).getBtitle())) {
                                String key = snapshot.getKey();

                                DatabaseReference book_ref = FirebaseDatabase.getInstance().getReference("Books").child(key);
                                // change status to borrowed
                                book_ref.child("borrowedBy").setValue(requesters.get(position));
                                book_ref.child("bstatus").setValue("Borrowed");
                                book_ref.child("requestedBy").setValue("");
                                // when implemented; take user to maps to set location
                                // clear all other requests for this particular book
                                notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return booksRequestedList.size();
    }

}
