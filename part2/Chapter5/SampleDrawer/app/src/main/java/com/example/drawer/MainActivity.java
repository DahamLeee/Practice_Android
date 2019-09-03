package com.example.drawer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if(id == R.id.menu1){
           Toast.makeText(this, "첫 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
           onFragmentSelected(0, null);
       }else if(id == R.id.menu2){
           Toast.makeText(this, "두 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
           onFragmentSelected(1, null);
       }else if(id == R.id.menu3){
           Toast.makeText(this, "세 번째 메뉴 선택됨", Toast.LENGTH_LONG).show();
           onFragmentSelected(2, null);
       }
       DrawerLayout drawer = findViewById(R.id.drawer_layout);
       drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;
        if(position == 0){
            curFragment = Fragment1.newInstacne();
            toolbar.setTitle("첫 번째 화면");
        }else if(position == 1){
            curFragment = fragment2;
            toolbar.setTitle("두 번째 화면");
        }else if(position == 2){
            curFragment = fragment3;
            toolbar.setTitle("세 번째 화면");
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).addToBackStack(null).commit();
    }
}
