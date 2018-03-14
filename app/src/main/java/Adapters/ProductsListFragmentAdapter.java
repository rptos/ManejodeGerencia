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
        public final LinearLayout rootView;
        public final ImageView image;

        private ViewHolder(TextView textViewName, TextView textViewCode, TextView textViewExistence, LinearLayout rootView, ImageView image) {
            this.textViewName = textViewName;
            this.textViewCode = textViewCode;
            this.textViewExistence = textViewExistence;
            this.rootView = rootView;
            this.image = image;
        }

        public static ViewHolder create(LinearLayout rootView) {
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewName);
            TextView textViewCode = (TextView) rootView.findViewById(R.id.textViewCode);
            TextView textViewExistence = (TextView) rootView.findViewById(R.id.textViewExistence);
            ImageView image = (ImageView) rootView.findViewById(R.id.imageViewPhoto);
            return new ViewHolder(textViewName, textViewCode, textViewExistence, rootView, image);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.product_list, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        INV item = items.get(position);

        vh.textViewName.setText(item.getINVNOMBRE());
        vh.textViewCode.setText(item.getINVCODIGO());
        vh.textViewExistence.setText("");
        Picasso.with(context).load(Variables.getDireccion_fotos() + item.getINVFOTO() + "&width=250").into(vh.image);
        return vh.rootView;
    }
}
