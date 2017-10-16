package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import aljentelhosting.ksaksafactory.R;


public class RegisterCompletTwo extends AppCompatActivity {
    EditText part_of_service,sherka_tadamonia,number_of_worker,adress_of_company,box_email,postel_code;
    Button register_as_moassa_two ;
    Bundle bundle;
    String name_of_comapy ,email_of_comapy ,phone_of_comapy,name_comany_arabick,name_comany_english;
    ImageView go_back ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_company_second);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterCompletTwo.this,  RegistrationCompany.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        part_of_service=(EditText)findViewById(R.id.part_of_service);
        sherka_tadamonia=(EditText)findViewById(R.id.sherka_tadamonia);
        number_of_worker=(EditText)findViewById(R.id.number_of_worker);
        adress_of_company=(EditText)findViewById(R.id.adress_of_company);
        box_email=(EditText)findViewById(R.id.box_email);
        postel_code=(EditText)findViewById(R.id.postel_code);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            name_of_comapy=bundle.getString("name_of_comapy");
            email_of_comapy=bundle.getString("email_of_comapy");
            phone_of_comapy=bundle.getString("phone_of_comapy");
            name_comany_arabick=bundle.getString("name_comany_arabick");
            name_comany_english=bundle.getString("name_comany_english");
         }



        register_as_moassa_two=(Button)findViewById(R.id.register_as_moassa_two);
        register_as_moassa_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterCompletTwo.this,RegisterCompletThree.class);
                intent.putExtra("part_of_service",part_of_service.getText().toString().trim());
                intent.putExtra("sherka_tadamonia",sherka_tadamonia.getText().toString().trim());
                intent.putExtra("adress_of_company",adress_of_company.getText().toString().trim());
                intent.putExtra("box_email",box_email.getText().toString().trim());
                intent.putExtra("postel_code",postel_code.getText().toString().trim());

                intent.putExtra("name_of_comapy",name_of_comapy);
                intent.putExtra("email_of_comapy",email_of_comapy);
                intent.putExtra("phone_of_comapy",phone_of_comapy);
                intent.putExtra("name_comany_arabick",name_comany_arabick);
                intent.putExtra("name_comany_english",name_comany_english);
                  startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(   RegisterCompletTwo.this,  RegistrationCompany.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
