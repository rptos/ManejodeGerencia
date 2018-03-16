package com.henry.clientesnuevos;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import Connection.Accounts;
import Model.CPA;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class CPAFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;

    private ListView list;

    public CPAFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_cpa, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment

        list = (ListView) view.findViewById(R.id.ListCPA);
        Accounts.Sync_CPA(Variables.getCliPK(),list,context, view);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                final CPA posActual = Accounts.listCPA.get(position);
                final CharSequence colors[] = new CharSequence[] {"Enviar por correo", "Eliminar cuenta pagada"};

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Cuenta pagada del dia " + posActual.getCPAFECHA());
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Accounts.SentCPA(posActual.getCPAPK(), view);
                                break;
                            case 1:
                                Accounts.DeleteCPA(posActual.getCPAPK(), view);
                                Accounts.Sync_CPA(Variables.getCliPK(),list,context, view);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

        return view;
    }

}
