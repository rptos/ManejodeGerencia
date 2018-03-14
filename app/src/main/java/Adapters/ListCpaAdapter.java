package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henryruiz.manejoalmacenmantis.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.CPA;
import Model.Variables;

/**
 * Created by Henry on 6/3/2018.
 */

public class ListCpaAdapter extends ArrayAdapter {
    private Context context;
    private List<CPA> items;
    private LayoutInflater mInflater;

    public ListCpaAdapter(Context context, List<CPA> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.items = objects;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {return this.items.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder
    {
        TextView Menssage;
        TextView Date;
        TextView Balance;
        ImageView Photo;
        public final LinearLayout rootView;
        private ViewHolder(TextView Menssage, TextView Date,TextView Balance, ImageView Photo, LinearLayout rootView) {
            this.Menssage = Menssage;
            this.Date = Date;
            this.Balance = Balance;
            this.Photo = Photo;
            this.rootView = rootView;
        }

        public static ListCpaAdapter.ViewHolder create(LinearLayout rootView) {
            TextView Menssage = (TextView) rootView.findViewById(R.id.textViewMsg);
            TextView Date = (TextView) rootView.findViewById(R.id.textViewFecha);
            TextView Balance = (TextView) rootView.findViewById(R.id.textViewSaldo);
            ImageView Photo = (ImageView) rootView.findViewById(R.id.imageViewFoto);
            return new ListCpaAdapter.ViewHolder(Menssage, Date,Balance, Photo,rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_cpa, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        CPA item = items.get(position);
        vh.Date.setText("Fecha: " + item.getCPAFECHA().toString());
        vh.Balance.setText("Monto Pagado: " + item.getCPAMONTO());
        vh.Menssage.setText(item.getCPAMENSAJE());
        Picasso.with(context).load(Variables.getDireccion_fotos() + item.getCPAFOTO() + "&width=250").into(vh.Photo);
        return vh.rootView;
    }

}
