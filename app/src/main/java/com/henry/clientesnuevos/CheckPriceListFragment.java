package com.henry.clientesnuevos;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import Connection.Accounts;
import Model.Search;
import Model.SentEmail;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class CheckPriceListFragment extends Fragment {

    private View view;
    private Context context;
    private static LayoutInflater inflater;

    public CheckPriceListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_check_price_list, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        final TextInputEditText percentage = (TextInputEditText) view.findViewById(R.id.etPercentage);
        final TextInputEditText etEemail = (TextInputEditText) view.findViewById(R.id.etEmail);
        final TextInputEditText Class = (TextInputEditText) view.findViewById(R.id.etClase);
        final TextInputEditText etSearch = (TextInputEditText) view.findViewById(R.id.etSearch);
        Spinner spinMark = (Spinner) view.findViewById(R.id.spinnerMark);
        final ListView list = (ListView) view.findViewById(R.id.List);
        final FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.menu_fab);
        final FloatingActionButton fab_search = (FloatingActionButton) view.findViewById(R.id.accion_browser1);
        final FloatingActionButton fab_send = (FloatingActionButton) view.findViewById(R.id.accion_sent);
        // Inflate the layout for this fragment
        Accounts.Sync_Group(spinMark, context, view);

        fab_search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        floatingActionsMenu.collapse();
                        Search search = new Search();
                        search.setValor(etSearch.getText().toString().trim());
                        search.setUser(Variables.getId());
                        search.setClase(Class.getText().toString().trim());
                        Accounts.Search_INV_group(search, Variables.getGruPK(), list, context, view);
                    }
                }
        );

        fab_send.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        floatingActionsMenu.collapse();
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Tomar Foto");
                        final CharSequence colors[] = new CharSequence[] {"Enviar Productos", "Enviar mas vendidos"};
                        builder.setItems(colors, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Variables.setMasVendido(false);
                                        break;
                                    case 1:
                                        Variables.setMasVendido(true);
                                        break;
                                }
                            }
                        });
                        builder.show();

                        SentEmail email = new SentEmail();
                        email.setValor(etSearch.getText().toString().trim());
                        email.setPocentaje(percentage.getText().toString().trim());
                        email.setCliente(etEemail.getText().toString().trim());
                        email.setClase(Class.getText().toString().trim());
                        email.setAsunto("");
                        email.setMsg("");
                        email.setUser(Variables.getId());
                        if(Variables.getMasVendido())
                            email.setMasVendido("True");
                        Accounts.Sent_Email(email, Variables.getGruPK(), v);
                    }
                }
        );



        return view;
    }

}
