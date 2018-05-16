package com.mekonetolrom.homework_week1;

import com.mekonetolrom.homework_week1.FirebaseMatchesModel;
import com.mekonetolrom.homework_week1.MatchesItem;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FirebaseMatchesViewModel {
    private FirebaseMatchesModel matchesModel;

    public FirebaseMatchesViewModel() {
        matchesModel = new FirebaseMatchesModel();
    }

    public void addMatchesItem(MatchesItem item) {
        matchesModel.addMatchesItem(item);
    }

    public void getMatchesItems(Consumer<ArrayList<MatchesItem>> responseCallback) {
        matchesModel.getMatchesItems(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<MatchesItem> matchesItems = new ArrayList<>();
                    for (DataSnapshot matchesSnapshot : dataSnapshot.getChildren()) {
                        MatchesItem item = matchesSnapshot.getValue(MatchesItem.class);
                        assert item != null;
                        item.uid = matchesSnapshot.getKey();
                        matchesItems.add(item);
                    }
                    responseCallback.accept(matchesItems);
                },
                (databaseError -> System.out.println("Error reading MatchesItems: " + databaseError))
        );
    }

    public void updateMatchesItem(MatchesItem item) {
        matchesModel.updateMatchesItemById(item);
    }

    public void clear() {
        matchesModel.clear();
    }
}
