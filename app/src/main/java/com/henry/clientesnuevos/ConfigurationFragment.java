package com.henry.clientesnuevos;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import Model.Variables;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigurationFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;


    public ConfigurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_configuration, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment
        final Switch switch_url = (Switch) view.findViewById(R.id.switchUrl);
        final Switch switch_menu = (Switch) view.findViewById(R.id.switchMenu);

        if(Variables.getUrl().toString().equals(Variables.getUrl_local())) {
            switch_url.setText("CONEXIÓN:   Local");
            switch_url.setChecked(false);
        }else if(Variables.getUrl().toString().equals(Variables.getUrl_remote())) {
            switch_url.setText("CONEXIÓN:   Remoto");
            switch_url.setChecked(true);
        }

        if(Variables.getTypeMenu().toString().equals("classic")){
            switch_menu.setText("MENU:   Lateral");
            switch_menu.setChecked(false);
        }else if(Variables.getTypeMenu().toString().equals("modern")){
            switch_menu.setText("MENU:   Slide");
            switch_menu.setChecked(true);
        }

        switch_url.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                SharedPreferences sp = context.getSharedPreferences("profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit(); String msg;
                if(switch_url.isChecked()){
                    editor.putString("Conection", Variables.getUrl_remote());
                    Variables.setUrl(Variables.getUrl_remote()); msg = "REMOTO";
                    switch_url.setText("CONEXIÓN:   Remoto");
                }else{
                    editor.putString("Conection", Variables.getUrl_local());
                    Variables.setUrl(Variables.getUrl_local()); msg = "LOCAL";
                    switch_url.setText("CONEXIÓN:   Local");
                }
                editor.commit();
                Snackbar.make(view, "Conexión modificada a "+ msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            }
        );

        switch_menu.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = context.getSharedPreferences("profile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    if(switch_menu.isChecked()){
                        editor.putString("Menu", "modern");
                        Variables.setTypeMenu("modern");
                    }else{
                        editor.putString("Menu", "classic");
                        Variables.setTypeMenu("classic");
                    }
                    editor.commit();
                    goMain(switch_menu);
                }
            }
        );

        return view;
    }

    private void goMain(Switch switch_menu){
        Intent intent;
        if(switch_menu.isChecked()){
            intent = new Intent(context, MainResideActivity.class);
            startActivity(intent);
        }else{
            intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }
        getFragmentManager().beginTransaction().remove(this).commit();
    }

}
