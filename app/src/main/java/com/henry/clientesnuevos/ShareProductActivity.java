package com.henry.clientesnuevos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import Connection.ProductsList;
import Model.Variables;

public class ShareProductActivity extends AppCompatActivity {

    Context context;
    static LayoutInflater inflater;

    ToggleButton togglePrice1;
    ToggleButton togglePrice3;
    TextInputEditText percentage;
    TextView textViewPrice;
    TextView textViewPriceDollar;
    ImageView imageView;
    CheckBox checkBoxPrice;
    CheckBox checkBoxPriceDollar;
    float amountInBolivar;
    float amonutInDollar;
    Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_product);
        Bundle extras = getIntent().getExtras();
        context = (Context) this;
        this.inflater = LayoutInflater.from(context);

        SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
        if (settings.getString("USR_PK", null) != null) {
            Variables.setId(settings.getString("USR_PK", ""));
            Variables.setLanid(settings.getString("USR_LANID", ""));
            Variables.setUrl(settings.getString("Conection", ""));
        }

        if(extras != null){
            position = Integer.parseInt(extras.getString("position"));

            percentage = (TextInputEditText) findViewById(R.id.TextInputEditTextPercentage);
            togglePrice1 = (ToggleButton) findViewById(R.id.toggleButtonPrice1);
            togglePrice3 = (ToggleButton) findViewById(R.id.toggleButtonPrice3);
            ImageButton buttonRecalculate = (ImageButton) findViewById(R.id.imageButtonAct);
            textViewPrice = (TextView) findViewById(R.id.textViewPrice);
            textViewPriceDollar = (TextView) findViewById(R.id.textViewPriceDollar);
            TextView textViewName = (TextView) findViewById(R.id.textViewName);
            imageView = (ImageView) findViewById(R.id.imageView);
            checkBoxPrice = (CheckBox) findViewById(R.id.checkBoxPrice);
            checkBoxPriceDollar = (CheckBox) findViewById(R.id.checkBoxPriceDollar);
            FloatingActionButton accept = (FloatingActionButton) findViewById(R.id.fabAccept);
            Picasso.with(context).load(Variables.getDireccion_fotos() + ProductsList.listProducts.get(position).getINVFOTO() + "&width=250").into(imageView);
            textViewName.setText(ProductsList.listProducts.get(position).getINVNOMBRE().toString().trim());
            setTextType("price6", String.valueOf(ProductsList.listProducts.get(position).getINVPRECIO6().replace(",",".")));

            togglePrice1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if(ProductsList.listProducts.get(position).getINVPRECIO1().equals("")
                                || ProductsList.listProducts.get(position).getINVPRECIO1().equals(null)){
                            Snackbar.make(v, "error: PRECIO 1 con valor NULL", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else*/ if(percentage.getText().toString().trim().equals("")){
                            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                            Snackbar.make(v, "Ingrese Valor de Porcentaje", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            setTextType("price1", String.valueOf(ProductsList.listProducts.get(position).getINVPRECIO1().replace(",",".")));
                            checkBoxPrice.setChecked(true);
                        }
                    }
                }
            );
            togglePrice3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if(ProductsList.listProducts.get(position).getINVPRECIO3().equals("")
                                || ProductsList.listProducts.get(position).getINVPRECIO3().equals(null)){
                            Snackbar.make(v, "error: PRECIO 3 con valor NULL", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else*/ if(percentage.getText().toString().trim().equals("")){
                            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                            Snackbar.make(v, "Ingrese Valor de Porcentaje", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else {
                            setTextType("price3", String.valueOf(ProductsList.listProducts.get(position).getINVPRECIO3().replace(",",".")));
                            checkBoxPrice.setChecked(true);
                        }
                    }
                }
            );

            accept.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!checkBoxPrice.isChecked() && !checkBoxPriceDollar.isChecked()){
                        //if(!togglePrice1.isChecked() && !togglePrice3.isChecked()){
                            Snackbar.make(v, "SELECCIONE MONTO", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            shareProducts();
                        }
                    }
                }
            );
            buttonRecalculate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*if(!togglePrice1.isChecked() && !togglePrice3.isChecked()) {
                            Snackbar.make(v, "Seleccione tipo de Precio", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else*/ if(percentage.getText().toString().trim().equals("")){
                            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                            Snackbar.make(v, "Ingrese Valor de Porcentaje", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            float price = 0;
                            DecimalFormatSymbols symbol=new DecimalFormatSymbols();
                            symbol.setDecimalSeparator(',');
                            symbol.setGroupingSeparator('.');
                            DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);
                            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                            float Percentage = Float.parseFloat(percentage.getText().toString().trim()) / 100;
                            if(togglePrice1.isChecked() && !togglePrice3.isChecked()){
                                price = Float.valueOf(ProductsList.listProducts.get(position).getINVPRECIO1().replace(",","."));
                            }else if(!togglePrice1.isChecked() && togglePrice3.isChecked()){
                                price = Float.valueOf(ProductsList.listProducts.get(position).getINVPRECIO3().replace(",","."));
                            }
                            amountInBolivar = price + (price * Percentage);
                            textViewPrice.setText(String.valueOf(formatter.format(amountInBolivar)) + " Bs.S");
                        }
                    }
                }
            );
        }
    }

    private void shareProducts(){
        //imageView.buildDrawingCache();
        //Bitmap bitmap = imageView.getDrawingCache();
        String TextSend = "";
        try {
            DecimalFormatSymbols symbol=new DecimalFormatSymbols();
            symbol.setDecimalSeparator(',');
            symbol.setGroupingSeparator('.');
            DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);

            /*File file = new File(getCacheDir(), bitmap + ".png");
            FileOutputStream fOut = null;
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);*/

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(Intent.EXTRA_SUBJECT, "Manejo de Gerencia de " + getResources().getString(R.string.company_name));
            /*i.putExtra(Intent.EXTRA_TEXT, "\n" + ProductsList.listProducts.get(position).getINVNOMBRE() +
                    "\n\n" + "COD: "+ ProductsList.listProducts.get(position).getINVCODIGO() +
                    "\n" + "PRECIO:  " +formatter.format(amountInBolivar) + " Bs.S\n\n");*/

            TextSend += "\n" + ProductsList.listProducts.get(position).getINVNOMBRE();
            TextSend += "\n\n" + "COD: "+ ProductsList.listProducts.get(position).getINVCODIGO();
            if(checkBoxPriceDollar.isChecked())
                TextSend += "\n" + "PRECIO:  " +formatter.format(amonutInDollar) + " $";
            if(checkBoxPrice.isChecked())
                TextSend += "\n" + "PRECIO:  " +formatter.format(amountInBolivar) + " Bs.S\n\n";

            i.putExtra(Intent.EXTRA_TEXT, TextSend);
            //i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            i.setType("text/plain");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(i, "Compartir en"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    private void comparteView(View viewShare) {
        // Creamos un bitmap con el tamaño de la vista
        Bitmap bitmap = Bitmap.createBitmap(viewShare.getWidth(),
                viewShare.getHeight(), Bitmap.Config.ARGB_8888);
        // Creamos el canvas para pintar en el bitmap
        Canvas canvas = new Canvas(bitmap);
        // Pintamos el contenido de la vista en el canvas y así en el bitmap
        viewShare.draw(canvas);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        Uri uriF = null;
        try {
            File f = File.createTempFile("sharedImage", ".jpg",
                    getExternalCacheDir());
            f.deleteOnExit();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(stream.toByteArray());
            fo.close();

            uriF = Uri.fromFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("image/jpeg");

        sharingIntent.putExtra(Intent.EXTRA_STREAM, uriF);
        startActivity(sharingIntent);

    }

    void setTextType(String t, String price) {
        DecimalFormatSymbols symbol=new DecimalFormatSymbols();
        symbol.setDecimalSeparator(',');
        symbol.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
        float Percentage = Float.parseFloat(price) * (Float.parseFloat(percentage.getText().toString().trim()) / 100);
        if(t.equals("price1") || t.equals("price3")) {
            amountInBolivar = Float.parseFloat(price) + Percentage;
        }else if(t.equals("price6")){
            amonutInDollar = Float.parseFloat(price) + Percentage;
        }
        switch (t) {
            case "price1":
                togglePrice1.setChecked(true);
                togglePrice3.setChecked(false);
                break;
            case "price3":
                togglePrice3.setChecked(true);
                togglePrice1.setChecked(false);
                break;
        }
        if(t.equals("price1") || t.equals("price3")) {
            textViewPrice.setText(String.valueOf(formatter.format(amountInBolivar)) + " Bs.S");
        }else if(t.equals("price6")){
            textViewPriceDollar.setText(String.valueOf(formatter.format(amonutInDollar)) + " $");
        }
    }
}
