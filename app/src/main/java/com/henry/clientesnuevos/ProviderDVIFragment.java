package com.henry.clientesnuevos;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import Connection.Accounts;
import Model.CLI;
import Model.PRO;
import Model.Search;
import Model.Variables;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProviderDVIFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;
    ImageView progressView;
    private String pk = Variables.getGruPK();


    private ListView list;

    static LinearLayout linear1;
    static TextView tvToolBarSearch;

    static LinearLayout linear2;
    static TextView tvToolBarSearch1;
    static TextView tvToolBarSearchCode1;
    static EditText value;

    public ProviderDVIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_provider_dvi, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment

        list = (ListView) view.findViewById(R.id.list);
        final FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.menu_fab);
        final com.getbase.floatingactionbutton.FloatingActionButton fab_search = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.accion_browser3);
        final com.getbase.floatingactionbutton.FloatingActionButton fab_new = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.accion_newProvider);
        linear1 = (LinearLayout) view.findViewById(R.id.Linear1);
        tvToolBarSearch = (TextView) view.findViewById(R.id.textViewToolBarSearch);

        linear2 = (LinearLayout) view.findViewById(R.id.Linear2);
        tvToolBarSearch1 = (TextView) view.findViewById(R.id.textViewToolBarSearch1);
        tvToolBarSearchCode1 = (TextView) view.findViewById(R.id.textViewToolBarSearchCode1);

        progressView = (ImageView) view.findViewById(R.id.progress);
        Glide.with(this).load(R.drawable.logo_animado).into((ImageView) progressView);
        progressView.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.GONE);

        Accounts.sync_providers(list, context, view, progressView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long id) {
                final PRO posActual = Accounts.listPRO.get(position);
                final CharSequence colors[] = new CharSequence[]{"Editar", "Detalles de pago", "Enviar Correo"};

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setTitle("Proveedor " + posActual.getPRONOMBRE());
                builder.setItems(colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Variables.setFragment("CreateDVIActivity");
                                Variables.setIdDVI(posActual.getPRODVIPK());
                                Intent intent = new Intent(context, CreateDVIActivity.class);
                                intent.putExtra("state", "0");
                                startActivity(intent);
                                break;
                            case 1:
                                Variables.setFragment("PaymentDetailFragment");
                                PaymentDetailFragment fragment1 = new PaymentDetailFragment(posActual.getPRODVIPK(), String.valueOf(posActual.getPROPK()), Integer.valueOf(position));
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.frament, fragment1);
                                transaction.addToBackStack(null);
                                transaction.commit();
                                Variables.setiD(posActual.getPRODVIPK());
                                Variables.setidpro(posActual.getPROPK());
                                Variables.setPosition(String.valueOf(position));
                                break;
                            case 2:
                                Variables.setIdDVI(posActual.getPRODVIPK());
                                Accounts.sendMAIL(Variables.getIdDVI(), view);
                                break;
                        }
                    }
                });
                builder.show();
            }
        });

        fab_new.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Variables.setFragment("CreateDVIActivity");
                        Variables.setIdDVI("");
                        Intent intent = new Intent(context, CreateDVIActivity.class);
                        startActivity(intent);
                        floatingActionsMenu.collapse();
                    }
                }
        );



        return view;
    }
}
