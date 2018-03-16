package com.henry.clientesnuevos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import Connection.Accounts;
import Model.BitmapRe;
import Model.CPA;
import Model.CXC;
import Model.Variables;

public class ClientDetailActivity extends AppCompatActivity {
    Context context;
    Integer position;
    static LayoutInflater inflater;
    TextView textViewName;
    TextView textViewCI;
    EditText editTextMensage;
    EditText editTextMonto;
    private View view1;

    private static String CARPETA = "/sdcard/DCIM/Camera/";
    private String dir = "";
    private String archivo = "foto.jpg";
    private String name = "";
    private static int TAKE_PICTURE = 1;
    private static int SELECT_PICTURE = 2;
    private String pagado = "";
    private double monto = 0;
    Uri selectedImage;
    private String encodedImage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DecimalFormatSymbols symbol=new DecimalFormatSymbols();
        symbol.setDecimalSeparator(',');
        symbol.setGroupingSeparator('.');
        final DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);

        Bundle extras = getIntent().getExtras();
        context = (Context) this;
        this.inflater = LayoutInflater.from(context);

        SharedPreferences settings = getSharedPreferences("profile", MODE_PRIVATE);
        if (settings.getString("USR_PK", null) != null) {
            Variables.setId(settings.getString("USR_PK", ""));
            Variables.setLanid(settings.getString("USR_LANID", ""));
        }

        if (extras != null) {
            position = Integer.parseInt(extras.getString("position"));
            final ListView list = (ListView) findViewById(R.id.listViewFac);
            textViewName = (TextView) findViewById(R.id.textViewName);
            textViewCI = (TextView) findViewById(R.id.textViewCI);
            editTextMensage = (EditText) findViewById(R.id.editTextMensage);
            editTextMonto = (EditText) findViewById(R.id.editTextMonto);
            android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout = (android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            setSupportActionBar(toolbar);

            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(editTextMensage.getWindowToken(), 0);

            textViewName.setText(Accounts.listClient.get(position).getCLINOMBRE().toString().trim());
            textViewCI.setText(Accounts.listClient.get(position).getCLICODIGO());

            toolbar.setTitle(Accounts.listClient.get(position).getCLINOMBRE().toString().trim());
            collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.Blank_Tittle);

            Accounts.getAccountsReceivable(Accounts.listClient.get(position).getCLIPK().toString().trim(), list, context, view1);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                final CXC posActual = Accounts.listCXC.get(position);
                String str1 = String.valueOf(editTextMensage.getText());
                int wantedPosition = position; // Whatever position you're looking for
                int firstPosition = list.getFirstVisiblePosition() - list.getHeaderViewsCount(); // This is the same as child #0
                int wantedChild = wantedPosition - firstPosition;
                if (str1.toUpperCase().contains(Accounts.listCXC.get(position).getCXCFACTURA().toUpperCase())){
                    editTextMensage.setText(str1.replace("\r\n Factura Nro. " + Accounts.listCXC.get(position).getCXCFACTURA().toUpperCase() + " Monto " + Accounts.listCXC.get(position).getCXCSALDO().toUpperCase(),""));
                    list.getChildAt(wantedChild).setBackgroundColor(Color.WHITE);
                    pagado.replace(Accounts.listCXC.get(position).getCXCFACTURA().toUpperCase() + " ","");
                    monto = monto - Double.valueOf(Accounts.listCXC.get(position).getCXCSALDO().toUpperCase().replace(",","."));
                    if (monto<0){
                        monto = 0;
                    }
                    editTextMonto.setText(String.valueOf(formatter.format(monto)));
                }
                else {
                    editTextMensage.setText(str1 + "\r\n Factura Nro. " + Accounts.listCXC.get(position).getCXCFACTURA().toUpperCase() + " Monto " + Accounts.listCXC.get(position).getCXCSALDO().toUpperCase());
                    pagado += Accounts.listCXC.get(position).getCXCFACTURA().toUpperCase() + " ";
                    list.getChildAt(wantedChild).setBackgroundColor(Color.GRAY);
                    monto += Double.valueOf(Accounts.listCXC.get(position).getCXCSALDO().toUpperCase().replace(",","."));
                    editTextMonto.setText(String.valueOf(formatter.format(monto)));
                }
            }
        });



        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                view1 = view;
                name = CARPETA + archivo;
                final Intent[] intent = {new Intent(MediaStore.ACTION_IMAGE_CAPTURE)};
                final int[] code = {TAKE_PICTURE};
                final CharSequence colors[] = new CharSequence[] {"Tomar Foto", "Seleccionar Foto de Galeria", "Sin Foto", "Enviar cuentas por cobrar", "Lista de precios"};
                final boolean[] band = {true};
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
                                intent[0] = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                code[0] = SELECT_PICTURE;
                                break;
                            case 2:
                                SendPayment(Accounts.listClient.get(position).getCLIPK().toString(), editTextMensage.getText().toString(), editTextMonto.getText().toString(), "No", view);
                                band[0] = false;
                                break;
                            case 3:
                                Accounts.sent_AccountsPayment(Accounts.listCXC.get(position).getCXCCLIPK().toString().trim(), view);
                                //new EnviarCuentasxCobrar().execute(Variables.getCliPk().toString());
                                band[0] = false;
                                break;
                            case 4:
                                /*CheckPriceListFragment fragment = new CheckPriceListFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.frament, fragment)
                                        .commit();*/

                                /*ConsultaListaPrecios fragment2 = new ConsultaListaPrecios();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(com.henryruiz.manejoalmacenmantis.R.id.content_frame, fragment2);
                                fragmentTransaction.commit();*/

                                Snackbar.make(view, "Opcion Consultada en Mantenimiento...", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                band[0] = false;
                                break;
                        }
                    }
                });
                builder.show();
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
            InputStream is;
            try {
                is = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                bitmap = BitmapFactory.decodeStream(bis);
            } catch (FileNotFoundException e) {
            }
        }
        if (bitmap != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        }
    }

    private void SendPayment(String PK,String msg, String balance,String string,View view){
        CPA cpa = new CPA();
        cpa.setCPACLIFK(PK.toString().trim());
        cpa.setCPAMENSAJE(msg.toString().trim());
        cpa.setCPAMONTO(balance.toString().trim());
        Accounts.sent_payment(cpa,view);
        String selectedImagePath = getPath(selectedImage);

        if (selectedImagePath!=null)
            Accounts.sent_CPA_photo(selectedImagePath, view);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Accounts.listClient.get(position).getCLIEMAIL().toString().trim(), "administracion@rptoscoreanos.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Comprobante de pago");
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg + "\r\nTotal Pagado: " + balance);
        if (string=="Si")
            emailIntent.putExtra(Intent.EXTRA_STREAM, selectedImage);
        startActivity(Intent.createChooser(emailIntent, "Enviar correo..."));
    }

    private String getPath(Uri uri) {
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
