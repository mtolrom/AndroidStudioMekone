package com.mekonetolrom.homework_week1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class ProfileFragment extends Fragment {

    private Button btn;
    private TextView tvName, tvUsername, tvEmail, tvAge, tvOccupation, tvDescription;
    private  String nameParam, usernameParam, emailParam, ageParam, occupationParam, descriptionParam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        if (getArguments() != null) {
            nameParam = getArguments().getString("name");
            usernameParam = getArguments().getString("username");
            emailParam = getArguments().getString("email");
            ageParam = getArguments().getString("age");
            occupationParam = getArguments().getString("occupation");
            descriptionParam = getArguments().getString("description");
        }
        */

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = v.findViewById(R.id.tv_greeting);
        tvUsername = v.findViewById(R.id.tv_username);
        tvEmail = v.findViewById(R.id.tv_email);
        tvAge = v.findViewById(R.id.tv_age);
        tvOccupation = v.findViewById(R.id.tv_occupation);
        tvDescription = v.findViewById(R.id.tv_description);

        tvName.setText(nameParam);
        tvUsername.setText(usernameParam);
        tvEmail.setText(emailParam);
        tvAge.setText(ageParam);
        tvOccupation.setText(occupationParam);
        tvDescription.setText(descriptionParam);

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

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        nameParam = getArguments().getString("name");
        usernameParam = getArguments().getString("username");
        emailParam = getArguments().getString("email");
        ageParam = getArguments().getString("age");
        occupationParam = getArguments().getString("occupation");
        descriptionParam = getArguments().getString("description");

    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            nameParam = savedInstanceState.getBundle("name").toString();
            //nameParam = getArguments().getString("name");
            usernameParam = getArguments().getString("username");
            emailParam = getArguments().getString("email");
            ageParam = getArguments().getString("age");
            occupationParam = getArguments().getString("occupation");
            descriptionParam = getArguments().getString("description");
        }
    }
    */
}

