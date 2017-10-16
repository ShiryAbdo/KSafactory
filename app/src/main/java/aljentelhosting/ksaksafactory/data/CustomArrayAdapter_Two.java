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
 * Created by shirya on 19/09/17.
 */

public class CustomArrayAdapter_Two   extends ArrayAdapter<ads_category_Data> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final ArrayList<ads_category_Data> items;
    private final int mResource;

    public CustomArrayAdapter_Two(@NonNull Context context, @LayoutRes int resource,
                                  @NonNull ArrayList<ads_category_Data> objects) {
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

        ads_category_Data offerData = items.get(position);

        offTypeTv.setText(offerData.getAds_categories_name().substring(20).replace("\";}", ""));
        numOffersTv.setText(offerData.getAds_categories_id());

        return view;
    }
}
