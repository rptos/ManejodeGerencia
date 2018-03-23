package com.henry.clientesnuevos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
