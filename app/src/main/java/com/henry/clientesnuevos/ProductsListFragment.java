package com.henry.clientesnuevos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;

import Connection.ProductsList;
import Model.Variables;

import static android.view.View.GONE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsListFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    View view;
    Context context;
    ImageView progressView;
    String valueSearch = "";
    static LayoutInflater inflater;
    static EditText number;
    static String type = "";
    static String valueTemp = "";
    static String typeNumber = "";
    static String stringType = "";
    static float amount;

    static ToggleButton tbCot;
    static ToggleButton tbFact;
    static ToggleButton tbPed;
    static ToggleButton tbTra;
    static TextView tbType;

    static ToggleButton togglePrice1;
    static ToggleButton togglePrice3;
    static TextInputEditText percentage;
    static TextView textViewPrice;

    static LinearLayout linear1;

    static LinearLayout linear2;
    static TextView tvToolBarSearch1;
    static TextView tvToolBarSearchCode1;

    static LinearLayout linear3;
    static TextView tvToolBarSearch2;
    static TextView tvToolBarSearchCode2;
    static TextView tvToolBarSearchExt2;

    public ProductsListFragment() {
        // Required empty public constructor
    }

    public static ProductsListFragment newInstance(String param1, String param2, String param3) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getString(ARG_PARAM1);
            typeNumber = getArguments().getString(ARG_PARAM2);
            valueTemp = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView list = (ListView) view.findViewById(R.id.listViewProductsList);
        try {
            if (number==null){
                ProductsList.getPost(valueSearch, list, context, view, progressView);
            }
        }
        catch (Exception x){

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_products_list, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabSearch);
        final ListView list = (ListView) view.findViewById(R.id.listViewProductsList);

        linear1 = (LinearLayout) view.findViewById(R.id.Linear1);

        linear2 = (LinearLayout) view.findViewById(R.id.Linear2);
        tvToolBarSearch1 = (TextView) view.findViewById(R.id.textViewToolBarSearch1);
        tvToolBarSearchCode1 = (TextView) view.findViewById(R.id.textViewToolBarSearchCode1);

        linear3 = (LinearLayout)view.findViewById(R.id.Linear3);
        tvToolBarSearch2 = (TextView) view.findViewById(R.id.textViewToolBarSearch2);
        tvToolBarSearchCode2 = (TextView) view.findViewById(R.id.textViewToolBarSearchCode2);
        tvToolBarSearchExt2 = (TextView) view.findViewById(R.id.textViewToolBarSearchExt2);


        progressView = (ImageView) view.findViewById(R.id.progress);
        Glide.with(this).load(R.drawable.logo_animado).into((ImageView) progressView);
        progressView.setVisibility(View.VISIBLE);

        //condicion para cuando es compra
        if(type != "com") {
            if (type.equals("cons")) {
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.VISIBLE);
                linear3.setVisibility(View.GONE);
                tvToolBarSearch1.setText(getString(R.string.toggleButton_cot));
                tvToolBarSearchCode1.setText(typeNumber);
                stringType = getString(R.string.toggleButton_cot);
            }
            else {
                linear1.setVisibility(View.VISIBLE);
                linear2.setVisibility(View.GONE);
                linear3.setVisibility(View.GONE);
                stringType = "";
            }
        }else{
            if(!valueTemp.toString().equals("")){
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                linear3.setVisibility(View.VISIBLE);
                tvToolBarSearchExt2.setText(valueTemp);
            }else{
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.VISIBLE);
                linear3.setVisibility(View.GONE);
            }
            tvToolBarSearch1.setText(getString(R.string.typeSearch_com));
            tvToolBarSearchCode1.setText(typeNumber);
            stringType = typeNumber;
        }

        //condicion de type si esta vacio de otra manera traer el de la compra
        if(type=="")
            ProductsList.getPost("", list, context, view, progressView);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialogSearch(list);
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                Variables.setFragment("ShareProductActivity");
                Intent intent = new Intent(context, ShareProductActivity.class);
                intent.putExtra("position", String.valueOf(position));
                startActivity(intent);
            }
        });

        return  view;
    }

    /*private void shareProducts(final int pos){
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_SUBJECT, "Catalogo Movil de " + getResources().getString(R.string.company_name));
            i.putExtra(Intent.EXTRA_TEXT, "\n" + ProductsList.listProducts.get(pos).getINVNOMBRE() +
                    "\n" + "COD: "+ ProductsList.listProducts.get(pos).getINVCODIGO() +
                    "\n" + "PRECIO:  " +amount + "\n");
            i.putExtra(Intent.EXTRA_STREAM, Variables.getDireccion_fotos() + ProductsList.listProducts.get(pos).getINVFOTO());
            i.setType("text/plain");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(i, "Compartir en"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    private boolean selectPercentage(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout = inflater.inflate(R.layout.modal_share_product, null);
        builder.setView(layout);
        final boolean[] bool = {false};
        final AlertDialog alert = builder.create();
        percentage = (TextInputEditText) layout.findViewById(R.id.TextInputEditTextPercentage);
        togglePrice1 = (ToggleButton) layout.findViewById(R.id.toggleButtonPrice1);
        togglePrice3 = (ToggleButton) layout.findViewById(R.id.toggleButtonPrice3);
        ImageButton buttonRecalculate = (ImageButton) layout.findViewById(R.id.imageButtonAct);
        textViewPrice = (TextView) layout.findViewById(R.id.textViewPrice);
        Button accept = (Button) layout.findViewById(R.id.buttonAccept);
        ImageButton cancel = (ImageButton) layout.findViewById(R.id.imageButtonClose);

        togglePrice1.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!ProductsList.listProducts.get(position).getINVPRECIO1().equals("")){
                        setTextType("price1", ProductsList.listProducts.get(position).getINVPRECIO1());
                    }else{
                        Snackbar.make(v, "error: PRECIO 1 con valor NULL", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        );
        togglePrice3.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!ProductsList.listProducts.get(position).getINVPRECIO3().equals("")){
                        setTextType("price3", ProductsList.listProducts.get(position).getINVPRECIO3());
                    }else {
                        Snackbar.make(v, "error: PRECIO 3 con valor NULL", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        );

        accept.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!togglePrice1.isChecked() && !togglePrice3.isChecked()){
                        Snackbar.make(v, "Seleccione tipo de Precio", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        bool[0] = false;                    }else{
                        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                        alert.cancel();
                        bool[0] = true;
                    }
                }
            }
        );
        buttonRecalculate.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!togglePrice1.isChecked() && !togglePrice3.isChecked()){
                        Snackbar.make(v, "Seleccione tipo de Precio", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else{
                        float price = 0;
                        float Percentage = Float.parseFloat(percentage.getText().toString().trim());;
                        if(togglePrice1.isChecked() && !togglePrice3.isChecked()){
                            price = Float.valueOf(ProductsList.listProducts.get(position).getINVPRECIO1());
                        }else if(!togglePrice1.isChecked() && togglePrice3.isChecked()){
                            price = Float.valueOf(ProductsList.listProducts.get(position).getINVPRECIO3());
                        }
                        amount = price * Percentage;
                        textViewPrice.setText(String.valueOf(amount));
                    }
                    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                    alert.cancel();
                }
            }
        );
        cancel.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(percentage.getWindowToken(), 0);
                    alert.cancel();
                }
            }
        );
        alert.show();
        if(bool[0])
            return true;
        else
            return false;
    }*/

    private void createDialogSearch(final ListView list) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View layout = inflater.inflate(R.layout.modal_search, null);
        builder.setView(layout);
        final AlertDialog alert = builder.create();
        final EditText value = (EditText) layout.findViewById(R.id.editTextSearch);
        TextView textView = (TextView) layout.findViewById(R.id.textView4);
        number = (EditText) layout.findViewById(R.id.editTextNumber);
        ProgressBar pm = (ProgressBar) layout.findViewById(R.id.progressBar);
        LinearLayout CotPedFacTra = (LinearLayout) layout.findViewById(R.id.LinearLayoutCotPedFacTra);

        Button search = (Button) layout.findViewById(R.id.buttonSearch);
        ImageButton cancel = (ImageButton) layout.findViewById(R.id.imageButtonClose);
        tbCot = (ToggleButton) layout.findViewById(R.id.toggleButtonCot);
        tbFact = (ToggleButton) layout.findViewById(R.id.toggleButtonFact);
        tbPed = (ToggleButton) layout.findViewById(R.id.toggleButtonPed);
        tbTra = (ToggleButton) layout.findViewById(R.id.toggleButtonTra);
        tbType = (TextView) layout.findViewById(R.id.textViewType);

        CotPedFacTra.setVisibility(GONE);
        textView.setVisibility(GONE);
        number.setVisibility(GONE);
        if(typeNumber!="")
        {
            number.setText(typeNumber);
        }

        search.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    valueSearch = value.getText().toString().trim();
                    if ((!value.getText().toString().trim().equals("") && number.getText().toString().equals("")) ||
                            !number.getText().toString().equals("")) {
                        if (number.getText().toString().equals("")) {
                            typeNumber = "";
                            ProductsList.getPost(value.getText().toString().trim(), list, context, v, progressView);
                            stringType = value.getText().toString().trim();
                            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(number.getWindowToken(), 0);
                        }
                        else {
                            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.hideSoftInputFromWindow(number.getWindowToken(), 0);
                        }

                        if(number.getText().toString().equals("")){
                            linear1.setVisibility(View.GONE);
                            linear2.setVisibility(View.VISIBLE);
                            linear3.setVisibility(View.GONE);
                            tvToolBarSearch1.setText(R.string.TvToolBarListProducts_all);
                            tvToolBarSearchCode1.setText(value.getText().toString().trim());
                        }else if(!number.getText().toString().equals("") && !value.getText().toString().equals("")){
                            linear1.setVisibility(View.GONE);
                            linear2.setVisibility(View.GONE);
                            linear3.setVisibility(View.VISIBLE);
                            tvToolBarSearch2.setText(stringType);
                            tvToolBarSearchCode2.setText(typeNumber);
                            tvToolBarSearchExt2.setText(value.getText().toString().trim());
                        }else if(!number.getText().toString().equals("") && value.getText().toString().equals("")){
                            linear1.setVisibility(View.GONE);
                            linear2.setVisibility(View.VISIBLE);
                            linear3.setVisibility(View.GONE);
                            tvToolBarSearch1.setText(stringType);
                            tvToolBarSearchCode1.setText(typeNumber);
                        }

                        alert.cancel();
                    }
                    else{
                        Snackbar.make(v, "Ingrese un nombre o codigo de producto", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        );
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressView.setVisibility(View.GONE);
                        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(number.getWindowToken(), 0);
                        alert.cancel();
                    }
                }
        );

        alert.show();
    }

    /*void setTextType(String t, String price) {
        float Percentage = Integer.parseInt(percentage.getText().toString().trim());
        amount = Float.parseFloat(price) * Percentage;
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
        textViewPrice.setText(String.valueOf(amount));
    }*/
}
