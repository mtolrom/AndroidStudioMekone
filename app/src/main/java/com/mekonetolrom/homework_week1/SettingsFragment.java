package com.mekonetolrom.homework_week1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

    public static EditText edit_email;
    public static EditText edit_dailyReminder;
    public static EditText edit_minDistance;
    public static EditText edit_maxDistance;

    public static EditText edit_maleFemale;
    public static EditText edit_accountStatus;

    public static EditText edit_minAge;
    public static EditText edit_maxAge;
    public static EditText edit_photoUrl;
    public static ImageView img_PhotoUrl;

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

                String[] emls = new String[5];
                emls[0] = edit_email.getText().toString();
                List<Setting> setts = new ArrayList<Setting>();
                setts = loadAllByIds(db, emls);
                //new GetSettingTask(getActivity(), edit_email.getText().toString()).execute();

                boolean b = false;
                if (edit_email.getText().toString() != null && !edit_email.getText().toString().isEmpty() && edit_maleFemale.getText().toString() != null && !edit_maleFemale.getText().toString().isEmpty() && edit_accountStatus.getText().toString() != null && !edit_accountStatus.getText().toString().isEmpty()) {
                    b = true;
                }

                if (setts.size() == 0) {
                    //insert
                    if (b) {
                        if (edit_maleFemale.getText().toString().toLowerCase() != "f" || edit_maleFemale.getText().toString().toLowerCase() != "m") {
                            if (edit_accountStatus.getText().toString().toLowerCase() != "public" || edit_accountStatus.getText().toString().toLowerCase() != "private") {
                                insertDatabase(getView(), setting);
                                Toast.makeText(getContext(), "Profile saved, " + setting.getEmail(), Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "Missing required fields!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    //update
                    if (b) {
                        if (edit_maleFemale.getText().toString().toLowerCase() != "f" || edit_maleFemale.getText().toString().toLowerCase() != "m") {
                            if (edit_accountStatus.getText().toString().toLowerCase() != "public" || edit_accountStatus.getText().toString().toLowerCase() != "private") {
                                updateDatabase(getView(), setting);
                                Toast.makeText(getContext(), "Profile updated, " + setting.getEmail(), Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "Missing required fields!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //List<Setting> settings = new ArrayList<Setting>();
        //settings = getAll(db);
        new GetSettingTask(getActivity()).execute();

        return v;
    }
    //update
    public void updateDatabase(View view, Setting setting) {
        new UpdateSettingTask(this.getActivity(), setting).execute();
    }

    private static class UpdateSettingTask extends AsyncTask<Void, Void, Setting> {

        private WeakReference<Activity> weakActivity;
        private Setting setting;

        public UpdateSettingTask(Activity activity, Setting setting) {
            weakActivity = new WeakReference<>(activity);
            this.setting = setting;
        }

        @Override
        protected Setting doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return null;
            }

            AppDatabase db = AppDatabase.getAppDatabase(activity.getApplicationContext());
            db.settingsDao().updateSettings(setting);
            return setting;
        }
    }

    //insert
    public void insertDatabase(View view, Setting setting) {
        new InsertSettingTask(this.getActivity(), setting).execute();
    }

    private static class InsertSettingTask extends AsyncTask<Void, Void, Setting> {

        private WeakReference<Activity> weakActivity;
        private Setting setting;

        public InsertSettingTask(Activity activity, Setting setting) {
            weakActivity = new WeakReference<>(activity);
            this.setting = setting;
        }

        @Override
        protected Setting doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return null;
            }

            AppDatabase db = AppDatabase.getAppDatabase(activity.getApplicationContext());
            db.settingsDao().insertAll(setting);
            return setting;
        }
    }

    //get
    private static class GetSettingTask extends AsyncTask<Void, Void, Setting> {

        private WeakReference<Activity> weakActivity;
        //private String userEmail;

        public GetSettingTask(Activity activity) {
            weakActivity = new WeakReference<>(activity);
            //this.userEmail = userEmail;
        }

        @Override
        protected Setting doInBackground(Void... voids) {
            Activity activity = weakActivity.get();
            if (activity == null) {
                return null;
            }

            AppDatabase db = AppDatabase.getAppDatabase(activity.getApplicationContext());
            //String[] emails = {userEmail};
            //List<Setting> users = db.settingsDao().loadAllByIds(emails);
            List<Setting> users = db.settingsDao().getAll();
            if (users.size() <= 0 || users.get(0) == null) {
                return null;
            }
            return users.get(0);
        }
        @Override
        protected void onPostExecute(Setting setting) {
            MainActivity activity = (MainActivity) weakActivity.get();
            if(setting == null || activity == null) {
                return;
            }

            edit_email.setText(setting.getEmail());
            edit_dailyReminder.setText(setting.getDailyReminder());
            edit_minDistance.setText(setting.getMinDistance());
            edit_maxDistance.setText(setting.getMaxDistance());
            edit_maleFemale.setText(setting.getMaleFemale());
            edit_accountStatus.setText(setting.getAccountStatus());
            edit_minAge.setText(setting.getMinAge());
            edit_maxAge.setText(setting.getMaxAge());
            edit_photoUrl.setText(setting.getPhotoUrl());
        }
    }

    //getById


    /*
    private static Setting insertAll(final AppDatabase db, Setting setting) {
        db.settingsDao().insertAll(setting);
        return setting;
    }

    private static List<Setting> getAll(final AppDatabase db) {
        List<Setting> settings = db.settingsDao().getAll();
        return settings;
    }
    */

    private static List<Setting> loadAllByIds(final AppDatabase db, String[] emails) {
        List<Setting> settings = db.settingsDao().loadAllByIds(emails);
        return settings;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_settings, parent, false));
        }
    }
}
