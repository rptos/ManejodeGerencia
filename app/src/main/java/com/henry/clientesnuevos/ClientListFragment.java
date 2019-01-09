package com.henry.clientesnuevos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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

import Connection.Accounts;
import Model.CLI;
import Model.Search;
import Model.Variables;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Integer position;
    View view;
    Context context;
    static LayoutInflater inflater;
    ImageView progressView;
    private String pk = Variables.getGruPK();
    private String type;

    static LinearLayout linear1;
    static TextView tvToolBarSearch;

    static LinearLayout linear2;
    static TextView tvToolBarSearch1;
    static TextView tvToolBarSearchCode1;
    static EditText value;

    public ClientListFragment() {
        // Required empty public constructor
    }

    public static ClientListFragment newInstance(String param1, String param2) {
        ClientListFragment fragment = new ClientListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = Integer.valueOf(getArguments().getString(ARG_PARAM1));
            type = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_client_list, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        final ListView list = (ListView) view.findViewById(R.id.listViewClients);
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabSearch1);

        TextView module1 = (TextView) view.findViewById(R.id.textViewBrowse);
        TextView module2 = (TextView) view.findViewById(R.id.textViewBrowse1);
        linear1 = (LinearLayout) view.findViewById(R.id.Linear1);
        tvToolBarSearch = (TextView) view.findViewById(R.id.textViewToolBarSearch);

        linear2 = (LinearLayout) view.findViewById(R.id.Linear2);
        tvToolBarSearch1 = (TextView) view.findViewById(R.id.textViewToolBarSearch1);
        tvToolBarSearchCode1 = (TextView) view.findViewById(R.id.textViewToolBarSearchCode1);

        progressView = (ImageView) view.findViewById(R.id.progress3);
        Glide.with(this).load(R.drawable.logo_animado).into((ImageView) progressView);
        progressView.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        final Search search = new Search();
        search.setValor("");

        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.GONE);
        if(!type.equals("0")){
            tvToolBarSearch.setText(Accounts.listGroup.get(position).getGCLNOMBRE().toString().trim());
        }else{
            tvToolBarSearch.setText(R.string.TvToolBarListProducts_all);
        }

        if(type.equals("cpa")) {
            module1.setText("Ctas Pagadas");
            module2.setText("Ctas Pagadas");
            Accounts.getClientsCpa(pk,search, list, context, view, progressView);
            fab.setVisibility(GONE);
        }else if(type.equals("cxc") || type.equals("0")) {
            if(type.equals("0")){
                module1.setText("Clientes Credito");
                module2.setText("Clientes Credito");}
            Accounts.getClientsCxc(pk,search, list, context, view, progressView);
            //Accounts.getClientsCredit(list,context,view,progressView);
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogSearch(list, search);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                if(type.equals("cpa")){
                    Variables.setFragment("CPAFragment");
                    final CLI posActual = Accounts.listClient.get(position);
                    Variables.setCliPK(String.valueOf(posActual.getCLIPK()));
                    CPAFragment fragment = new CPAFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fragment);
                    transaction.commit();

                    /*Snackbar.make(view, "Detalle de CPA en Mantenimiento...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                }else if(type.equals("cxc")) {
                    Variables.setFragment("ClientDetailActivity");
                    final CLI posActual = Accounts.listClient.get(position);
                    Variables.setGruPK(String.valueOf(posActual.getCLIPK()));
                    Intent intent = new Intent(context, ClientDetailActivity.class);
                    intent.putExtra("position", String.valueOf(position));
                    startActivity(intent);
                }else if(type.equals("0")){
                    Variables.setFragment("CheckPriceListFragment");
                    CheckPriceListFragment fragment = new CheckPriceListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("param1", String.valueOf(position));
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


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
                    Accounts.getClientsCxc(pk,search, list, context, view, progressView);
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
                    tvToolBarSearch.setText(Accounts.listGroup.get(position).getGCLNOMBRE().toString().trim());
                }else if(!value.getText().toString().equals("")){
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.VISIBLE);
                    tvToolBarSearch1.setText(Accounts.listGroup.get(position).getGCLNOMBRE().toString().trim());
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
