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
import aljentelhosting.ksaksafactory.activites.A3lanActivityItem;

/**
 * Created by shirya on 31/08/17.
 */

public class e3lanatDataAdaptor  extends RecyclerView.Adapter<e3lanatDataAdaptor.ViewHolder> {
    private ArrayList<e3lanatData> androidList;
    private Context context;
    private int lastPosition=-1;
    String text;

    public e3lanatDataAdaptor(ArrayList<e3lanatData> android, Context c) {
        this.androidList = android;
        this.context=c;
    }

    @Override
    public e3lanatDataAdaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eslan_row, viewGroup, false);


        return new e3lanatDataAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final e3lanatDataAdaptor.ViewHolder viewHolder, final int i) {

   if(androidList.get(i).getAds_title().contains("ar")){
       text= androidList.get(i).getAds_title().substring(20).replace("\";}", "");

   }else {
       text=  androidList.get(i).getAds_title();
   }
          viewHolder.description_add.setText(text);
        final String imagee ="http://ksafactory.com/files/frontend/"+ androidList.get(i).getAds_image_name();
         Picasso.with(context).load(imagee).error(android.R.drawable.stat_notify_error).fit().into(viewHolder.imageView_add);
          viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,A3lanActivityItem.class);

                intent.putExtra("ads_id",androidList.get(i).getAds_id());
                intent.putExtra("image",imagee);
                context.startActivity(intent);

//                context.startActivity(new Intent(context,  A3lanActivityItem.class));
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
        private TextView description_add;
        private ImageView imageView_add;
        private CardView cardView;
        public ViewHolder(View view) {
            super(view);
            cardView=(CardView)view.findViewById(R.id.cardView);
            description_add = (TextView)view.findViewById(R.id.description_add);
            imageView_add = (ImageView)view.findViewById(R.id.imageView_add);


        }
    }

}