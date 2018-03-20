package com.henry.clientesnuevos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import Model.Search;
import Model.Variables;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewClientsFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;
    ImageView progressView;

    private Integer position;
    private String pk = Variables.getGruPK();


    private ListView list;

    static LinearLayout linear1;
    static TextView tvToolBarSearch;

    static LinearLayout linear2;
    static TextView tvToolBarSearch1;
    static TextView tvToolBarSearchCode1;
    static EditText value;

    public NewClientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_new_clients, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment

        list = (ListView) view.findViewById(R.id.list);
        final FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.menu_fab);
        final com.getbase.floatingactionbutton.FloatingActionButton fab_search = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.accion_browser2);
        final com.getbase.floatingactionbutton.FloatingActionButton fab_new = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.accion_newClient);
        linear1 = (LinearLayout) view.findViewById(R.id.Linear1);
        tvToolBarSearch = (TextView) view.findViewById(R.id.textViewToolBarSearch);

        linear2 = (LinearLayout) view.findViewById(R.id.Linear2);
        tvToolBarSearch1 = (TextView) view.findViewById(R.id.textViewToolBarSearch1);
        tvToolBarSearchCode1 = (TextView) view.findViewById(R.id.textViewToolBarSearchCode1);

        progressView = (ImageView) view.findViewById(R.id.progress4);
        Glide.with(this).load(R.drawable.logo_animado).into((ImageView) progressView);
        progressView.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.GONE);
        final Search search = new Search();
        search.setValor("");
        Accounts.Sync_ListNewClients(pk, search, list,context,view, progressView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Variables.setFragment("CheckPriceListFragment");
                final CLI posActual = Accounts.listClient.get(position);
                Variables.setCliPK(String.valueOf(posActual.getCLIPK()));
                Variables.setEmailCliN(posActual.getCLIEMAIL().toString().trim());
                CheckPriceListFragment fragment = new CheckPriceListFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frament, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        fab_search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        floatingActionsMenu.collapse();
                        createDialogSearch(list, search);
                    }
                }
        );

        fab_new.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Variables.setFragment("RegisterClientFragment");
                        RegisterClientFragment fragment = new RegisterClientFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.frament, fragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        floatingActionsMenu.collapse();
                    }
                }
        );
        return view;
    }

    public void createDialogSearch(final ListView list, final Search search) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout = inflater.inflate(R.layout.modal_search, null);
        builder.setView(layout);
        final AlertDialog alert = builder.create();
        value = (EditText) layout.findViewById(R.id.editTextSearch);
        final EditText number = (EditText) layout.findViewById(R.id.editTextNumber);
        LinearLayout CotPedFacTra = (LinearLayout) layout.findViewById(R.id.LinearLayoutCotPedFacTra);
        LinearLayout searchType = (LinearLayout) layout.findViewById(R.id.linearLayout_typeOfSearch);

        final Button Btnsearch = (Button) layout.findViewById(R.id.buttonSearch);
        ImageButton cancel = (ImageButton) layout.findViewById(R.id.imageButtonClose);

        CotPedFacTra.setVisibility(GONE);
        searchType.setVisibility(GONE);
        number.setVisibility(GONE);

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(value.getWindowToken(), 0);

        Btnsearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!value.getText().toString().trim().equals("")) {
                            search.setValor(value.getText().toString().trim());
                            Accounts.Sync_ListNewClients(pk ,search, list, context,view, progressView);
                            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(value.getWindowToken(), 0);
                            alert.cancel();
                        }else{
                            Snackbar.make(v, "Ingrese un nombre", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                        if(value.getText().toString().equals("")){
                            linear1.setVisibility(View.VISIBLE);
                            linear2.setVisibility(View.GONE);
                            //tvToolBarSearch.setText(Accounts.listGroup.get(position).getGCLNOMBRE().toString().trim());
                        }else if(!value.getText().toString().equals("")){
                            linear1.setVisibility(View.GONE);
                            linear2.setVisibility(View.VISIBLE);
                            //tvToolBarSearch1.setText(Accounts.listGroup.get(position).getGCLNOMBRE().toString().trim());
                            tvToolBarSearchCode1.setText(value.getText().toString().trim());
                        }
                    }
                }
        );
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(number.getWindowToken(), 0);
                        alert.cancel();
                    }
                }
        );
        alert.show();
    }

}
