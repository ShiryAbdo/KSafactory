package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import aljentelhosting.ksaksafactory.R;


public class MainActivity extends AppCompatActivity {


    LinearLayout productesImage_layout ,layou_factory,cyties_dastries_layout ,layout_acount_user
          ,layot_factory_parts,layout_supports,layou_media ,layout_bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);

//
//        productesImage_layout= (LinearLayout)findViewById(R.id.productesImage_layout);
//        productesImage_layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),ProductesActivity.class);
//                startActivity(intent);
//
//            }
//        });
////
        layou_factory= (LinearLayout)findViewById(R.id.layou_factory);
        layou_factory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SoadyFactory.class);
                startActivity(intent);

            }
        });
//


        layot_factory_parts= (LinearLayout)findViewById(R.id.layot_factory_parts);

        layot_factory_parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),    FactoryParts.class);
                startActivity(intent);

            }
        });

//
        layout_supports= (LinearLayout)findViewById(R.id.layout_supports);

        layout_supports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SupportActivity.class);
                startActivity(intent);

            }
        });




        layou_media=(LinearLayout)findViewById(R.id.layout_media);
        layou_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(getApplicationContext(), e3lanatActivity.class);
                startActivity(intent);

            }
        });


        layout_bank= (LinearLayout)findViewById(R.id.layout_bank);

        layout_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BankActivity.class);
                startActivity(intent);

            }
        });



        cyties_dastries_layout= (LinearLayout)findViewById(R.id.cyties_dastries_layout);
        cyties_dastries_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Show_Industrial_cities.class);
                startActivity(intent);

            }
        });


        layout_acount_user =(LinearLayout)findViewById(R.id.layout_acount_user);
        layout_acount_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AcountUser.class);
                startActivity(intent);

            }
        });
    }
}
