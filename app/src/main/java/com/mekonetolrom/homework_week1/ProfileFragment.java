package com.mekonetolrom.homework_week1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;


import android.net.Uri;


public class ProfileFragment extends Fragment {

    private Button btn;
    private TextView tvName, tvUsername, tvEmail, tvAge, tvOccupation, tvDescription;
    private  String nameParam, usernameParam, emailParam, ageParam, occupationParam, descriptionParam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = v.findViewById(R.id.tv_greeting);
        tvUsername = v.findViewById(R.id.tv_username);
        tvEmail = v.findViewById(R.id.tv_email);
        tvAge = v.findViewById(R.id.tv_age);
        tvOccupation = v.findViewById(R.id.tv_occupation);
        tvDescription = v.findViewById(R.id.tv_description);


        if(getArguments() != null) {
            nameParam = getArguments().getString("name");
            usernameParam = getArguments().getString("username");
            emailParam = getArguments().getString("email");
            ageParam = getArguments().getString("age");
            occupationParam = getArguments().getString("occupation");
            descriptionParam = getArguments().getString("description");

            tvName.setText(nameParam);
            tvUsername.setText(usernameParam);
            tvEmail.setText(emailParam);
            tvAge.setText(ageParam);
            tvOccupation.setText(occupationParam);
            tvDescription.setText(descriptionParam);
        }
        if(nameParam == "" || nameParam == null){
            LinearLayout linearLayout1 = (LinearLayout) v.findViewById(R.id.linearLayout1);
            ImageView image = new ImageView(getContext());
            image.setBackgroundResource(R.drawable.wait);
            linearLayout1.addView(image);
        }


        btn = (Button) v.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThankyouActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_profile, parent, false));
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private static final int LENGTH = 18;

        public ContentAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // no-op
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }

}

