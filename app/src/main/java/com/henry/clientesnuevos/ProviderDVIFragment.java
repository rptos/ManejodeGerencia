package com.henry.clientesnuevos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import Connection.Accounts;
import Model.Search;
import Model.Variables;


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
        final Search search = new Search();
        search.setValor("");
        Accounts.sync_providers(list, context, view, progressView);

        return view;
    }

}
