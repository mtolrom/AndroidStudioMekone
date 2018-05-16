package com.mekonetolrom.homework_week1;

import com.mekonetolrom.homework_week1.MatchesItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

public class FirebaseMatchesModel {

    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;

    public FirebaseMatchesModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }

    public void addMatchesItem(MatchesItem item) {
        DatabaseReference matchesItemsRef = mDatabase.child("matchesItems");
        matchesItemsRef.push().setValue(item);
    }

    public void getMatchesItems(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        DatabaseReference matchesItemsRef = mDatabase.child("matchesItems");
        ValueEventListener matchesItemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);
            }
        };
        matchesItemsRef.addValueEventListener(matchesItemsListener);
        listeners.put(matchesItemsRef, matchesItemsListener);
    }

    public void updateMatchesItemById(MatchesItem item) {
        DatabaseReference matchesItemsRef = mDatabase.child("matchesItems");
        matchesItemsRef.child(item.uid).setValue(item);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }
}
