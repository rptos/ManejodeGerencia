package Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.henryruiz.manejoalmacenmantis.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import Model.PHOTO;
import Model.Variables;

/**
 * Created by Henry on 7/2/2018.
 */

public class TakePhotoFragmentAdapter extends ArrayAdapter {

    private Context context;
    private List<PHOTO> items;
    private LayoutInflater mInflater;

    public TakePhotoFragmentAdapter(Context context, List<PHOTO> objects) {
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
        public final LinearLayout rootView;
        public final ImageView image;

        private ViewHolder(LinearLayout rootView, ImageView image) {
            this.rootView = rootView;
            this.image = image;
        }

        public static TakePhotoFragmentAdapter.ViewHolder create(LinearLayout rootView) {
            ImageView image = (ImageView) rootView.findViewById(R.id.imgMenu);
            return new TakePhotoFragmentAdapter.ViewHolder(rootView, image);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final TakePhotoFragmentAdapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.photos, parent, false);
            vh = TakePhotoFragmentAdapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (TakePhotoFragmentAdapter.ViewHolder) convertView.getTag();
        }
        PHOTO item = items.get(position);
        Log.i("photo","image " + Variables.getDireccion_fotos() + item.getIMPFOTO() + "&width=250");
        Picasso.with(context).load(Variables.getDireccion_fotos() + item.getIMPFOTO() + "&width=250")
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE).into(vh.image);
        return vh.rootView;
    }
}
