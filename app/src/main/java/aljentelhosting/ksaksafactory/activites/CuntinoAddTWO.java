package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.maps.CountinuAdding;


public class  CuntinoAddTWO extends AppCompatActivity {
    EditText city_name ,facebok ,Twitter,Google ,instgrame ,discraption ,fax;
    Button cuttong_adding;
    Bundle bundle;

    String name_of_onwe,name_of_factory,telphone_of_factory,phone_numbe,email_adress,
            what_is_producted,site,category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuntino_add_two);
        bundle=getIntent().getExtras();
        if(bundle!=null){
            name_of_onwe=bundle.getString("name_of_onwe");
            name_of_factory=bundle.getString("name_of_factory");
            telphone_of_factory=bundle.getString("telphone_of_factory");
            phone_numbe=bundle.getString("phone_numbe");
            email_adress=bundle.getString("email_adress");
            what_is_producted=bundle.getString("what_is_producted");
            site=bundle.getString("site");
            category_id= bundle.getString("category_id");
        }

        city_name=(EditText)findViewById(R.id.city_name);
        facebok=(EditText)findViewById(R.id.facebok);
        Twitter=(EditText)findViewById(R.id.Twitter);
        instgrame=(EditText)findViewById(R.id.instgrame);
        discraption=(EditText)findViewById(R.id.discraption);
        Google=(EditText)findViewById(R.id.Google);
        fax=(EditText)findViewById(R.id.fax);
        cuttong_adding=(Button)findViewById(R.id.cuttong_adding);


        cuttong_adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CuntinoAddTWO.this,CountinuAdding.class);
                intent.putExtra("city",city_name.getText().toString().trim());
                intent.putExtra("facebook",facebok.getText().toString().trim());
                intent.putExtra("twitter",facebok.getText().toString().trim() );
                intent.putExtra("instegrame",facebok.getText().toString().trim());
                intent.putExtra("google",facebok.getText().toString().trim());
                intent.putExtra("fax",facebok.getText().toString().trim());
                intent.putExtra("image",discraption.getText().toString().trim());
                intent.putExtra("site",site);
                intent.putExtra("name_of_onwe",name_of_onwe);
                intent.putExtra("name_of_factory",name_of_factory);
                intent.putExtra("telphone_of_factory",telphone_of_factory);
                intent.putExtra("phone_numbe",phone_numbe);
                intent.putExtra("email_adress",email_adress);
                intent.putExtra("what_is_producted",what_is_producted);
                intent.putExtra("category_id",category_id);
                startActivity(intent);

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent( CuntinoAddTWO.this,  addFactory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
