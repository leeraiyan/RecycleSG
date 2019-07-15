package com.example.recyclesg;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabse;
    private DatabaseReference mReferenceBooks;
    private List<Book> books = new ArrayList<>();


    public FirebaseDatabaseHelper() {
        mDatabse = FirebaseDatabase.getInstance();
        mReferenceBooks = mDatabse.getReference("books");

    }

    public interface  DataStatus{
        void DataIsLoaded(List<Book> books, List<String> keys);
        void DataInserted();
        void DataIsUpdated();
        void DataIsDeleted();

    }
    public void readBooks(final DataStatus dataStatus){
        mReferenceBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Book book = keyNode.getValue(Book.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books, keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
