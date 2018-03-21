package com.henry.clientesnuevos;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import Model.Variables;


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
        if(Variables.getUrl().toString().equals(Variables.getUrl_local())) switch_url.setChecked(false);
        else if(Variables.getUrl().toString().equals(Variables.getUrl_remote())) switch_url.setChecked(true);

        switch_url.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                SharedPreferences sp = context.getSharedPreferences("profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit(); String msg;
                if(switch_url.isChecked()){
                    editor.putString("Conection", Variables.getUrl_remote());
                    Variables.setUrl(Variables.getUrl_remote()); msg = "REMOTO";
                }else{
                    editor.putString("Conection", Variables.getUrl_local());
                    Variables.setUrl(Variables.getUrl_local()); msg = "LOCAL";
                }
                editor.commit();
                Snackbar.make(view, "Conexión modificada a "+ msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
            }
        );


        return view;
    }


}
