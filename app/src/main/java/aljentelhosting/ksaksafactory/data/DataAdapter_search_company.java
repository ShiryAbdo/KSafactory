package aljentelhosting.ksaksafactory.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;


/**
 * Created by shirya on 11/09/17.
 */

public class DataAdapter_search_company  extends RecyclerView.Adapter<DataAdapter_search_company.ViewHolder> implements Filterable {
    private ArrayList<search_company_Data> mArrayList;
    private ArrayList<search_company_Data> mFilteredList;

    public DataAdapter_search_company(ArrayList< search_company_Data> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    @Override
    public DataAdapter_search_company.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter_search_company.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(mFilteredList.get(i).getCompany_name());
        viewHolder.tv_version.setText(mFilteredList.get(i).getCompany_category_name().substring(20).replace("\";}", ""));
//        viewHolder.tv_api_level.setText(mFilteredList.get(i).getCompany_type_name());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<search_company_Data> filteredList = new ArrayList<>();

                    for (search_company_Data androidVersion : mArrayList) {

                        if (androidVersion.getCompany_name().toLowerCase().contains(charString) || androidVersion.getCompany_category_name().toLowerCase().contains(charString) || androidVersion.getCompany_about().toLowerCase().contains(charString)) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<search_company_Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_version = (TextView)view.findViewById(R.id.name_factory);
            tv_api_level = (TextView)view.findViewById(R.id.tv_api_level);

        }
    }

}