package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import aljentelhosting.ksaksafactory.R;


public class Show_Industrial_cities extends AppCompatActivity {
    LinearLayout layou_over_all_factores,search_factors_layout_cites;
    ImageView go_back ,productesImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__industrial_cities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Show_Industrial_cities.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        layou_over_all_factores= (LinearLayout)findViewById(R.id.layou_over_all_factores);

        layou_over_all_factores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(getApplicationContext(),  Industrial_cities.class);
                startActivity(intent);
            }
        });
        search_factors_layout_cites=(LinearLayout)findViewById(R.id.search_factors_layout_cites);
        search_factors_layout_cites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),   Part_Industrial_cities.class);
                startActivity(intent);

            }
        });
        productesImage=(ImageView)findViewById(R.id.productesImage);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Show_Industrial_cities.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
