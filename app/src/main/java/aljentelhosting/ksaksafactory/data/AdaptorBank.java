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
import aljentelhosting.ksaksafactory.activites.MainActivity;

/**
 * Created by shirya on 29/08/17.
 */

public class AdaptorBank extends RecyclerView.Adapter<AdaptorBank.ViewHolder> {
    private ArrayList<Bank_data> androidList;
    private Context context;
    private int lastPosition=-1;


    public AdaptorBank(ArrayList<Bank_data> android, Context c) {
        this.androidList = android;
        this.context=c;
    }

    @Override
    public AdaptorBank.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.  bank_rwo, viewGroup, false);


        return new AdaptorBank.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdaptorBank.ViewHolder viewHolder, int i) {

        viewHolder.name_of_bank.setText(androidList.get(i).getBank());
        viewHolder.number_of_bank.setText(androidList.get(i).getBank_number());
        viewHolder.name_of_company.setText(androidList.get(i).getCompany_name());
        viewHolder.number_of_acount.setText(androidList.get(i).getBank_ipan());
        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, MainActivity.class));
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
        private TextView name_of_bank,number_of_bank,name_of_company ,number_of_acount;
        private CardView card;
        public ViewHolder(View view) {
            super(view);
            card=(CardView)view.findViewById(R.id.card);
            name_of_bank = (TextView)view.findViewById(R.id.name_of_bank);
            number_of_bank = (TextView)view.findViewById(R.id.number_of_bank);
            name_of_company = (TextView)view.findViewById(R.id.name_of_company);
            number_of_acount = (TextView)view.findViewById(R.id.number_of_acount);

        }
    }

}

