package com.henry.clientesnuevos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import Connection.Accounts;
import Model.BitmapRe;
import Model.DVI;
import Model.Variables;

public class CreateDVIActivity extends AppCompatActivity {
    Context context;
    static LayoutInflater inflater;
    private View view1;
    private String pk;
    private int pro= Variables.get_pro_dvi();

    private static String FOLDER = "/sdcard/DCIM/Camera/";
    private String archive = "foto.jpg";
    private String dir = "";
    private static int TAKE_PICTURE = 1;
    private static int SELECT_PICTURE = 2;
    private String name = "";
    private String encodedImage = "";

    Spinner spinProvider;
    Uri selectedImage;
    android.support.design.widget.TextInputEditText balance_dollar;
    android.support.design.widget.TextInputEditText balance_bolivar;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dvi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        this.inflater = LayoutInflater.from(context);

        android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        spinProvider = (Spinner) findViewById(R.id.spinnerProvider);
        balance_dollar = (android.support.design.widget.TextInputEditText) findViewById(R.id.etBalanceD);
        balance_bolivar = (android.support.design.widget.TextInputEditText) findViewById(R.id.etBalanceB);
        image = (ImageView) findViewById(R.id.image_taken);
        view1 = findViewById(android.R.id.content);

        //Accounts.sync_proAll(view1);
        //Accounts.sync_dvi(balance_bolivar,balance_dollar,spinProvider,image,Variables.getIdDVI(),context, view1);
        Accounts.sync_pro_Dvi(balance_bolivar,balance_dollar,spinProvider,image,Variables.getIdDVI(),context, view1);//         AQUI ES LO QUE NO LOGRO QUE CARGUE EL SPINNER  t____t

        SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
        if (settings.getString("USR_PK", null) != null) {
            Variables.setId(settings.getString("USR_PK", ""));
            Variables.setLanid(settings.getString("USR_LANID", ""));
            Variables.setUrl(settings.getString("Conection", ""));
        }
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.Blank_Tittle);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(Variables.get_pro_dvi()!=-1 && !balance_bolivar.getText().toString().trim().equals("") && !balance_dollar.getText().toString().trim().equals("")){
                DVI dvi = new DVI();
                dvi.setDVIPROFK(String.valueOf(Variables.get_pro_dvi()));
                dvi.setDVIMONTOD(balance_dollar.getText().toString().trim());
                dvi.setDVIMONTOB(balance_bolivar.getText().toString().trim());
                if(!Variables.getIdDVI().equals(""))
                    dvi.setDVIPK(Variables.getIdDVI());
                Accounts.save_DVI(dvi, view);

                if(!Variables.getIdDVI().equals("")){
                    name = FOLDER + archive;

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
                        is = getContentResolver().openInputStream(selectedImage);
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
                is = getContentResolver().openInputStream(selectedImage);
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
        if(requestCode == -1)
        {

            //PENDIENTE-----------------------------------------------------------------------------------------------------------------------------
            DVI dvi = new DVI();
            dvi.setDVIFOTO(encodedImage);
            Accounts.doFileUploadDVI(dvi, archive, Variables.getIdDVI(), view1);
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
