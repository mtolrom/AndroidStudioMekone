package com.mekonetolrom.homework_week1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mekonetolrom.homework_week1.Matches;
import com.mekonetolrom.homework_week1.MatchesFragment.OnListFragmentInteractionListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder> {
    private List<Matches> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MatchesRecyclerViewAdapter(List<Matches> matches, OnListFragmentInteractionListener listener) {
        mValues = matches;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_matches, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.mMatches = mValues.get(position);
        Picasso.get().load(mValues.get(position).imageUrl).into(holder.mImageView);
        holder.mTitleView.setText(mValues.get(position).name);
        holder.mLat.setText("Latitude : " + mValues.get(position).lat);
        holder.mLong.setText("Longitude : " + mValues.get(position).longitude);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the fragment
                    // is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mMatches);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTitleView;
        public final TextView mLat;
        public final TextView mLong;
        public Matches mMatches;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.card_image);
            mTitleView = view.findViewById(R.id.card_title);
            mLat = view.findViewById(R.id.tv_lat);
            mLong = view.findViewById(R.id.tv_long);

            ImageButton favoriteImageButton = itemView.findViewById(R.id.favorite_button);
            favoriteImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the fragment
                        // is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(mMatches);
                    }
                    Toast.makeText(v.getContext(), "You liked " + mTitleView.getText(), Toast.LENGTH_LONG).show();

                }
            });
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }

    public void updateMatchListItems(List<Matches> matches) {
        if (mValues == null) {
            mValues = new ArrayList<>();
        }
        final MatchesDiffCallback diffCallback = new MatchesDiffCallback(mValues, matches);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        mValues.clear();
        mValues.addAll(matches);
        diffResult.dispatchUpdatesTo(this);
    }
}
