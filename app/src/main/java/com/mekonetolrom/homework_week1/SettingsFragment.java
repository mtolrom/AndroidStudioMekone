package com.mekonetolrom.homework_week1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.Toast;

import com.mekonetolrom.homework_week1.SettingsDao;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment {

    public EditText edit_email;
    public EditText edit_dailyReminder;
    public EditText edit_minDistance;
    public EditText edit_maxDistance;

    public EditText edit_maleFemale;
    public EditText edit_accountStatus;

    public EditText edit_minAge;
    public EditText edit_maxAge;
    public EditText edit_photoUrl;
    public ImageView img_PhotoUrl;

    private Button btn;
    private AppDatabase db = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        db = AppDatabase.getAppDatabase(this.getActivity().getApplicationContext());

        edit_email = v.findViewById(R.id.edit_st_email);
        edit_dailyReminder = v.findViewById(R.id.edit_st_dailyreminder);
        edit_minDistance = v.findViewById(R.id.edit_st_mindistance);
        edit_maxDistance = v.findViewById(R.id.edit_st_maxdistance);
        edit_maleFemale = v.findViewById(R.id.st_malefemale);
        edit_accountStatus = v.findViewById(R.id.st_publicprivate);
        edit_minAge = v.findViewById(R.id.edit_st_minage);
        edit_maxAge = v.findViewById(R.id.edit_st_maxage);
        edit_photoUrl = v.findViewById(R.id.edit_st_photourl);
        //img_PhotoUrl = v.findViewById(R.id.email);

        btn = (Button) v.findViewById(R.id.btnSettings);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Setting setting = new Setting();
                setting.setEmail(edit_email.getText().toString());
                setting.setAccountStatus(edit_accountStatus.getText().toString());
                setting.setMaleFemale(edit_maleFemale.getText().toString());
                setting.setDailyReminder(edit_dailyReminder.getText().toString());
                setting.setMaxAge(edit_maxAge.getText().toString());
                setting.setMinAge(edit_minAge.getText().toString());
                setting.setMaxDistance(edit_maxDistance.getText().toString());
                setting.setMinDistance(edit_minDistance.getText().toString());
                setting.setPhotoUrl(edit_photoUrl.getText().toString());

                if(edit_email.getText().toString() != null && !edit_email.getText().toString().isEmpty() && edit_maleFemale.getText().toString() != null && !edit_maleFemale.getText().toString().isEmpty() && edit_accountStatus.getText().toString() != null && !edit_accountStatus.getText().toString().isEmpty()) {
                    insertAll(db, setting);
                    Toast.makeText(getContext(), "Profile saved, " + setting.getEmail(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(), "Missing required fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

        List<Setting> settings = new ArrayList<Setting>();
        settings = getAll(db);

        if(settings.size() > 0)
        {
            Setting s = new Setting();
            s = settings.get(0);
            edit_email.setText(s.getEmail());
            edit_dailyReminder.setText(s.getDailyReminder());
            edit_minDistance.setText(s.getMinDistance());
            edit_maxDistance.setText(s.getMaxDistance());
            edit_maleFemale.setText(s.getMaleFemale());
            edit_accountStatus.setText(s.getAccountStatus());
            edit_minAge.setText(s.getMinAge());
            edit_maxAge.setText(s.getMaxAge());
            edit_photoUrl.setText(s.getPhotoUrl());
        }

        return v;
    }


    private static Setting insertAll(final AppDatabase db, Setting setting) {
        db.settingsDao().insertAll(setting);
        return setting;
    }

    private static List<Setting> getAll(final AppDatabase db) {
        List<Setting> settings = db.settingsDao().getAll();
        return settings;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_settings, parent, false));
        }
    }
}
