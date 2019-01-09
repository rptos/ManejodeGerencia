package com.henry.clientesnuevos;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;

import java.util.Calendar;
import java.util.Date;

import Connection.connection;
import Model.IMPORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImportWebFragment extends Fragment {

    private View view;
    private Context context;
    private static LayoutInflater inflater;
    String dayI = "", monthI = "", anioI = "";
    String dayF = "", monthF = "", anioF = "";

    public ImportWebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_import_web, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);

        final TextInputEditText Et_date_initial = (TextInputEditText) view.findViewById(R.id.et_date_initial);
        final TextInputEditText Et_date_final = (TextInputEditText) view.findViewById(R.id.et_date_final);
        final TextInputEditText Et_nro_order = (TextInputEditText) view.findViewById(R.id.et_nro_order);
        final TextInputEditText Et_id_user = (TextInputEditText) view.findViewById(R.id.et_id_user);
        ImageButton calendar01 = (ImageButton) view.findViewById(R.id.ImageButtonCalendar01);
        ImageButton calendar02 = (ImageButton) view.findViewById(R.id.ImageButtonCalendar02);
        Button btnImport = (Button) view.findViewById(R.id.btn_import);
        Button btnExport = (Button) view.findViewById(R.id.btn_export);

        Calendar calendar = Calendar.getInstance();
        dayI = dayF = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        monthI = monthF = String.valueOf(AddZero(calendar.get(Calendar.MONTH)+1));
        anioI = anioF = String.valueOf(calendar.get(Calendar.YEAR));

        Et_date_initial.setText(dayI+"/"+ monthI +"/"+anioI);
        Et_date_final.setText(dayF+"/"+ monthF +"/"+anioF);

        calendar01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogSearch("01", Et_date_initial, Et_date_final);
            }
        });

        calendar02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogSearch("02", Et_date_initial, Et_date_final);
            }
        });

        btnImport.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IMPORT Import = new IMPORT();
                        String dateInitial = dayI+"-"+monthI+"-"+anioI;
                        String dateFinal = dayF+"-"+monthF+"-"+anioF;
                        Import.setDateI(dateInitial);
                        Import.setDateF(dateFinal);
                        if(Et_nro_order.getText().toString()!="") {
                            Import.setOrder(Et_nro_order.getText().toString().trim());
                            Import.setId("");
                        }else if(Et_id_user.getText().toString()!="") {
                            Import.setId(Et_id_user.getText().toString().trim());
                            Import.setOrder("");
                        }
                        connection.ImportWeb(Import, v);
                        Snackbar.make(v, "Importando...", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }
        );


        btnExport.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        connection.ExportWeb(v);
                        Snackbar.make(v, "Exportando...", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }
        );

        return view;
    }

    public void createDialogSearch(final String type_calendar, final TextInputEditText et_date_initial, final TextInputEditText et_date_final) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout = inflater.inflate(R.layout.modal_calendar, null);
        builder.setView(layout);
        final AlertDialog alert = builder.create();
        ImageButton close = (ImageButton) layout.findViewById(R.id.imageButtonClose);
        Button btnAccept = (Button) layout.findViewById(R.id.buttonAccept);
        final DatePicker Date = (DatePicker) layout.findViewById(R.id.datePicker);

        Date.setMaxDate(new Date().getTime());

        btnAccept.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type_calendar != "01" && (Integer.valueOf(anioI) > Date.getYear()
                            || Integer.valueOf(monthI) > (Date.getMonth()+1)
                            || Integer.valueOf(monthI) >= (Date.getMonth()+1)
                            && Integer.valueOf(dayI) > Date.getDayOfMonth())) {
                        Snackbar.make(v, "Fecha Seleccionada No Valida", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else{
                        if(type_calendar == "01"){
                            dayI = String.valueOf(Date.getDayOfMonth());
                            monthI = String.valueOf(AddZero(Date.getMonth()+1));
                            anioI = String.valueOf(Date.getYear());
                            et_date_initial.setText(dayI+"/"+ monthI +"/"+anioI);
                        }else if(type_calendar == "02"){
                            dayF = String.valueOf(Date.getDayOfMonth());
                            monthF = String.valueOf(AddZero(Date.getMonth()+1));
                            anioF = String.valueOf(Date.getYear());
                            et_date_final.setText(dayF+"/"+ monthF +"/"+anioF);
                        }
                        alert.cancel();
                    }
                }
            }
        );
        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.cancel();
                    }
                }
        );

        alert.show();
    }

    private static String AddZero(int value){
        if(value<10){
            return "0"+value;
        }
        else {
            return String.valueOf(value);
        }
    }
}
