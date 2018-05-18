package com.mekonetolrom.homework_week1;

import android.support.v7.util.DiffUtil;
import java.util.ArrayList;
import java.util.List;

public class MatchesDiffCallback extends DiffUtil.Callback  {
    private final List<MatchesItem> mOldMatchesList;
    private final List<MatchesItem> mNewMatchesList;

    public MatchesDiffCallback(List<MatchesItem> oldMatchesList, List<MatchesItem> newMatchesList) {
        this.mOldMatchesList = oldMatchesList;
        this.mNewMatchesList = newMatchesList;
    }

    @Override
    public int getOldListSize() {
        return mOldMatchesList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewMatchesList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldMatchesList.get(oldItemPosition).uid == mNewMatchesList.get(
                newItemPosition).uid;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final MatchesItem oldMatches = mOldMatchesList.get(oldItemPosition);
        final MatchesItem newMatches = mNewMatchesList.get(newItemPosition);

        return oldMatches.name.equals(newMatches.name);
    }

    //@Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
