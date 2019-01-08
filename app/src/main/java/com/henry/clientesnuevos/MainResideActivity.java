package com.henry.clientesnuevos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import Model.Variables;

public class MainResideActivity extends AppCompatActivity implements View.OnClickListener {
    Context context;
    static LayoutInflater inflater;
    private static final String TAG = "myTag";

    private ResideMenu resideMenu;
    private ResideMenuItem itemAccountsReceivable;
    private ResideMenuItem itemPaidAccounts;
    private ResideMenuItem itemClientsOnCredit;
    private ResideMenuItem itemNewClients;
    private ResideMenuItem itemProvidersDVI;
    private ResideMenuItem itemListProducts;
    private ResideMenuItem itemImportWeb;
    private ResideMenuItem itemConfiguration;
    private ResideMenuItem itemAccountsBank;
    private ResideMenuItem itemSendContact;
    private ResideMenuItem itemContact;
    private ResideMenuItem itemCloseSession;

    @Override
    public void onBackPressed(){
        if (resideMenu.isOpened()) {
            resideMenu.closeMenu();
        } else {
            if (Variables.getFragment().equals("")) {
                //super.onBackPressed();
            } else {
                if (Variables.getFragment().equals("AccountsReceivableGroupFragment")
                        || (Variables.getFragment().equals("ClientsOnCreditGroupFragment"))
                        || Variables.getFragment().equals("NewClientsFragment")
                        || Variables.getFragment().equals("ProviderDVIFragment")
                        || Variables.getFragment().equals("ConfigurationFragment")
                        || Variables.getFragment().equals("ProductsListFragment")
                        || Variables.getFragment().equals("ImportWebActivity")
                        || Variables.getFragment().equals("AccountBankActivity")
                        || Variables.getFragment().equals("ShareProductActivity")) {
                    Variables.setFragment("");
                    Variables.setType_GruPK("");
                    Variables.setEmailCliN("");
                    HomeFragment fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment)
                            .commit();
                } else if (Variables.getFragment().equals("ClientListFragment") && !Variables.getGruPK().equals("0")) {
                    Variables.setFragment("AccountsReceivableGroupFragment");
                    AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
                    Bundle bundle = new Bundle();
                    if (Variables.getType_GruPK().equals("cxc"))
                        bundle.putString("param1", "cxc");

                    else if (Variables.getType_GruPK().equals("cpa"))
                        bundle.putString("param1", "cpa");
                    fragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment)
                            .commit();
                } else if (Variables.getFragment().equals("ClientDetailActivity")) {
                    Variables.setFragment("ClientListFragment");
                } else if (Variables.getFragment().equals("CreateDVIActivity")) {
                    Variables.setFragment(("ProviderDVIFragment"));
                    Variables.set_pro_dvi(-1);
                } else if (Variables.getFragment().equals("CPAFragment")) {
                    Variables.setFragment("ClientListFragment");
                    ClientListFragment fragment = new ClientListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("param1", Variables.getPositionGru());
                    bundle.putString("param2", "cpa");
                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment)
                            .commit();
                } else if (Variables.getFragment().equals("CheckPriceListFragment")) {
                    if (!Variables.getEmailCliN().equals("")) {
                        Variables.setFragment("NewClientsFragment");
                        Variables.setEmailCliN("");
                        Variables.setGruPK("0");
                        Variables.sePositionGru("0");
                        NewClientsFragment fragment = new NewClientsFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentReside, fragment)
                                .commit();
                    } else {
                        Variables.setFragment("ClientListFragment");
                        Variables.setGruPK("0");
                        Variables.sePositionGru("0");
                        ClientListFragment fragment = new ClientListFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("param1", Variables.getPositionGru());
                        bundle.putString("param2", Variables.getGruPK());
                        fragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentReside, fragment)
                                .commit();
                    }
                } else if (Variables.getFragment().equals("RegisterClientFragment")) {
                    Variables.setFragment("NewClientsFragment");
                    Variables.setEmailCliN("");
                    Variables.setGruPK("0");
                    Variables.sePositionGru("0");
                    NewClientsFragment fragment = new NewClientsFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment)
                            .commit();
                } else if (Variables.getFragment().equals("CreateDVIActivity")) {
                    Variables.setFragment("ProviderDVIFragment");
                } else if (Variables.getFragment().equals("PaymentDetailFragment")) {
                    Variables.setFragment("ProviderDVIFragment");
                    Variables.setEmailCliN("");
                    Variables.setGruPK("");
                    Variables.sePositionGru("0");
                    ProviderDVIFragment fragment = new ProviderDVIFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment)
                            .commit();
                } else if (Variables.getFragment().equals("CreateDetailDVIFragment")) {
                    Variables.setFragment("PaymentDetailFragment");
                    PaymentDetailFragment fragment1 = new PaymentDetailFragment(Variables.getiD(), Variables.getidpro(), Integer.valueOf(Variables.getPosition()));
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment1)
                            .commit();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_reside);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = (Context) this;
        setUpMenu();
        this.inflater = LayoutInflater.from(context);

        SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
        if (settings.getString("USR_PK", null)!=null) {
            Variables.setId(settings.getString("USR_PK", ""));
            Variables.setLanid(settings.getString("USR_LANID", ""));
            Variables.setUrl(settings.getString("Conection", ""));
            Variables.setTypeMenu(settings.getString("Menu", ""));
        }

        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentReside, fragment)
                .commit();
    }

    private void setUpMenu(){
        resideMenu = new ResideMenu(this);

        resideMenu.setBackground(R.drawable.fondo_movil2);
        resideMenu.attachToActivity(this);
        resideMenu.setShadowVisible(true);
        resideMenu.setMenuListener(menuListener);

        resideMenu.setScaleValue(0.52f);

        itemAccountsReceivable = new ResideMenuItem(this, R.drawable.cxc, R.string.navdrawer_AccountsReceivable);
        itemPaidAccounts       = new ResideMenuItem(this, R.drawable.cp, R.string.navdrawer_PaidAccounts);
        itemClientsOnCredit    = new ResideMenuItem(this, R.drawable.credit, R.string.navdrawer_ClientsOnCredit);
        itemNewClients         = new ResideMenuItem(this, R.drawable.newuser, R.string.navdrawer_NewClients);
        itemProvidersDVI       = new ResideMenuItem(this, R.drawable.dvi, R.string.navdrawer_ProvidersDVI);
        itemListProducts       = new ResideMenuItem(this, R.drawable.box2, R.string.navdrawer_ListProducts);
        itemImportWeb          = new ResideMenuItem(this, R.drawable.flechas, R.string.navdrawer_ImportWEB);
        itemAccountsBank       = new ResideMenuItem(this, R.drawable.bank, R.string.navdrawer_share_accounts);
        itemSendContact        = new ResideMenuItem(this, R.drawable.contact_card, R.string.navdrawer_share_contacts);
        itemConfiguration      = new ResideMenuItem(this, R.drawable.ic_menu_manage, R.string.navdrawer_Configuration);
        itemContact            = new ResideMenuItem(this, R.drawable.ic_menu_send, R.string.navdrawer_contact);
        itemCloseSession       = new ResideMenuItem(this, R.drawable.ic_close, R.string.sign_off);

        itemAccountsReceivable.setOnClickListener(this);
        itemPaidAccounts.setOnClickListener(this);
        itemClientsOnCredit.setOnClickListener(this);
        itemNewClients.setOnClickListener(this);
        itemProvidersDVI.setOnClickListener(this);
        itemListProducts.setOnClickListener(this);
        itemImportWeb.setOnClickListener(this);
        itemAccountsBank.setOnClickListener(this);
        itemSendContact.setOnClickListener(this);
        itemConfiguration.setOnClickListener(this);
        itemContact.setOnClickListener(this);
        itemCloseSession.setOnClickListener(this);

        resideMenu.addMenuItem(itemAccountsReceivable, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemPaidAccounts, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemClientsOnCredit, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNewClients, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProvidersDVI, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemListProducts, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemImportWeb, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemAccountsBank, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemSendContact, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemConfiguration, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemContact, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemCloseSession, ResideMenu.DIRECTION_RIGHT);


        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        if (view == itemAccountsReceivable) {
            Variables.setFragment("AccountsReceivableGroupFragment");
            Variables.setType_GruPK("cxc");Variables.setEmailCliN("");
            Log.d(TAG, Variables.getType_GruPK());
            AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","cxc");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentReside, fragment)
                    .commit();
        } else if (view == itemPaidAccounts) {
            Variables.setFragment("AccountsReceivableGroupFragment");
            Variables.setType_GruPK("cpa");Variables.setEmailCliN("");
            AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1","cpa");
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentReside, fragment)
                    .commit();
        } else if (view == itemClientsOnCredit) {
            Variables.setFragment("ClientsOnCreditGroupFragment");Variables.setEmailCliN("");
            Variables.setGruPK("0"); Variables.sePositionGru("0");
            ClientsOnCreditGroupFragment fragment = new ClientsOnCreditGroupFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentReside, fragment)
                    .commit();
        } else if (view == itemNewClients) {
            Variables.setFragment("NewClientsFragment");Variables.setEmailCliN("");
            Variables.setGruPK("0"); Variables.sePositionGru("0");
            NewClientsFragment fragment = new NewClientsFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentReside, fragment)
                    .commit();
        } else if (view == itemProvidersDVI) {
            Variables.setFragment("ProviderDVIFragment");
            Variables.setEmailCliN("");
            Variables.setGruPK("");
            Variables.sePositionGru("0");
            ProviderDVIFragment fragment = new ProviderDVIFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentReside, fragment)
                    .commit();
        }else if(view == itemListProducts) {
            Variables.setFragment("ProductsListFragment");
            Variables.setEmailCliN("");
            Variables.setGruPK("");
            Variables.sePositionGru("0");
            SelectWarehouse();
        } else if (view == itemImportWeb) {
            Variables.setFragment("ImportWebActivity");Variables.setEmailCliN("");
            Variables.setGruPK(""); Variables.sePositionGru("0");
            Intent intent = new Intent(context, ImportWebActivity.class);
            startActivity(intent);
        } else if (view == itemConfiguration) {
            Variables.setFragment("ConfigurationFragment");
            ConfigurationFragment fragment = new ConfigurationFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentReside, fragment)
                    .commit();
        } else if (view == itemAccountsBank) {
            Variables.setFragment("AccountBankActivity");Variables.setEmailCliN("");
            Variables.setGruPK(""); Variables.sePositionGru("0");
            Intent intent = new Intent(context, AccountBankActivity.class);
            startActivity(intent);
        } else if (view == itemSendContact) {
            Intent intent = new Intent(context, ContactSendActivity.class);
            startActivity(intent);
        } else if (view == itemContact) {
            ViewUserData();
        }else if (view == itemCloseSession){
            SharedPreferences sp = context.getSharedPreferences("profile", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Menu", "modern");
            Variables.setTypeMenu("modern");
            editor.commit();

            SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
            settings.edit().clear().apply();
            Intent intent = new Intent(context, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }

        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            //Toast.makeText(context, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            //Toast.makeText(context, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    public ResideMenu getResideMenu(){
        return resideMenu;
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

    private void SelectWarehouse(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout = inflater.inflate(R.layout.modal_select_warehouse, null);
        builder.setView(layout);
        final AlertDialog alert = builder.create();
        ImageButton cancel = (ImageButton) layout.findViewById(R.id.imageButtonClose);
        Button accept = (Button) layout.findViewById(R.id.buttonAccept);
        final RadioButton alm1 = (RadioButton) layout.findViewById(R.id.radioButton_alm1);
        final RadioButton alm2 = (RadioButton) layout.findViewById(R.id.radioButton_alm2);
        final RadioButton alm3 = (RadioButton) layout.findViewById(R.id.radioButton_alm3);
        final RadioButton alm4 = (RadioButton) layout.findViewById(R.id.radioButton_alm4);

        cancel.setOnClickListener(
            new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.cancel();
                    }
                }
        );

        accept.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductsListFragment fragment = new ProductsListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("param1","");
                    bundle.putString("param2", "");
                    if(alm1.isChecked())
                        bundle.putString("param4", "1");
                    else if(alm2.isChecked())
                        bundle.putString("param4", "2");
                    else if(alm3.isChecked())
                        bundle.putString("param4", "3");
                    else if(alm4.isChecked())
                        bundle.putString("param4", "4");

                    fragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentReside, fragment)
                            .commit();
                    alert.cancel();
                }
            }
        );
        alert.show();
    }
}
