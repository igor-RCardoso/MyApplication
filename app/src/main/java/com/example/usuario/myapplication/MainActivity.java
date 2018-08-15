package com.example.usuario.myapplication;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.usuario.myapplication.intaractor.EmployeeInteractor;
import com.example.usuario.myapplication.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    BottomNavigationView bottomNavigationView;
    LinearLayout navBarExternalLayout;
//    private Fragment searchFragment;
    Fragment x;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_layout).setBackgroundResource(R.drawable.bg2);
        //searchFragment = new SearchFragment();
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        navBarExternalLayout = (LinearLayout) findViewById(R.id.layout_navigation_bar2);
        LinearLayout ll = (LinearLayout) findViewById(R.id.linear_layout_1);
        ll.getBackground().setAlpha(1);
        //setFragment(searchFragment);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_bot);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Fragment fragment = BottomNavigationViewHelper.setNewItemSelected(bottomNavigationView, navBarExternalLayout, bottomNavigationView.getMenu().getItem(0), getBaseContext());
        setFragment(fragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                x = BottomNavigationViewHelper.setNewItemSelected(bottomNavigationView, navBarExternalLayout, item, getBaseContext());
                setFragment(x);
                return false;
            }
        });

    }

    public void onClick(View v) {
        SearchView sv = (SearchView) findViewById(R.id.busca);
        sv.setIconified(false);
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
