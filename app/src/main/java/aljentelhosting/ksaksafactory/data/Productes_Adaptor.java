package aljentelhosting.ksaksafactory.data;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.activites.DetalisOfProduction_WitAdd;
import aljentelhosting.ksaksafactory.activites.ProductesActivity;

/**
 * Created by shirya on 12/09/17.
 */



public class Productes_Adaptor   extends RecyclerView.Adapter<Productes_Adaptor.ViewHolder> {
    private ArrayList<Productes_data> androidList;
    private Context context;
    private int lastPosition=-1;
    String city_name ,factory_space, factory_title;
    ProductesActivity productesActivity= new ProductesActivity();
    String id_company ,company_category_name;


    public Productes_Adaptor(ArrayList<Productes_data> android, Context c , String id_company ,
                             String company_category_name) {
        this.androidList = android;
        this.context=c;
        this.id_company=id_company;
        this.company_category_name=company_category_name;
     }

    @Override
    public Productes_Adaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.  industrial_cities_row, viewGroup, false);


        return new Productes_Adaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Productes_Adaptor.ViewHolder viewHolder, final int i) {

        viewHolder.city_name.setText(androidList.get(i).getProduct_title());


////        String city_name;
//        if(androidList.get(i).getCity_name().contains("ar")){
//            viewHolder.city_name.setText(androidList.get(i).getCity_name().substring(20).replace("\";}", ""));
////            city_name= androidList.get(i).getCity_name().substring(18).replace("\";}", "");
//
//        }else {
//            viewHolder.city_name.setText(androidList.get(i).getCity_name());
//        }



         viewHolder.factory_space.setText(androidList.get(i).getProduct_service());
        viewHolder.factory_title.setText(androidList.get(i).getDate());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetalisOfProduction_WitAdd.class);
                intent.putExtra("company_id", id_company);
                intent.putExtra("company_category_name",company_category_name);
                intent.putExtra("id",androidList.get(i).getProduct_id());
                context.startActivity(intent);
            }
        });
        setAnimation(viewHolder.cardView, i);

    }



    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return androidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView city_name,factory_space,factory_title;
        private CardView cardView;
        private ImageView factory_image;
        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view.findViewById(R.id.cardView);
            city_name = (TextView)view.findViewById(R.id.city_name);
            factory_space = (TextView)view.findViewById(R.id.factory_space);
            factory_title = (TextView)view.findViewById(R.id.factory_title);
            factory_image = (ImageView)view.findViewById(R.id.factory_image);

        }
    }

}
