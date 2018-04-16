package com.henry.clientesnuevos;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    Spinner spinRif;
    private String optionSelectedRif = "";

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
        spinRif = (Spinner) view.findViewById(R.id.spinnerRif);
        final TextInputEditText etPhone = (TextInputEditText) view.findViewById(R.id.tbet_phone);
        final TextInputEditText etEmail = (TextInputEditText) view.findViewById(R.id.tbet_email);
        final TextInputEditText etAddress = (TextInputEditText) view.findViewById(R.id.tbet_address);
        final TextInputEditText etObservation = (TextInputEditText) view.findViewById(R.id.tbet_observation);
        Button btn_save = (Button) view.findViewById(R.id.buttonSave);

        Accounts.getGroupsbyEstate(spin, context, view);
        load_spinner_rif();
        btn_save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       if(!etRif.getText().toString().trim().equals("")){
                            CLN cln = new CLN();
                            String rif = optionSelectedRif + etRif.getText().toString().trim();
                            cln.setCLNRIF(rif);
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

    private void load_spinner_rif(){
        final String array_spinner[]=new String[3];
        array_spinner[0] = "V";
        array_spinner[1] = "J";
        array_spinner[2] = "E";
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, array_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinRif.setAdapter(adapter);
        spinRif.setSelection(1);
        spinRif.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                optionSelectedRif = array_spinner[arg2].toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }
}
