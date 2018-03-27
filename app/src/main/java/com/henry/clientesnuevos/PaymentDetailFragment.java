package com.henry.clientesnuevos;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentDetailFragment extends Fragment {

    View view;
    Context context;
    static LayoutInflater inflater;


    public PaymentDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_payment_detail, container, false);
        context = (Context) getActivity();
        this.inflater = LayoutInflater.from(context);
        // Inflate the layout for this fragment


        return view;
    }

}
