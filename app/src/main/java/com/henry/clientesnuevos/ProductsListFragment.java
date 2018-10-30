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
    private static final String ARG_PARAM4 = "param4";

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
    String Warehouse= "";

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
    static TextView tvToolBarSearch;

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

    public static ProductsListFragment newInstance(String param1, String param2, String param3, String param4) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
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
            Warehouse = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ListView list = (ListView) view.findViewById(R.id.listViewProductsList);
        try {
            if (number==null){
                ProductsList.getPost(valueSearch, Warehouse, list, context, view, progressView);
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
        tvToolBarSearch = (TextView) view.findViewById(R.id.textViewToolBarSearch);

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
            ProductsList.getPost("", Warehouse.trim(), list, context, view, progressView);
        setTextType(Warehouse.toString());


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
                            ProductsList.getPost(value.getText().toString().trim(), Warehouse, list, context, v, progressView);
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
                            //tvToolBarSearch1.setText(R.string.TvToolBarListProducts_all);
                            tvToolBarSearchCode1.setText(value.getText().toString().trim());
                        }else if(!number.getText().toString().equals("") && !value.getText().toString().equals("")){
                            linear1.setVisibility(View.GONE);
                            linear2.setVisibility(View.GONE);
                            linear3.setVisibility(View.VISIBLE);
                            //tvToolBarSearch2.setText(stringType);
                            tvToolBarSearchCode2.setText(typeNumber);
                            tvToolBarSearchExt2.setText(value.getText().toString().trim());
                        }else if(!number.getText().toString().equals("") && value.getText().toString().equals("")){
                            linear1.setVisibility(View.GONE);
                            linear2.setVisibility(View.VISIBLE);
                            linear3.setVisibility(View.GONE);
                            //tvToolBarSearch1.setText(stringType);
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

    void setTextType(String opction) {
        switch (opction){
            case "1":
                tvToolBarSearch.setText(R.string.main_warehouse);
                tvToolBarSearch1.setText(R.string.main_warehouse);
                tvToolBarSearch2.setText(R.string.main_warehouse);
                break;
            case "2":
                tvToolBarSearch.setText(R.string.other_warerhouse);
                tvToolBarSearch1.setText(R.string.other_warerhouse);
                tvToolBarSearch2.setText(R.string.other_warerhouse);
                break;
            case "3":
                tvToolBarSearch.setText(R.string.senior_warehouse);
                tvToolBarSearch1.setText(R.string.senior_warehouse);
                tvToolBarSearch2.setText(R.string.senior_warehouse);
                break;
            case "4":
                tvToolBarSearch.setText(R.string.retail_warehouse);
                tvToolBarSearch1.setText(R.string.retail_warehouse);
                tvToolBarSearch2.setText(R.string.retail_warehouse);
                break;
        }

    }
}
