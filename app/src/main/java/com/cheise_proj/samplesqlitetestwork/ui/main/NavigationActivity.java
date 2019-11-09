package com.cheise_proj.samplesqlitetestwork.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.cheise_proj.samplesqlitetestwork.R;
import com.cheise_proj.samplesqlitetestwork.ui.auth.AuthActivity;
import com.cheise_proj.samplesqlitetestwork.ui.base.BaseActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

public class NavigationActivity extends BaseActivity {
    private NavController navController;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        FloatingActionButton fabCreate = findViewById(R.id.fab);
        fabCreate.setOnClickListener(v -> navigateToCreateContact());

        setupNavigation();
    }

    private void navigateToCreateContact() {
        Navigation.findNavController(this, R.id.nav_host_fragment)
                .navigate(R.id.createContactFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            navigateToLoginPage();
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToLoginPage() {
        preferenceUtil.setLoginInfo(false, 0);
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupActionBarWithNavController(this, navController, drawerLayout);
        navView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
        });
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout);
    }
}
