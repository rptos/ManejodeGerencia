package Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.henryruiz.manejoalmacenmantis.R;

import java.util.List;

import Model.AUDI;

/**
 * Created by extre_000 on 23/2/2018.
 */

public class ConsignmentListFragmentAdapter extends ArrayAdapter {
    private Context context;
    private List<AUDI> items;
    private LayoutInflater mInflater;

    public ConsignmentListFragmentAdapter(Context context, List<AUDI> objects) {
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
        public final TextView textViewNumber;
        public final TextView textViewDate;
        public final CardView rootView;

        private ViewHolder(TextView textViewNumber, TextView textViewDate, CardView rootView) {
            this.textViewNumber = textViewNumber;
            this.textViewDate = textViewDate;
            this.rootView = rootView;
        }

        public static ViewHolder create(CardView rootView) {
            TextView textViewNumber = (TextView) rootView.findViewById(R.id.textViewNumber);
            TextView textViewDate = (TextView) rootView.findViewById(R.id.textViewDate);
            return new ViewHolder(textViewNumber, textViewDate, rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.audits_list, parent, false);
            vh = ViewHolder.create((CardView) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        AUDI item = items.get(position);

        vh.textViewNumber.setText(item.getV02NUMERO());
        vh.textViewDate.setText(item.getV02FECHA());
        return vh.rootView;
    }
}
