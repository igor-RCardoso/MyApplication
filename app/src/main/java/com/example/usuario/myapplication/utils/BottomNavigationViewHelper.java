package com.example.usuario.myapplication.utils;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.usuario.myapplication.DiscoverFragment;
import com.example.usuario.myapplication.MainActivity;
import com.example.usuario.myapplication.R;
import com.example.usuario.myapplication.SaleFragment;
import com.example.usuario.myapplication.SearchFragment;

import java.lang.reflect.Field;

public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
    public static android.support.v4.app.Fragment setNewItemSelected(BottomNavigationView bottomNavigationView, LinearLayout view, @NonNull MenuItem item, Context ctx) {

        RelativeLayout rl1 = (RelativeLayout) view.findViewById(R.id.relative_layout_1);
        RelativeLayout rl2 = (RelativeLayout) view.findViewById(R.id.relative_layout_2);
        RelativeLayout rl3 = (RelativeLayout) view.findViewById(R.id.relative_layout_3);
        RelativeLayout rl4 = (RelativeLayout) view.findViewById(R.id.relative_layout_4);
        RelativeLayout rl5 = (RelativeLayout) view.findViewById(R.id.relative_layout_5);
        LinearLayout ll1 = (LinearLayout) view.findViewById(R.id.linear_layout_1);

        item.setChecked(true);
        switch (item.getItemId()){
            case R.id.nav_search:
                rl1.setVisibility(View.VISIBLE);
                rl2.setVisibility(View.INVISIBLE);
                rl3.setVisibility(View.INVISIBLE);
                rl4.setVisibility(View.INVISIBLE);
                rl5.setVisibility(View.INVISIBLE);
                ll1.setBackgroundColor(0x66FF6000);
                ll1.getBackground().setAlpha(70);
                return new SearchFragment();

            case R.id.nav_sale:
                rl1.setVisibility(View.INVISIBLE);
                rl2.setVisibility(View.VISIBLE);
                rl3.setVisibility(View.INVISIBLE);
                rl4.setVisibility(View.INVISIBLE);
                rl5.setVisibility(View.INVISIBLE);
                return new SaleFragment();

            case R.id.nav_discover:
                rl1.setVisibility(View.INVISIBLE);
                rl2.setVisibility(View.INVISIBLE);
                rl3.setVisibility(View.VISIBLE);
                rl4.setVisibility(View.INVISIBLE);
                rl5.setVisibility(View.INVISIBLE);
                return new DiscoverFragment();

            case R.id.nav_requests:
                rl1.setVisibility(View.INVISIBLE);
                rl2.setVisibility(View.INVISIBLE);
                rl3.setVisibility(View.INVISIBLE);
                rl4.setVisibility(View.VISIBLE);
                rl5.setVisibility(View.INVISIBLE);
                return null;

            case R.id.nav_profile:
                rl1.setVisibility(View.INVISIBLE);
                rl2.setVisibility(View.INVISIBLE);
                rl3.setVisibility(View.INVISIBLE);
                rl4.setVisibility(View.INVISIBLE);
                rl5.setVisibility(View.VISIBLE);
                return null;


                default: return null;
        }
    }
}