package com.example.dimpy.whemsley;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static FragmentManager fragmentManager;
    ActionBar actionBar;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_add:
                    if (getActiveFragment()) return true;
                    actionBar.hide();
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                            .replace(R.id.content,
                                    new addPackage_frag(),
                                    "addPackage_frag").commit();

                    return true;
                case R.id.navigation_viewTrolly:
                    if (getActiveFragment()) return true;
                    actionBar.hide();
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                            .replace(R.id.content,
                                    new viewTrolly_frag(),
                                    "viewTrolly_frag").commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;

                case R.id.navigation_search:
                    if (getActiveFragment()) return true;
                    actionBar.hide();
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                            .replace(R.id.content,
                                    new searchPackage_frag(),
                                    "searchPackage_frag").commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;

                case R.id.navigation_pastTransactions:
                    if (getActiveFragment()) return true;
                    actionBar.hide();
                    fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                            .replace(R.id.content,
                                    new viewHistory_frag(),
                                    "viewHistory_frag").commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;

                case R.id.navigation_aboutMe:
                    if (getActiveFragment()) return true;
                    actionBar.show();
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.content,
                                    new aboutMe_frag(),
                                    "aboutMe_frag").commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;


            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Drawable dr = getResources().getDrawable(R.drawable.redwarehouse);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 55, 55, true));
        actionBar.setIcon(d);
        actionBar.setDisplayUseLogoEnabled(Boolean.TRUE);
        //actionBar.hide();

        fragmentManager = getSupportFragmentManager();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("logged_in", true); // Storing boolean - true/false
        editor.putString("name", "Sarah Jones"); // Storing string
        editor.putString("phone", "9898938475"); // Storing string
        editor.putString("sex", "F"); // Storing string
        editor.putBoolean("animate_bit", false); //Storing Boolean
        editor.putInt("tans_id", -1); //Storing Integer
        editor.commit(); // commit changes

        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                .replace(R.id.content,
                        new aboutMe_frag(),
                        "aboutMe_frag").commit();
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(2).setChecked(Boolean.TRUE);



    }

    public boolean getActiveFragment() {
        connectTrollyDets_frag myFragment0 = (connectTrollyDets_frag) fragmentManager.findFragmentByTag("connectTrollyDets_frag");
        aboutMe_frag aboutMe = (aboutMe_frag) fragmentManager.findFragmentByTag("aboutMe_frag");

        if (myFragment0 != null && myFragment0.isVisible()) {
            navigation.setVisibility(View.GONE);
            return true;
        } else if (aboutMe != null && aboutMe.isVisible()) {
            navigation.getMenu().getItem(2).setChecked(Boolean.TRUE);
            actionBar.show();
            return false;

        } else {
            return false;
        }
    }

}
