package com.pdmproyect.ifmsaelsalvador.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pdmproyect.ifmsaelsalvador.R;
import com.pdmproyect.ifmsaelsalvador.api.ClientRequest;
import com.pdmproyect.ifmsaelsalvador.database.IFMSAViewModel;
import com.pdmproyect.ifmsaelsalvador.fragments.CommitteeFragment;
import com.pdmproyect.ifmsaelsalvador.fragments.CreateProjectsFragment;
import com.pdmproyect.ifmsaelsalvador.fragments.ProfileFragment;
import com.pdmproyect.ifmsaelsalvador.fragments.ProjectsFragment;

import static java.lang.Boolean.getBoolean;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private IFMSAViewModel viewModel;
    private boolean isFirstEntry = true;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("first")) {
                isFirstEntry = getBoolean("first");
            }
        }
        bindViews(isFirstEntry);
    }

    private void bindViews(boolean flag) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        viewModel = ViewModelProviders.of(this).get(IFMSAViewModel.class);
        if (flag) {
            setFirstView();
        }
        ClientRequest.getProjects(viewModel, getToken());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("first", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        String title = "";
        Fragment fragment = null;
        switch (id) {
            case R.id.perfil_menu:
                fragment = new ProfileFragment();
                title = "Profile";
                break;
            case R.id.comites_menu:
                fragment = new CommitteeFragment();
                title = "Committees";
                break;
            case R.id.proyectos_menu:
                fragment = new ProjectsFragment();
                title = "Projects";
                break;
            case R.id.solicitar_proyecto_menu:
                fragment = new CreateProjectsFragment();
                break;
            case R.id.settings_menu:
                break;
            default:
                break;
        }
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_content_frame, fragment).commit();
        getSupportActionBar().setTitle(title);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String getToken() {
        String token = getSharedPreferences(getString(R.string.sharedpreferences_name), Context.MODE_PRIVATE)
                .getString(getString(R.string.sharedpreferences_key), "");
        return token;
    }

    private void setFirstView() {
        if (isFirstEntry) {
            navigationView.setCheckedItem(R.id.perfil_menu);
            navigationView.getMenu().performIdentifierAction(R.id.perfil_menu, 0);
        }
    }

}
