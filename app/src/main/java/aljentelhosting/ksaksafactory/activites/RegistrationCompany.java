package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import aljentelhosting.ksaksafactory.R;


public class RegistrationCompany extends AppCompatActivity {
 EditText name_user, email_user,phone_user,name_comany_arabick,name_comany_english;

    String item;
    Spinner spinner;
    String emailRegEx ;
    Button register_as_moassa ;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String emails ;
    ImageView go_back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_company_frist);
        register_as_moassa= (Button)findViewById(R.id.register_as_moassa);


        name_user=(EditText)findViewById(R.id.name_user);
        email_user=(EditText)findViewById(R.id.email_user);
        phone_user=(EditText)findViewById(R.id.phone_user);
        name_comany_arabick=(EditText)findViewById(R.id.name_comany_arabick);
        name_comany_english=(EditText)findViewById(R.id.name_comany_english);
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationCompany.this,  LOgActivty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
      emails =email_user.getText().toString().trim();




        register_as_moassa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( email_user.getText().toString().trim().matches(emailRegEx) && email_user.getText().toString().trim().length() > 0)
                {
                    Intent intent=new Intent(RegistrationCompany.this,RegisterCompletTwo.class);
                    intent.putExtra("name_of_comapy",name_user.getText().toString().trim());
                    intent.putExtra("email_of_comapy",email_user.getText().toString().trim());
                    intent.putExtra("phone_of_comapy",phone_user.getText().toString().trim());
                    intent.putExtra("name_comany_arabick",name_comany_arabick.getText().toString().trim());
                    intent.putExtra("name_comany_english",name_comany_english.getText().toString().trim());
                     startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"الإيميل غير صالح", Toast.LENGTH_SHORT).show();
                }



            }
        });




    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(  RegistrationCompany.this,  LOgActivty.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
