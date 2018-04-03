package com.henry.clientesnuevos;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import Connection.Accounts;
import Model.BitmapRe;
import Model.MED;
import Model.Variables;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateDetailDVIFragment extends Fragment {
    View view;
    Context context;
    static LayoutInflater inflater;
    private String encodedImage = "";

    private int year = 0;
    private int month = 0;
    private int day = 0;
    static final int DATE_PICKER_ID = 1111;
    TextInputEditText date;
    String id;
    String idPro;
    String msg;

    private static String CARPETA = "/sdcard/DCIM/Camera/";
    private String archivo = "foto.jpg";
    private String dir = "";
    private static int TAKE_PICTURE = 1;
    private static int SELECT_PICTURE = 2;
    private String name = "";
    String foto = "";

    @SuppressLint("ValidFragment")
    public CreateDetailDVIFragment(String pk, String proPk) {
        // Required empty public constructor
        id = pk;
        idPro = proPk;
    }

    public CreateDetailDVIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_create_detail_dvi, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment
        Button save = (Button) view.findViewById(R.id.buttonGuardar);
        Button volver = (Button) view.findViewById(R.id.volver);
        date = (TextInputEditText) view.findViewById(R.id.etDate);
        final TextInputEditText balance = (TextInputEditText) view.findViewById(R.id.etBalanceinBs);
        final TextInputEditText ref = (TextInputEditText) view.findViewById(R.id.etReference);
        final TextInputEditText obs = (TextInputEditText) view.findViewById(R.id.etObsevation);
        ImageView image = (ImageView) view.findViewById(R.id.pictureTaken);
        Calendar miCalendario = Calendar.getInstance();
        day = miCalendario.get(Calendar.DAY_OF_MONTH);
        month = miCalendario.get(Calendar.MONTH);
        year = miCalendario.get(Calendar.YEAR);
        date.setText(Variables.addZero(day)+"/"+Variables.addZero(month+1)+"/"+String.valueOf(year));
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                createDialogWithoutDateField().show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!date.getText().toString().equals("") && !balance.getText().toString().equals("") &&
                        !ref.getText().toString().equals("") && !obs.getText().toString().equals("")){
                    setDvi(balance.getText().toString().trim(), ref.getText().toString().trim(),obs.getText().toString().trim());
                    if (!Variables.getIdDetalleDVI().equals("")) {
                        name = CARPETA + archivo;
                        final Intent[] intent = {new Intent(MediaStore.ACTION_IMAGE_CAPTURE)};
                        final int[] code = {TAKE_PICTURE};

                        final CharSequence colors[] = new CharSequence[]{"Tomar Foto", "Seleccionar Foto de Galeria"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Tomar Foto");
                        builder.setItems(colors, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Uri output = Uri.fromFile(new File(name));
                                        intent[0].putExtra(MediaStore.EXTRA_OUTPUT, output);
                                        break;
                                    case 1:
                                        intent[0] = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                        code[0] = SELECT_PICTURE;
                                        break;
                                }
                                startActivityForResult(intent[0], code[0]);
                            }
                        });
                        builder.show();
                    }
                }else{
                AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                alertDialog.setTitle("Seleccione un Inventario");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
            }
        });
        if (!Variables.getIdDetalleDVI().equals("")) {
            Accounts.GetDVI(Variables.getIdDetalleDVI(), "true", balance, ref, obs, image,context,id, idPro, view);
            id
        }


        return view;
    }

    public void setID(String id, String idpro){

    }


    private void setDvi(String balance, String ref, String obs){
        MED med = new MED();
        med.setMED_CLIFK(idPro);
        med.setMED_MONTO(balance);
        med.setMED_REFERENCIA(ref);
        med.setMED_FACTURA(obs);
        med.setMED_FECHA(date.getText().toString().trim());
        med.setMED_DVIFK(id);
        if(!Variables.getIdDetalleDVI().equals(""))
            med.setMED_PK(Variables.getIdDetalleDVI());
        Accounts.setDVI(med, view);
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            year  = selectedYear;
            month = selectedMonth;
            day   = selectedDay;
            date.setText(Variables.addZero(day) + "/" + Variables.addZero((month + 1)) + "/" + String.valueOf(year));
            // Show selected date
            /*Output.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));*/

        }
    };

    private DatePickerDialog createDialogWithoutDateField(){

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), pickerListener, year, month,day);
        try{
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = new Object();
                            dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }

            }
        }catch(Exception ex){

        }
        return dpd;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = null;
        if (requestCode == TAKE_PICTURE) {
            if (data != null) {
                if (data.hasExtra("data")) {
                    Uri selectedImage = data.getData();
                    InputStream is;
                    try {
                        is = context.getContentResolver().openInputStream(selectedImage);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        bitmap = BitmapFactory.decodeStream(bis);
                    } catch (FileNotFoundException e) {
                    }
                    bitmap = ((Bitmap) data.getParcelableExtra("data"));
                }
            } else {
                new MediaScannerConnection.MediaScannerConnectionClient() {
                    private MediaScannerConnection msc = null;

                    {
                        msc = new MediaScannerConnection(context, this);
                        msc.connect();
                    }

                    public void onMediaScannerConnected() {
                        msc.scanFile(name, null);
                    }

                    public void onScanCompleted(String path, Uri uri) {
                        msc.disconnect();
                    }
                };
                File f = new File(name);
                //OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
                bitmap = BitmapRe.decodeFile(f, 1024, 768);
            }
        } else if (requestCode == SELECT_PICTURE) {
            Uri selectedImage = data.getData();

            String selectedImagePath = getPath(selectedImage);
            dir = selectedImagePath;

            InputStream is;
            try {
                is = context.getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
            } catch (FileNotFoundException e) {
            }
        }
        //transform the image into a string
        if (bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        }
        if (resultCode == -1)
        {
            //PENDIENTE
            MED med = new MED();
            med.setMED_FOTO(encodedImage);
            Accounts.doFileUploadDetailDVI(med,archivo, Variables.getIdDetalleDVI(), view);
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }
}
