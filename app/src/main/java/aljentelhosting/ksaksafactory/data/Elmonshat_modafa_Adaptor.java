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
import aljentelhosting.ksaksafactory.activites.wasffWithTabb;

/**
 * Created by shirya on 12/09/17.
 */

public class Elmonshat_modafa_Adaptor extends RecyclerView.Adapter<Elmonshat_modafa_Adaptor.ViewHolder> {
    private ArrayList<Elmonshat_modafa_Data> androidList;
    private Context context;
    private int lastPosition=-1;

    public Elmonshat_modafa_Adaptor(ArrayList<Elmonshat_modafa_Data> android, Context c) {
        this.androidList = android;
        this.context=c;
    }

    @Override
    public Elmonshat_modafa_Adaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.elmonsha_row, viewGroup, false);


        return new Elmonshat_modafa_Adaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Elmonshat_modafa_Adaptor.ViewHolder viewHolder, final int i) {

        viewHolder.company_name.setText(androidList.get(i).getCompany_name());

        final String imagee ="http://ksafactory.com/files/frontend/"+ androidList.get(i).getCompany_image_name();
        Picasso.with(context).load(imagee).error(android.R.drawable.stat_notify_error).fit().into(viewHolder.company_image);


        viewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,wasffWithTabb.class);

                intent.putExtra("company_id","3");
                intent.putExtra("company_category_name","null");
                intent.putExtra("image",imagee);
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
        private TextView company_name,date_insert;
        private CardView card;
        private ImageView company_image;
        public ViewHolder(View view) {
            super(view);
            company_image=(ImageView)view.findViewById(R.id.company_image);
            card=(CardView)view.findViewById(R.id.cardView);
            company_name = (TextView)view.findViewById(R.id.company_name);

        }
    }

}

