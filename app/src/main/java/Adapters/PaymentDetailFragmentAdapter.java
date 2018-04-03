package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.clientesnuevos.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import Model.MED;

/**
 * Created by Henry on 2/4/2018.
 */

public class PaymentDetailFragmentAdapter extends ArrayAdapter {
    private Context context;
    private List<MED> items;
    private LayoutInflater mInflater;

    public PaymentDetailFragmentAdapter(Context context, List<MED> objects) {
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
        TextView title;
        TextView code;
        public final LinearLayout rootView;
        private ViewHolder(TextView textViewName, TextView textViewCode, LinearLayout rootView) {
            this.title = textViewName;
            this.code = textViewCode;
            this.rootView = rootView;
        }

        public static ViewHolder create(LinearLayout rootView) {
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewNombre);
            TextView textViewCode = (TextView) rootView.findViewById(R.id.textViewCodigo);
            return new ViewHolder(textViewName, textViewCode,rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        MED item = items.get(position);
        DecimalFormatSymbols symbol=new DecimalFormatSymbols();
        symbol.setDecimalSeparator(',');
        symbol.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);

        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_grupo_cli, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.title.setText(item.getMED_FACTURA());
        vh.code.setText("Monto " + formatter.format(Float.parseFloat(item.getMED_MONTO().replace(",","."))) + " - Fecha " +  item.getMED_FECHA());

        return vh.rootView;
    }

}
