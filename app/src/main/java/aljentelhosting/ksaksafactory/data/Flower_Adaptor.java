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
import android.widget.TextView;


import java.util.ArrayList;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.activites.AcountUser;

/**
 * Created by shirya on 12/09/17.
 */

public class Flower_Adaptor extends RecyclerView.Adapter<Flower_Adaptor.ViewHolder> {
    private ArrayList<Folwers_Data> androidList;
    private Context context;
    private int lastPosition=-1;

    public Flower_Adaptor(ArrayList<Folwers_Data> android, Context c) {
        this.androidList = android;
        this.context=c;
    }

    @Override
    public Flower_Adaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.flower_row, viewGroup, false);


        return new Flower_Adaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Flower_Adaptor.ViewHolder viewHolder, final int i) {

        viewHolder.username.setText(androidList.get(i).getUsername());

        viewHolder.date_insert.setText(androidList.get(i).getDate_insert());



        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,AcountUser.class);


                context.startActivity(intent);
            }
        });
        setAnimation(viewHolder.card, i);

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
        private TextView username,date_insert;
        private CardView card;
        public ViewHolder(View view) {
            super(view);
            card=(CardView)view.findViewById(R.id.cardView);
            username = (TextView)view.findViewById(R.id.username);
            date_insert = (TextView)view.findViewById(R.id.date_insert);

        }
    }

}

