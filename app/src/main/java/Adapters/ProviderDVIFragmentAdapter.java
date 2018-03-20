package Adapters;

import android.content.Context;
import android.text.Html;
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
import Model.PRO;
import Model.Variables;

/**
 * Created by Henry on 20/3/2018.
 */

public class ProviderDVIFragmentAdapter extends ArrayAdapter {
    private Context context;
    private List<PRO> items;
    private LayoutInflater mInflater;

    public ProviderDVIFragmentAdapter(Context context, List<PRO> objects) {
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

    private static class ViewHolder {
        public final TextView textViewName;
        public final TextView textViewCode;
        public final LinearLayout rootView;
        private ViewHolder(TextView textViewName, TextView textViewCode, LinearLayout rootView) {
            this.textViewName = textViewName;
            this.textViewCode = textViewCode;
            this.rootView = rootView;
        }

        public static ViewHolder create(LinearLayout rootView) {
            TextView textViewName = (TextView) rootView.findViewById(R.id.textViewNombre);
            TextView textViewCode = (TextView) rootView.findViewById(R.id.textViewCodigo);
            return new ViewHolder(textViewName, textViewCode, rootView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_group_pro, parent, false);
            vh = ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        PRO item = items.get(position);

        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator(',');
        simbolo.setGroupingSeparator('.');
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
        String totals = "<p>" + item.getPROCODIGO() + " - Total Deuda " + formateador.format(Float.parseFloat(item.getPROSALDOB().replace(",",".")))
                + " Bs  " + formateador.format(Float.parseFloat(item.getPROSALDOD().replace(",","."))) + " $ " + " <br/>Pagado " + formateador.format(Float.parseFloat(item.getPROPAGADO().replace(",",".")))
                + " <br/><strong>Falta " + formateador.format((Float.parseFloat(item.getPROSALDOD().replace(",",".")) * Float.parseFloat(item.getPROSALDOB().replace(",","."))) -
                Float.parseFloat(item.getPROPAGADO().replace(",","."))) + "</strong></p>";

        vh.textViewName.setText(item.getPRONOMBRE());
        if (!Variables.getGruPK().equals("0"))
            vh.textViewCode.setText(Html.fromHtml(totals));
        else
            vh.textViewCode.setText(item.getPROCODIGO());
        return vh.rootView;
    }
}
