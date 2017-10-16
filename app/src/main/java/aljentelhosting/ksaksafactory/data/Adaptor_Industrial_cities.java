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

 import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.activites.CitiesDetails;

/**
 * Created by shirya on 31/08/17.
 */

public class Adaptor_Industrial_cities extends RecyclerView.Adapter<Adaptor_Industrial_cities.ViewHolder> {
    private ArrayList<Industrial_cities_Data> androidList;
    private Context context;
    private int lastPosition=-1;
    String city_name ,factory_space, factory_title,nameActivity;


    public Adaptor_Industrial_cities(ArrayList<Industrial_cities_Data> android, Context c , String nameActivity) {
        this.androidList = android;
        this.context=c;
        this.nameActivity=nameActivity;
    }

    @Override
    public Adaptor_Industrial_cities.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.  industrial_cities_row, viewGroup, false);


        return new Adaptor_Industrial_cities.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adaptor_Industrial_cities.ViewHolder viewHolder, final int i) {
        if(androidList.get(i).getCity_name()!=null){
        viewHolder.city_name.setText(androidList.get(i).getCity_name().substring(20).replace("\";}", ""));

        }else {
            viewHolder.city_name.setText(androidList.get(i).getCity_name());
        }





        viewHolder.factory_space.setText(androidList.get(i).getFactory_space());
        viewHolder.factory_title.setText(androidList.get(i).getFactory_title());
        final String imageUr ="http://ksafactory.com/files/frontend/"+androidList.get(i). getFactory_image();
                Picasso.with(context).load(imageUr).error(android.R.drawable.stat_notify_error).fit().into(viewHolder.factory_image);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,CitiesDetails.class);
                intent.putExtra("imageUr",imageUr);
                intent.putExtra("nameActivity",nameActivity);
                intent.putExtra("city_name",androidList.get(i).getCity_name());
                intent.putExtra("Factory_space",androidList.get(i).getFactory_space());
                intent.putExtra("Factory_title",androidList.get(i).getFactory_title());
                intent.putExtra("contant",androidList.get(i).getContent());
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
