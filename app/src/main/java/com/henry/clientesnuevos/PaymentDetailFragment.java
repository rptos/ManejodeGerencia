package com.henry.clientesnuevos;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import Connection.Accounts;
import Model.MED;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentDetailFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;
    String id;
    String idPro;

    private Integer position;
    private ListView list;

    static LinearLayout linear1;
    static TextView tvToolBarSearch;

    static LinearLayout linear2;
    static TextView tvToolBarSearch1;
    static TextView tvToolBarSearchCode1;

    @SuppressLint("ValidFragment")
    public PaymentDetailFragment(String pk, String proPk, int pos) {
        // Required empty public constructor
        id = pk;
        idPro = proPk;
        position = pos;
    }

    public PaymentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_payment_detail, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment
        list = (ListView) view.findViewById(R.id.list);
        final FloatingActionsMenu floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.menu_fab);
        final FloatingActionButton fab_add = (FloatingActionButton) view.findViewById(R.id.accion_add);
        linear1 = (LinearLayout) view.findViewById(R.id.Linear1);
        tvToolBarSearch = (TextView) view.findViewById(R.id.textViewToolBarSearch);

        linear2 = (LinearLayout) view.findViewById(R.id.Linear2);
        tvToolBarSearch1 = (TextView) view.findViewById(R.id.textViewToolBarSearch1);
        tvToolBarSearchCode1 = (TextView) view.findViewById(R.id.textViewToolBarSearchCode1);
        linear1.setVisibility(View.GONE);
        linear2.setVisibility(View.VISIBLE);
        tvToolBarSearchCode1.setText(Accounts.listPRO.get(position).getPRONOMBRE().toString().trim());

        Accounts.Sync_DetailDVI(id,"false", list, context, view);

        fab_add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        floatingActionsMenu.collapse();
                        Variables.setFragment("CreateDetailDVIFragment");
                        Variables.setIdDetalleDVI("");
                        CreateDetailDVIFragment fragment2 = new CreateDetailDVIFragment(id, idPro, position);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentReside, fragment2);
                        transaction.addToBackStack(null);
                        transaction.commit();

                    }
                }
        );

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long id) {
                final MED posActual = Accounts.listMED.get(position);
                Variables.setIdDetalleDVI(posActual.getMED_PK());
                Variables.setFragment("CreateDetailDVIFragment");
                CreateDetailDVIFragment fragment2 = new CreateDetailDVIFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentReside, fragment2);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

}
