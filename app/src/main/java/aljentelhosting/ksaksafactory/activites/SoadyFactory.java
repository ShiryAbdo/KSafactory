package aljentelhosting.ksaksafactory.activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import aljentelhosting.ksaksafactory.R;


public class SoadyFactory extends AppCompatActivity implements View.OnClickListener {
    LinearLayout search_factors_layout ,layou_over_all_factores ,add_new_factory;
    ImageView go_back ;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPref;
    String idi,typeShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysoadyfactory);
        sharedPref = getApplicationContext().getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        idi= sharedPref.getString("id", null);
        typeShared= sharedPref.getString("type", "null");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoadyFactory.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });


        search_factors_layout   = (LinearLayout) findViewById(R.id.search_factors_layout);
        layou_over_all_factores = (LinearLayout) findViewById(R.id.layou_over_all_factores);
        add_new_factory         = (LinearLayout) findViewById(R.id.add_new_factory);


        search_factors_layout   .setOnClickListener(this);
        layou_over_all_factores.setOnClickListener(this);
                add_new_factory.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int Id=view.getId();
        switch (Id){
            case R.id.add_new_factory:
                if(typeShared.equals("1")){
                    Toast.makeText(SoadyFactory.this,"عليك  التسجيل كا منشأة", Toast.LENGTH_SHORT).show();
                }
                if (typeShared.equals("2")){
                    Move_on(addFactory.class);
                }

                break;


        case R.id.layou_over_all_factores:

            Move_on(AllFactores_Category.class);
            break;

            case R.id.search_factors_layout:
                Move_on( SearchWithNameActivity.class);
                break;
        }
    }

    void Move_on( Class theclass){
        startActivity(new Intent(getApplicationContext(),theclass));
        SoadyFactory.this.finish();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SoadyFactory.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
