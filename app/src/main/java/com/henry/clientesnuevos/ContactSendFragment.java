package com.henry.clientesnuevos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactSendFragment extends Fragment {

    private View view;
    private Context context;
    private static LayoutInflater inflater;
    private String sAux = "\nPermiteme Recomendarte lo/s Siguientes Contactos:\n";

    private String ped = "\n\n PEDIDOS\n"+
            "+58 0424-9064235\n"+
            "pedidos@rptos.com";

    private String client = "\n\n ATENCION AL CLIENTE\n"+
            "+58 0424-9105612\n"+
            "atencionalcliente@rptos.com";

    private String gerent = "\n\n GERENCIA\n"+
            "+58 0424-9005029\n"+
            "smith@rptos.com";

    private String admin = "\n\n ADMINISTRACION\n"+
            "+58 0414-8546014\n"+
            "administracion@rptos.com";

    public ContactSendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_contact_send, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);

        CardView cardView_ped = (CardView) view.findViewById(R.id.CardViewPed);
        CardView cardView_client = (CardView) view.findViewById(R.id.CardViewClient);
        CardView cardView_gerent = (CardView) view.findViewById(R.id.CardViewGerent);
        CardView cardView_admin = (CardView) view.findViewById(R.id.CardViewAdmin);
        final CheckBox checkBox_ped = (CheckBox) view.findViewById(R.id.checkBoxPed);
        final CheckBox checkBox_client = (CheckBox) view.findViewById(R.id.checkBoxClient);
        final CheckBox checkBox_gerent = (CheckBox) view.findViewById(R.id.checkBoxGerent);
        final CheckBox checkBox_admin = (CheckBox) view.findViewById(R.id.checkBoxAdmin);
        FloatingActionButton fab_sent = (FloatingActionButton) view.findViewById(R.id.fabSent2);

        cardView_ped.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBox_ped.isChecked())
                    checkBox_ped.setChecked(true);
                else
                    checkBox_ped.setChecked(false);
            }
        });

        cardView_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBox_client.isChecked())
                    checkBox_client.setChecked(true);
                else
                    checkBox_client.setChecked(false);
            }
        });

        cardView_gerent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBox_gerent.isChecked())
                    checkBox_gerent.setChecked(true);
                else
                    checkBox_gerent.setChecked(false);
            }
        });

        cardView_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBox_admin.isChecked())
                    checkBox_admin.setChecked(true);
                else
                    checkBox_admin.setChecked(false);
            }
        });

        fab_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBox_ped.isChecked()
                        && !checkBox_client.isChecked()
                        && !checkBox_gerent.isChecked()
                        && !checkBox_admin.isChecked()){
                    Snackbar.make(view, "Seleccione Un Contacto", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{

                    if(checkBox_ped.isChecked()) sAux += ped;
                    if(checkBox_client.isChecked()) sAux += client;
                    if (checkBox_gerent.isChecked()) sAux += gerent;
                    if (checkBox_admin.isChecked()) sAux += admin;
                    shareAccounts();
                }
            }
        });

        return view;
    }

    private void shareAccounts(){
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Manejo de Gerencia de " + getResources().getString(R.string.company_name));
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Compartir en"));
            sAux="\nPermiteme Recomendarte lo/s Siguientes Contactos:\n";
        } catch(Exception e) {
            //e.toString();
        }
    }
}
