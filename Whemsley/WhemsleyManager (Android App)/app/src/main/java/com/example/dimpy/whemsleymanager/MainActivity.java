package com.example.dimpy.whemsleymanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static FragmentManager fragmentManager;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "View Warehouse", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content,
                                new viewWarehouse_frag(),
                                "viewWarehouse_frag").commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();


        fragmentManager
                .beginTransaction()
                .replace(R.id.content,
                        new mainActivity_frag(),
                        "mainActivity_frag").commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view_warehouse) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content,
                            new viewWarehouse_frag(),
                            "viewWarehouse_frag").commit();

        } else if (id == R.id.nav_search_package) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content,
                            new searchPackage_frag(),
                            "searchPackage_frag").commit();

        } else if (id == R.id.nav_view_trolly) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content,
                            new viewTrolly_frag(),
                            "viewTrolly_frag").commit();

        } else if (id == R.id.nav_view_emp) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content,
                            new viewEmp_frag(),
                            "viewEmp_frag").commit();

        } else if (id == R.id.nav_transactions) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content,
                            new viewTransactions_frag(),
                            "viewTransactions_frag").commit();

        } else if (id == R.id.nav_lockdown) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.content,
                            new lockDown_frag(),
                            "lockDown_frag").commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
