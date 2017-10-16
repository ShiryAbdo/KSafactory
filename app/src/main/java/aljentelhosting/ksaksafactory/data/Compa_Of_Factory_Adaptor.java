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
 * Created by shirya on 11/09/17.
 */

public class Compa_Of_Factory_Adaptor  extends RecyclerView.Adapter<Compa_Of_Factory_Adaptor.ViewHolder> {
    private ArrayList<Compa_Of_Factory_Data> androidList;
    private Context context;
    private int lastPosition=-1;

    public Compa_Of_Factory_Adaptor(ArrayList<Compa_Of_Factory_Data> android, Context c) {
        this.androidList = android;
        this.context=c;
    }

    @Override
    public Compa_Of_Factory_Adaptor.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. company_of_factory_row, viewGroup, false);


        return new Compa_Of_Factory_Adaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Compa_Of_Factory_Adaptor.ViewHolder viewHolder, final int i) {

        viewHolder.description_add.setText(androidList.get(i).getCompany_name());

//        viewHolder.cintery_name.setText(androidList.get(i).getCompany_id());



        final String imagee ="http://ksafactory.com/files/frontend/"+ androidList.get(i).getCompany_image_name();
        Picasso.with(context).load(imagee).error(android.R.drawable.stat_notify_error).fit().into(viewHolder.imageView_add);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,wasffWithTabb.class);

//                intent.putExtra("company_id",androidList.get(i).getCompany_id());
                intent.putExtra("company_id",androidList.get(i).getCompany_id());
                intent.putExtra("company_category_name",androidList.get(i).getCompany_category_name());
                intent.putExtra("image",imagee);
                context.startActivity(intent);
//                context.startActivity(new Intent(context,wasffWithTabb.class));
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


