package com.mekonetolrom.homework_week1;

import android.annotation.TargetApi;

import com.mekonetolrom.homework_week1.FirebaseMatchesModel;
import com.mekonetolrom.homework_week1.Matches;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FirebaseMatchesViewModel {

    FirebaseMatchesModel matchesModel;

    public FirebaseMatchesViewModel() {
        matchesModel = new FirebaseMatchesModel();
    }

    @TargetApi(24)
    public void getMatches(Consumer<ArrayList<Matches>> responseCallBack) {
        matchesModel.getMatches(
                (DataSnapshot dataSnapshot) -> {
                    ArrayList<Matches> matches = new ArrayList<>();
                    for (DataSnapshot matchesSnapshot: dataSnapshot.getChildren()) {
                        Matches itemMatches = matchesSnapshot.getValue(Matches.class);
                        assert itemMatches != null;
                        itemMatches.uid = matchesSnapshot.getKey();
                        matches.add(itemMatches);
                    }
                    responseCallBack.accept(matches);
                },
                (databaseError -> System.out.println("Error reading matches items: " + databaseError))
        );
    }

    public void updateMatchesItem(Matches matches) {
        matchesModel.updateMatchesById(matches);
    }

    public void clear() {
        matchesModel.clear();
    }
}
