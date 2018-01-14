package com.laushkina.anastasia.uiexperiments.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.laushkina.anastasia.uiexperiments.R;
import com.laushkina.anastasia.uiexperiments.view.vision.VisionActivity;

public class MainActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener{

    private float startingPoint;
    private float MIN_DISTANCE = 300;
    private static final int toastDuration = Toast.LENGTH_LONG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initialize();
    }

    private void initialize(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float dpWidth  = outMetrics.widthPixels;
        MIN_DISTANCE = dpWidth/2;

        initializeLeftMenu();
        showHome();
    }

    @Override
    public void onResume(){
        super.onResume();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startingPoint = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endingPoint = event.getX();
                float deltaX = endingPoint - startingPoint;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    showToast("Dismissing.");
                    finish();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void showToast(CharSequence text){
        Toast toast = Toast.makeText(this, text, toastDuration);
        toast.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                showHome();
                break;
            case R.id.nav_drag_n_drop:
                showAnagram();
                break;
            case R.id.nav_vision:
                navigateToVision();
        }

        DrawerLayout drawer = getDrawerLayout();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showHome() {
        changeContent(new HomeFragment());
    }

    public void showAnagram() {
        changeContent(new DragAndDropFragment());
    }

    private void changeContent(Fragment fragment){
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    private void navigateToVision() {
        Intent intent = new Intent(this, VisionActivity.class);
        startActivity(intent);
    }

    private void initializeLeftMenu(){
        Toolbar toolbar = getToolbar();
        // If activity doesn't have toolbar - no action is needed
        if (toolbar == null) return;

        DrawerLayout drawer = getDrawerLayout();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = getNavigationView();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private Toolbar getToolbar(){
        return (Toolbar)findViewById(R.id.toolbar);
    }

    private NavigationView getNavigationView(){
        return (NavigationView) findViewById(R.id.nav_view);
    }

    private DrawerLayout getDrawerLayout(){
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }
}