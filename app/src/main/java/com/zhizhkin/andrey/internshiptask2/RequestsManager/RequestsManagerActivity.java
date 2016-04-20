package com.zhizhkin.andrey.internshiptask2.RequestsManager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zhizhkin.andrey.internshiptask2.R;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments.RequestsFragment;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments.RequestsFragmentListView;
import com.zhizhkin.andrey.internshiptask2.RequestsManager.Fragments.RequestsFragmentRecyclerView;
import com.zhizhkin.andrey.internshiptask2.Model.RequestsManager;
import com.zhizhkin.andrey.internshiptask2.Model.UserRequest;

import java.util.ArrayList;

public class RequestsManagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requests_manager_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViewPager();

    }

    private void initViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.requestsManagerViewPager);
        ArrayList<RequestsFragment> pages = new ArrayList<>();
        pages.add(createPage(UserRequest.StatusType.IN_PROCESS, new RequestsFragmentRecyclerView()));
        pages.add(createPage(UserRequest.StatusType.DONE, new RequestsFragmentRecyclerView()));
        pages.add(createPage(UserRequest.StatusType.WAITING, new RequestsFragmentListView()));
        viewPager.setAdapter(new RequestsMangerViewPagerFragmentAdapter(getSupportFragmentManager(), pages));
        ((TabLayout) findViewById(R.id.requestsManagerTabLayout)).setupWithViewPager(viewPager);
    }

    private RequestsFragment createPage(UserRequest.StatusType status, RequestsFragment fragment) {
        fragment.setRequests(RequestsManager.getInstance().getRequestsWithStatus(status));
        fragment.setTitle(status.toString());
        return fragment;
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
