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

import Model.CLI;

/**
 * Created by Henry on 26/2/2018.
 */

public class ClientListFragmentAdapter extends ArrayAdapter {
    private Context context;
    private List<CLI> items;
    private LayoutInflater mInflater;

    public ClientListFragmentAdapter(Context context, List<CLI> objects) {
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
            TextView textViewName = (TextView) rootView.findViewById(R.id.tVName);
            TextView textViewCode = (TextView) rootView.findViewById(R.id.tVCode);
            return new ViewHolder(textViewName, textViewCode,rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        CLI item = items.get(position);
        DecimalFormatSymbols symbol=new DecimalFormatSymbols();
        symbol.setDecimalSeparator(',');
        symbol.setGroupingSeparator('.');
        DecimalFormat formatter = new DecimalFormat("###,###.##",symbol);

        if (convertView == null) {
            View view = mInflater.inflate(R.layout.client_group, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.title.setText(item.getCLINOMBRE());
        if(item.getCLISALDO()!=null)
            vh.code.setText(item.getCLICODIGO() + "-" + formatter.format(Float.parseFloat(item.getCLISALDO().replace(",","."))));
        else
            vh.code.setText(item.getCLICODIGO());

        return vh.rootView;
    }

}
