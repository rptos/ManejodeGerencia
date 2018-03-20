package com.henry.clientesnuevos;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import Connection.Accounts;
import Model.CLN;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterClientFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;

    public RegisterClientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_register_client, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        final TextInputEditText etRif = (TextInputEditText) view.findViewById(R.id.tbet_rif);
        final TextInputEditText etName = (TextInputEditText) view.findViewById(R.id.tbet_name);
        Spinner spin = (Spinner) view.findViewById(R.id.spinner_zone);
        final TextInputEditText etPhone = (TextInputEditText) view.findViewById(R.id.tbet_phone);
        final TextInputEditText etEmail = (TextInputEditText) view.findViewById(R.id.tbet_email);
        final TextInputEditText etAddress = (TextInputEditText) view.findViewById(R.id.tbet_address);
        final TextInputEditText etObservation = (TextInputEditText) view.findViewById(R.id.tbet_observation);
        Button btn_save = (Button) view.findViewById(R.id.buttonSave);

        Accounts.getGroupsbyEstate(spin, context, view);

        btn_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if(!etRif.getText().toString().trim().equals("")){
                            CLN cln = new CLN();
                            cln.setCLNRIF(etRif.getText().toString().trim());
                            cln.setCLNNOMBRE(etName.getText().toString().trim());
                            cln.setCLNTEL(etPhone.getText().toString().trim());
                            cln.setCLNDIR(etAddress.getText().toString().trim());
                            cln.setCLNOBS(etObservation.getText().toString().trim());
                            cln.setCLNEMAIL(etEmail.getText().toString().trim());
                            cln.setCLNGCLFK(Variables.getGruPK());
                            Accounts.SaveClient(cln, v);
                       }else{
                           Snackbar.make(view, "Ingrese al menos el rif del cliente", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
                       }
                    }
                }
        );

        return view;
    }

}
