package com.henry.clientesnuevos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import Connection.Accounts;
import Model.Variables;

public class CreateDVIActivity extends AppCompatActivity {
    Context context;
    static LayoutInflater inflater;
    private View view1;
    private String pk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dvi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Spinner spinProvider = (Spinner) findViewById(R.id.spinnerProvider);
        android.support.design.widget.TextInputEditText balance_dollar = (android.support.design.widget.TextInputEditText) findViewById(R.id.etBalanceD);
        android.support.design.widget.TextInputEditText balance_bolivar = (android.support.design.widget.TextInputEditText) findViewById(R.id.etBalanceB);
        ImageView image = (ImageView) findViewById(R.id.image_taken);
        
        setSupportActionBar(toolbar);

        context = (Context) this;
        this.inflater = LayoutInflater.from(context);

        SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
        if (settings.getString("USR_PK", null) != null) {
            Variables.setId(settings.getString("USR_PK", ""));
            Variables.setLanid(settings.getString("USR_LANID", ""));
            Variables.setUrl(settings.getString("Conection", ""));
        }

        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.Blank_Tittle);
        Accounts.sync_dvi(balance_bolivar, balance_dollar, Variables.getIdDVI().toString(), image, context, view1);
        Accounts.sync_proAll(spinProvider, context, view1);
        //Picasso.with(context).load(Variables.getDireccion_fotos() + "dvi/" + Accounts.listDVI.get(0).getDVIFOTO() + "&width=250").into(image);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1=view;
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
