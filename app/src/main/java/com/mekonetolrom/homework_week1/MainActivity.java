package com.mekonetolrom.homework_week1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MatchesFragment.OnListFragmentInteractionListener{

    private String button_text;
    private TextView textView, textUsername, textEmail, textAge, textOccupation, textDescription;
    private static String name, imageUri; //username, email, age, occupation, description;
    private static boolean liked;
    private static MatchesItem mItem;
    private FragmentManager manager;
    private FirebaseMatchesViewModel viewModel;

    @Override
    public void onListFragmentInteraction(MatchesItem item) {
        item.liked = true;
        viewModel.updateMatchesItem(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Intent intent = getIntent();
        //if(intent != null) {
            /*
            assert b != null;
            if (b != null) {
                name = b.getString("name");
                imageUri = b.getString("imageUri");
                liked = false;
                //name = b.getString("name");
                //username = b.getString("username");
                //email = b.getString("email");
                //age = b.getString("age");
                //occupation = b.getString("occupation");
                //description = b.getString("description");
            }
            */
        //}

        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public void signUp(View View)
    {
        button_text = ((Button) View).getText().toString();
        if (button_text.equals("Sign Up")) {
            Intent it = new Intent(this, ThankyouActivity.class);
            startActivity(it);
        }
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager(), name, imageUri, liked); // username, email, age, occupation, description);
        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new MatchesFragment(), "Matches");
        adapter.addFragment(new SettingsFragment(), "Settings");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager, String na, String im, boolean lk) {
            super(manager);
            name = na;
            imageUri = im;
            liked = lk;
            //name = na;
            //username = us;
            //email = em;
            //age = ag;
            //occupation = oc;
            //description = de;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            Bundle bundle = new Bundle();

            bundle.putString("name", name);
            bundle.putString("imageUri", imageUri);
            bundle.putBoolean("liked", false);
            //bundle.putString("name", name);
            //bundle.putString("username", username);
            //bundle.putString("email", email);
            //bundle.putString("age", age);
            //bundle.putString("occupation", occupation);
            //bundle.putString("description", description);
            fragment.setArguments(bundle);

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
