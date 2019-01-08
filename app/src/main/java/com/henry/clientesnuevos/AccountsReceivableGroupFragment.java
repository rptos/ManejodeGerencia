package com.henry.clientesnuevos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import Connection.Accounts;
import Model.GCL;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountsReceivableGroupFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";

    View view;
    Context context;
    ImageView progressView;
    static LayoutInflater inflater;
    private String type;

    public AccountsReceivableGroupFragment() {
        // Required empty public constructor
    }

    public static AccountsReceivableGroupFragment newInstance(String param1) {
        AccountsReceivableGroupFragment fragment = new AccountsReceivableGroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_PARAM1);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_accounts_receivable_group, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        final ListView list = (ListView) view.findViewById(R.id.listViewGroupByStates);
        TextView module = (TextView) view.findViewById(R.id.textViewBrowse);
        progressView = (ImageView) view.findViewById(R.id.progress2);
        Glide.with(this).load(R.drawable.logo_animado).into((ImageView) progressView);
        progressView.setVisibility(View.VISIBLE);
        // Inflate the layout for this fragment
        if(type.equals("cpa")) {
            module.setText("Ctas Pagadas");
            Accounts.getGroupsCpa(list, context, view, progressView);
        }else if(type.equals("cxc")) {
            Accounts.getGroupsCxc(list, context, view, progressView);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                    Variables.setFragment("ClientListFragment");
                    final GCL posActual = Accounts.listGroup.get(position);
                    Variables.setGruPK(String.valueOf(posActual.getGCLPK()));
                    Variables.sePositionGru(String.valueOf(position));
                    ClientListFragment fragment = new ClientListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("param1", String.valueOf(position));
                    bundle.putString("param2", type);
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentReside, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();


            }
        });
        return view;
    }

}
