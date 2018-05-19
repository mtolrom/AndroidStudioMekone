package com.mekonetolrom.homework_week1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mekonetolrom.homework_week1.MatchesFragment.OnListFragmentInteractionListener;
import java.util.List;
import android.support.v7.util.DiffUtil;

/**
 * {@link RecyclerView.Adapter} that can display a {@link MatchesItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class MyMatchesItemRecyclerViewAdapter extends RecyclerView.Adapter<MyMatchesItemRecyclerViewAdapter.ViewHolder>  {
    private final List<MatchesItem> mValues;
    //private final List<MatchesItem> mMatches;
    private final OnListFragmentInteractionListener mListener;

    public MyMatchesItemRecyclerViewAdapter(List<MatchesItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        //mMatches = items;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_matches, parent, false);
        return new ViewHolder(view);
    }

    public void updateMatchesListItems(List<MatchesItem> matches) {
        final MatchesDiffCallback diffCallback = new MatchesDiffCallback(this.mValues, matches);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.mValues.clear();
        this.mValues.addAll(matches);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.format("%s", mValues.get(position).liked));
        holder.mContentView.setText(mValues.get(position).name);
        holder.mContentView.setText(mValues.get(position).imageUri);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public MatchesItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
