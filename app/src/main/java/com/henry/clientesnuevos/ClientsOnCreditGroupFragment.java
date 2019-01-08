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

import com.bumptech.glide.Glide;

import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientsOnCreditGroupFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;
    ImageView progressView;

    public ClientsOnCreditGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_clients_on_credit_group, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        final ListView list = (ListView) view.findViewById(R.id.listViewGroupByStates);
        // Inflate the layout for this fragment

        progressView = (ImageView) view.findViewById(R.id.progress2);
        Glide.with(this).load(R.drawable.logo_animado).into((ImageView) progressView);
        progressView.setVisibility(View.VISIBLE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
               Variables.setFragment("ClientListFragment");
                ClientListFragment fragment = new ClientListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("param1", String.valueOf(position));
                bundle.putString("param2", Variables.getGruPK());
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
