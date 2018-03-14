package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.clientesnuevos.R;

import java.util.List;

import Model.CXC;

/**
 * Created by Henry on 27/2/2018.
 */

public class ClientDetailActivityAdapter extends ArrayAdapter {
    private Context context;
    private List<CXC> items;
    private LayoutInflater mInflater;

    public ClientDetailActivityAdapter(Context context, List<CXC> objects) {
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
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder
    {
        TextView nro;
        TextView fecha;
        TextView vence;
        TextView saldo;
        private CheckBox select;

        public CheckBox getSelect() {return select;}

        public void setSelect(CheckBox select) {
            this.select = select;
        }

        public final LinearLayout rootView;
        private ViewHolder(TextView textViewNro,TextView textViewDate ,TextView textViewVence,TextView textViewSaldo, LinearLayout rootView) {
            this.nro = textViewNro;
            this.fecha = textViewDate;
            this.vence = textViewVence;
            this.saldo = textViewSaldo;
            this.rootView = rootView;
        }

        public static ViewHolder create(LinearLayout rootView) {
            TextView textViewNro = (TextView) rootView.findViewById(R.id.textViewNro);
            TextView textViewDate = (TextView) rootView.findViewById(R.id.textViewFecha);
            TextView textViewVence = (TextView) rootView.findViewById(R.id.textViewVence);
            TextView textViewSaldo = (TextView) rootView.findViewById(R.id.textViewSaldo);
            return new ViewHolder(textViewNro, textViewDate ,textViewVence, textViewSaldo, rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_cxc, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        CXC item = items.get(position);
        vh.nro.setText(item.getCXCFACTURA().trim());
        vh.fecha.setText(item.getCXCFECHA().trim());
        vh.vence.setText(item.getCXCVENCE().trim());
        vh.saldo.setText(item.getCXCSALDO().trim());

        return vh.rootView;
    }
}
