package aljentelhosting.ksaksafactory.data;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;

/**
 * Created by shirya on 15/09/17.
 */

public class CustomArrayAdapter    extends ArrayAdapter<Factory_data> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final ArrayList<Factory_data> items;
    private final int mResource;

    public CustomArrayAdapter(@NonNull Context context, @LayoutRes int resource,
                              @NonNull ArrayList<Factory_data> objects) {
        super(context, resource, 0, objects);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView offTypeTv = (TextView) view.findViewById(R.id.max_discount_txt);
        TextView numOffersTv = (TextView) view.findViewById(R.id.num_offers_txt);

        Factory_data offerData = items.get(position);

        offTypeTv.setText(offerData.getcompany_category_name().substring(20).replace("\";}", ""));
        numOffersTv.setText(offerData.getcompany_category_id());

        return view;
    }
}
