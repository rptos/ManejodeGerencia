package Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.clientesnuevos.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import Model.INV;
import Model.Variables;

/**
 * Created by Henry on 26/1/2018.
 */

public class ProductsListFragmentAdapter extends ArrayAdapter {
    private Context context;
    private List<INV> items;
    private LayoutInflater mInflater;

    public ProductsListFragmentAdapter(Context context, List<INV> objects) {
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
    public Object getItem(int position) {
        Log.i("Posicion", position + " - ");return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        public final TextView textViewName;
        public final TextView textViewCode;
        public final TextView textViewExistence;
        public final TextView textViewPrice;
        public final LinearLayout rootView;
        public final ImageView image;

        private ViewHolder(TextView textViewName, TextView textViewCode, TextView textViewExistence, TextView textViewPrice, LinearLayout rootView, ImageView image) {
            this.textViewName = textViewName;
            this.textViewCode = textViewCode;
            this.textViewExistence = textViewExistence;
            this.textViewPrice = textViewPrice;
            this.rootView = rootView;
            this.image = image;
        }

        public static ViewHolder create(LinearLayout rootView) {
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewCode = (TextView) rootView.findViewById(R.id.textViewCode);
            TextView textViewExistence = (TextView) rootView.findViewById(R.id.textViewExistence);
            TextView textViewPrice = (TextView) rootView.findViewById(R.id.textViewPrice);
            ImageView image = (ImageView) rootView.findViewById(R.id.imageViewPhoto);
            return new ViewHolder(textViewName, textViewCode, textViewExistence, textViewPrice, rootView, image);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;

        DecimalFormatSymbols symbol=new DecimalFormatSymbols();
        symbol.setDecimalSeparator(',');
        symbol.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);

        if (convertView == null) {
            View view = mInflater.inflate(R.layout.product_list, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        INV item = items.get(position);

        vh.textViewName.setText(item.getINVNOMBRE());
        vh.textViewCode.setText("COD: "+ item.getINVCODIGO());
        vh.textViewExistence.setText("Existencia:  "+String.valueOf((int)Float.parseFloat(item.getINVEXISTENCIA())));
        vh.textViewPrice.setText("\rPRECIO 1:  "+ formatter.format(Float.parseFloat(item.getINVPRECIO1().replace(",","."))) +"\n"
                                +"\rPRECIO 3:  "+ formatter.format(Float.parseFloat(item.getINVPRECIO3().replace(",", "."))));
        Picasso.with(context).load(Variables.getDireccion_fotos() + item.getINVFOTO() + "&width=250").into(vh.image);
        return vh.rootView;
    }
}
