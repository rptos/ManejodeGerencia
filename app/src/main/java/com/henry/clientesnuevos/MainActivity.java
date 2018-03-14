package com.henry.clientesnuevos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import Model.Variables;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    static LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = (Context) this;
        this.inflater = LayoutInflater.from(context);

        SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
        if (settings.getString("USR_PK", null)!=null) {
            Variables.setId(settings.getString("USR_PK", ""));
            Variables.setLanid(settings.getString("USR_LANID", ""));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frament, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
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

        if (id == R.id.nav_AccountsReceivable) {
            /*Variables.setFragment("AccountsReceivableGroupFragment");
            Variables.setType_GruPK("cxc");
            AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","cxc");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();*/
        } else if (id == R.id.nav_PaidAccounts) {
            /*Variables.setFragment("AccountsReceivableGroupFragment");
            Variables.setType_GruPK("cpa");
            AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","cpa");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();*/
        } else if (id == R.id.nav_ClientsOnCredit) {
            /*Variables.setFragment("ClientListFragment");
            Variables.setGruPK("0"); Variables.sePositionGru("0");
            ClientListFragment fragment = new ClientListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1", Variables.getPositionGru());
            bundle.putString("param2", Variables.getGruPK());
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();*/
        } else if (id == R.id.nav_NewClients) {
            /*Variables.setFragment("NewClientsFragment");
            Variables.setGruPK("0"); Variables.sePositionGru("0");
            NewClientsFragment fragment = new NewClientsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();*/
        } else if (id == R.id.nav_ProvidersDVI) {

        } else if (id == R.id.nav_Configuration) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
