package com.henry.clientesnuevos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import Model.Variables;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Context context;
    static LayoutInflater inflater;
    private static final String TAG = "myTag";

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
            Variables.setUrl(settings.getString("Conection", ""));
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
            if (Variables.getFragment().equals("")){
                //super.onBackPressed();
            }
            else {
                if(Variables.getFragment().equals("AccountsReceivableGroupFragment")
                        ||(Variables.getFragment().equals("ClientsOnCreditGroupFragment"))
                        ||Variables.getFragment().equals("NewClientsFragment")
                        ||Variables.getFragment().equals("ProviderDVIFragment")
                        ||Variables.getFragment().equals("ConfigurationFragment")
                        ||Variables.getFragment().equals("ProductsListFragment")
                        ||Variables.getFragment().equals("ImportWebActivity")
                        ||Variables.getFragment().equals("AccountBankActivity")){
                    Variables.setFragment("");
                    Variables.setType_GruPK("");
                    Variables.setEmailCliN("");
                    HomeFragment fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament, fragment)
                            .commit();
                }else if(Variables.getFragment().equals("ClientListFragment") && !Variables.getGruPK().equals("0")){
                    Variables.setFragment("AccountsReceivableGroupFragment");
                    AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
                    Bundle bundle = new Bundle();
                    if(Variables.getType_GruPK().equals("cxc"))
                        bundle.putString("param1","cxc");

                    else if(Variables.getType_GruPK().equals("cpa"))
                        bundle.putString("param1","cpa");
                    fragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament, fragment)
                            .commit();
                }else if(Variables.getFragment().equals("ClientDetailActivity")) {
                    Variables.setFragment("ClientListFragment");
                }else if(Variables.getFragment().equals("CreateDVIActivity")){
                    Variables.setFragment(("ProviderDVIFragment"));
                    Variables.set_pro_dvi(-1);
                }else if(Variables.getFragment().equals("CPAFragment")){
                    Variables.setFragment("ClientListFragment");
                    ClientListFragment fragment = new ClientListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("param1",Variables.getPositionGru());
                    bundle.putString("param2","cpa");
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament, fragment)
                            .commit();
                }else if(Variables.getFragment().equals("CheckPriceListFragment")){
                    if(!Variables.getEmailCliN().equals("")){
                        Variables.setFragment("NewClientsFragment");Variables.setEmailCliN("");
                        Variables.setGruPK("0"); Variables.sePositionGru("0");
                        NewClientsFragment fragment = new NewClientsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frament, fragment)
                                .commit();
                    }else{
                        Variables.setFragment("ClientListFragment");
                       Variables.setGruPK("0"); Variables.sePositionGru("0");
                        ClientListFragment fragment = new ClientListFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("param1", Variables.getPositionGru());
                        bundle.putString("param2", Variables.getGruPK());
                        fragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frament, fragment)
                                .commit();
                    }
                }else if(Variables.getFragment().equals("RegisterClientFragment")){
                    Variables.setFragment("NewClientsFragment");Variables.setEmailCliN("");
                    Variables.setGruPK("0"); Variables.sePositionGru("0");
                    NewClientsFragment fragment = new NewClientsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament, fragment)
                            .commit();
                }else if(Variables.getFragment().equals("CreateDVIActivity")){
                    Variables.setFragment("ProviderDVIFragment");
                }else if(Variables.getFragment().equals("PaymentDetailFragment")){
                    Variables.setFragment("ProviderDVIFragment");Variables.setEmailCliN("");
                    Variables.setGruPK(""); Variables.sePositionGru("0");
                    ProviderDVIFragment fragment = new ProviderDVIFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament, fragment)
                            .commit();
                }else if(Variables.getFragment().equals("CreateDetailDVIFragment")){
                    Variables.setFragment("PaymentDetailFragment");
                    PaymentDetailFragment fragment1 = new PaymentDetailFragment(Variables.getiD(), Variables.getidpro(), Integer.valueOf(Variables.getPosition()));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament, fragment1)
                            .commit();
                }
            }
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
        if (id == R.id.sign_off) {
            SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
            settings.edit().clear().apply();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_AccountsReceivable) {
            Variables.setFragment("AccountsReceivableGroupFragment");
            Variables.setType_GruPK("cxc");Variables.setEmailCliN("");
            Log.d(TAG, Variables.getType_GruPK());
            AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","cxc");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        } else if (id == R.id.nav_PaidAccounts) {
            Variables.setFragment("AccountsReceivableGroupFragment");
            Variables.setType_GruPK("cpa");Variables.setEmailCliN("");
            AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","cpa");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        } else if (id == R.id.nav_ClientsOnCredit) {
            Variables.setFragment("ClientsOnCreditGroupFragment");Variables.setEmailCliN("");
            Variables.setGruPK("0"); Variables.sePositionGru("0");
            ClientsOnCreditGroupFragment fragment = new ClientsOnCreditGroupFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        } else if (id == R.id.nav_NewClients) {
            Variables.setFragment("NewClientsFragment");Variables.setEmailCliN("");
            Variables.setGruPK("0"); Variables.sePositionGru("0");
            NewClientsFragment fragment = new NewClientsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        } else if (id == R.id.nav_ProvidersDVI) {
            Variables.setFragment("ProviderDVIFragment");
            Variables.setEmailCliN("");
            Variables.setGruPK("");
            Variables.sePositionGru("0");
            ProviderDVIFragment fragment = new ProviderDVIFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        }else if(id == R.id.nav_ListProducts) {
            Variables.setFragment("ProductsListFragment");
            Variables.setEmailCliN("");
            Variables.setGruPK("");
            Variables.sePositionGru("0");
            ProductsListFragment fragment = new ProductsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","");
            bundle.putString("param2", "");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        } else if (id == R.id.nav_ImportWeb) {
            Variables.setFragment("ImportWebActivity");Variables.setEmailCliN("");
            Variables.setGruPK(""); Variables.sePositionGru("0");
            Intent intent = new Intent(context, ImportWebActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_Configuration) {
            Variables.setFragment("ConfigurationFragment");
            ConfigurationFragment fragment = new ConfigurationFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frament, fragment)
                    .commit();
        } else if (id == R.id.nav_accounts_bank) {
            Variables.setFragment("AccountBankActivity");Variables.setEmailCliN("");
            Variables.setGruPK(""); Variables.sePositionGru("0");
            Intent intent = new Intent(context, AccountBankActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_send_contact) {
            Intent intent = new Intent(context, ContactSendActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Manejo de Gerencia de " + getResources().getString(R.string.company_name));
                String sAux = "\nPermíteme recomendarte esta aplicación\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.henry.clientesnuevos";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Compartir en"));
            } catch(Exception e) {
                //e.toString();
            }

        } else if (id == R.id.nav_contact) {
            ViewUserData();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ViewUserData(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout = inflater.inflate(R.layout.user_data, null);
        builder.setView(layout);
        final AlertDialog alert = builder.create();
        android.support.design.widget.TextInputEditText id = (android.support.design.widget.TextInputEditText) layout.findViewById(R.id.id_user);
        Button accept = (Button) layout.findViewById(R.id.buttonAccept);
        id.setText(Variables.getLanid().toString().trim());
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(id.getWindowToken(), 0);
        accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.cancel();
                    }
                }
        );
        alert.show();

    }
}
