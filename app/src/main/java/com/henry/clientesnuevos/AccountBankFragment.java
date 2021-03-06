package com.henry.clientesnuevos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountBankFragment extends Fragment {

    private View view;
    private Context context;
    private static LayoutInflater inflater;
    private String sAux = "\nPermiteme Recomendarte la/s Cuenta Bancaria:\n";

    String Mercantil_Factura = "\n\n Banco MERCANTIL: solo depósitos y transferencias\n" +
            "Tipo de cuenta: Corriente\n" +
            "RIF: J-31103080-8\n" +
            "De: Repuestos Coreanos Smith Rodríguez C.A.\n" +
            "N° 0105-0075-31-1075322812\n" +
            "Correo: administracion@rptos.com\n" +
            "Teléfono: 0414-8546014";

    String BNC_Factura  =  "\n\n Banco BNC: solo depósitos y transferencias\n" +
            "Tipo de cuenta: Corriente\n" +
            "RIF: J-31103080-8\n" +
            "De: Repuestos Coreanos Smith Rodríguez C.A.\n" +
            "N° 0191-0118-52-2100009489\n" +
            "Correo: administracion@rptos.com\n" +
            "Teléfono: 0414-8546014";

    String Exterior_Factura  = "\n\n Banco EXTERIOR: solo depósitos y transferencias\n" +
            "Tipo de cuenta: Corriente\n" +
            "RIF: J-31103080-8\n" +
            "De: Repuestos Coreanos Smith Rodríguez C.A.\n" +
            "N° 0115-0070-69-1001711009\n" +
            "Correo: administracion@rptos.com\n" +
            "Teléfono: 0414-8546014";
    /** NOTA DE ENTREGA **/
    String Mercantil_Nota   =  "\n\n Banco MERCANTIL: solo depósitos y transferencias\n" +
            "Tipo de cuenta: Corriente\n" +
            "C.I: 12.360.365 \n" +
            "De: Smith Rodríguez\n" +
            "N° 0105-0075-30-1075322987\n" +
            "Correo: Repuestoscoreanos85@gmail.com\n" +
            "Teléfono: 0424-9734593 / 0414-9064235";

    String Wells_Fargo   =  "\n\n Banco: WELLS FARGO\n" +
            "De: Rafael Barrios\n" +
            "Correo: repuestoscoreanos85@gmail.com\n";
            //"Correo: rbarrios@drgroupltd.com\n";

    public AccountBankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_account_bank, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);

        final CheckBox checkBoxMercantilJ = (CheckBox) view.findViewById(R.id.checkBoxMercantilFactura);
        final CheckBox checkBoxBncJ = (CheckBox) view.findViewById(R.id.checkBoxBncFactura);
        final CheckBox checkBoxExteriorJ = (CheckBox) view.findViewById(R.id.checkBoxExteriorFactura);
        final CheckBox checkBoxMercantilP = (CheckBox) view.findViewById(R.id.checkBoxMercantilNota);
        final CheckBox checkBoxWF = (CheckBox) view.findViewById(R.id.checkBoxWellsFargo);
        FloatingActionButton fab_sent = (FloatingActionButton) view.findViewById(R.id.fabSent);

        fab_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBoxBncJ.isChecked()
                        && !checkBoxMercantilJ.isChecked()
                        && !checkBoxExteriorJ.isChecked()
                        && !checkBoxMercantilP.isChecked()
                        && !checkBoxWF.isChecked()){
                    Snackbar.make(view, "Seleccione Una Cuenta", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{

                    if(checkBoxBncJ.isChecked()) sAux += BNC_Factura;
                    if(checkBoxExteriorJ.isChecked()) sAux += Exterior_Factura;
                    if (checkBoxMercantilJ.isChecked()) sAux += Mercantil_Factura;
                    if (checkBoxMercantilP.isChecked()) sAux += Mercantil_Nota;
                    if (checkBoxWF.isChecked()) sAux += Wells_Fargo;
                    shareBank();
                }
            }
        });

        return view;
    }

    private void shareBank(){
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, "Manejo de Gerencia de " + getResources().getString(R.string.company_name));
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            startActivity(Intent.createChooser(i, "Compartir en"));
            sAux="\nPermiteme Recomendarte la/s Cuenta Bancaria:\n";
        } catch(Exception e) {
            //e.toString();
        }
    }

}
