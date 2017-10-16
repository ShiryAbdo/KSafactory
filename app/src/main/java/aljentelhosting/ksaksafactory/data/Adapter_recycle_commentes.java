package aljentelhosting.ksaksafactory.data;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
 import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;

/**
 * Created by Shaymaa on 7/3/2017.
 */

public class Adapter_recycle_commentes extends RecyclerView.Adapter<Adapter_recycle_commentes.ViewHolder> {
    private List<Get_Data> data_comment;
    private Context context;
    private int lastPosition=-1;
    public Adapter_recycle_commentes(List<Get_Data> android, Context c) {
        this.data_comment = android;
        this.context=c;
    }

    @Override
    public Adapter_recycle_commentes.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout. row_commentes_recycle, viewGroup, false);


        return new Adapter_recycle_commentes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter_recycle_commentes.ViewHolder viewHolder, final int position) {
        final Get_Data data = data_comment.get(position);
        Picasso.with(context).load(data.photo_link).centerCrop().fit().into(viewHolder.prd_image);
        if (data.Like_Stat.equals("1")){
            viewHolder.likeImage.setImageResource(R.drawable.liked);
            viewHolder.like_string.setText("أعجبنى");

        }

        final Adapter_recycle_commentes.ViewHolder viewe =viewHolder;
        final int[] P = {position};
        final Get_Data data1 = data_comment.get(P[0]);
        viewHolder.Like_cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.Like_Stat.equals("0")){
                   Picasso.with(context).load(R.drawable.liked).centerCrop().fit().into(viewe.likeImage);
                    Like_func(data.post_Id, "1");
                    change_Data(P[0],"1",data1.photo_link,data1.post_Id);
                //    Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();
                    P[0] =lastPosition;
                    view=null;
                }else {
                    Picasso.with(context).load(R.drawable.like).centerCrop().fit().into(viewe.likeImage);
                    Like_func(data.post_Id, "0");
                    change_Data(P[0],"0",data1.photo_link,data1.post_Id);
                   // Toast.makeText(context, "dis", Toast.LENGTH_SHORT).show();
                    view=null;
                    P[0] =lastPosition;

                }
            }
        });
        setAnimation(viewHolder.card,position);
    }

    void change_Data(int p, String L, String photo, String id){
        Get_Data opject=new Get_Data(id,L,photo);
        data_comment.set(p,opject);
        notifyDataSetChanged();
        Toast.makeText(context, String.valueOf(p)+ "", Toast.LENGTH_SHORT).show();
        p=-1;
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
        return data_comment.size();
    }




  private  void   Like_func(final String post_Id, final String like){

      StringRequest request=new StringRequest(Request.Method.POST,"http://homebussines.net/udemy_courses/like.php", new Response.Listener<String>(){
          @Override
          public void onResponse(String response) {
           if (response.contains("done")){

           }

          }

      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {

          }}) {
          @Override
          protected Map<String, String> getParams() {
              Map<String, String> params = new HashMap<String, String>();
              params.put("postid",post_Id);
              params.put("likee",like);
              return params;}};
      Volley.newRequestQueue(context).add(request);
  }


    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout Like_cont;
        TextView like_string;
        ImageView likeImage,prd_image;
        CardView card;
        public ViewHolder(View view) {
            super(view);
            Like_cont=(LinearLayout)view.findViewById(R.id.like_conte);
            like_string=(TextView)view.findViewById(R.id.like_string);
            likeImage=(ImageView)view.findViewById(R.id.like_imgeView);
            prd_image=(ImageView)view.findViewById(R.id.prd_image);
            card=(CardView)view.findViewById(R.id.card);

        }
    }}