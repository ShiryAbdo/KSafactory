package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.others.AppController;

public class RegisterCompletThree extends AppCompatActivity {
    EditText number_of_fax,web_url,name_of_head,represint_company,numer_segil_togary,password,re_password;
    Button complet_register_moassa;
    Bundle bundle;

    String name_of_comapy ,email_of_comapy ,phone_of_comapy,
            name_comany_arabick,name_comany_english,item_spinner,
            part_of_service, sherka_tadamonia,adress_of_company,
            box_email,postel_code;
    String myUrl;
    ImageView go_back ;


    public static final String KEY_USERNAME = "username";
    public static final String KEY_name_ar = "name_ar";
    public static final String KEY_name_en = "name_en";
    public static final String KEY_email = "email";
    public static final String kEY_mail_box ="mail_box";

    public static final String kEY_mail_symbol ="mail_symbol";
    public static final String kEY_phone ="phone";
    public static final String kEY_fax ="fax";
    public static final String kEY_site ="site";
    public static final String kEY_manager ="manager";
    public static final String kEY_representative ="representative";


    public static final String kEY_address ="address";
    public static final String kEY_password ="password";
    public static final String kEY_id ="id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_company_thrid);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // back to main activity
        go_back =(ImageView)findViewById(R.id.go_back);
        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterCompletThree.this,  RegisterCompletTwo.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        bundle=getIntent().getExtras();

        if (bundle!=null){
            // from frist fagment
            name_of_comapy=bundle.getString("name_of_comapy");
            email_of_comapy=bundle.getString("email_of_comapy");
            phone_of_comapy=bundle.getString("phone_of_comapy");
            name_comany_arabick=bundle.getString("name_comany_arabick");
            name_comany_english=bundle.getString("name_comany_english");
            item_spinner=bundle.getString("item_spinner");

      // from second fragment
            part_of_service=bundle.getString("part_of_service");
            sherka_tadamonia=bundle.getString("sherka_tadamonia");
            adress_of_company=bundle.getString("adress_of_company");
            box_email=bundle.getString("box_email");
            postel_code=bundle.getString("postel_code");



        }

        number_of_fax= (EditText)findViewById(R.id.number_of_fax);
        web_url= (EditText)findViewById(R.id.web_url);
        name_of_head= (EditText)findViewById(R.id.name_of_head);
        represint_company= (EditText)findViewById(R.id.represint_company);
        numer_segil_togary= (EditText)findViewById(R.id.numer_segil_togary);
        password= (EditText)findViewById(R.id.password);
        re_password= (EditText)findViewById(R.id.re_password);

        complet_register_moassa=(Button)findViewById(R.id.complet_register_moassa);
        complet_register_moassa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password.getText().toString().trim().equals(re_password.getText().toString().trim())){
                    registerUser(name_of_comapy,name_comany_arabick,name_comany_english,email_of_comapy,box_email,postel_code,phone_of_comapy,
                            check_the_EditText(number_of_fax),check_the_EditText(web_url),check_the_EditText(name_of_head),
                            check_the_EditText(represint_company),check_the_EditText(represint_company),check_the_EditText(represint_company),""
                    );

                }else {
                    Toast.makeText(getApplicationContext(), "كلمة المرور غير  متطابقة", Toast.LENGTH_LONG).show();

                }



            }
        });

    }

    private void registerUser(final String username , final String name_ar , final String name_en
                             , final String email , final String mail_box, final String mail_symbol , final String phone,
                              final String fax , final String site, final String manager , final String representative
                              , final String address  , final String password, final String id){



        Uri.Builder builder = new Uri.Builder();
//        "http://ksafactory.com/API/login/index.php?email=m@gmail.com&password=6666&type=1"
//        http://ksafactory.com/API/login/index.php
        builder.scheme("http")
                .authority("www.ksafactory.com")
                .appendPath("API")
                .appendPath("registration")
                .appendPath("index1.php")
                .appendQueryParameter("username",username)
                .appendQueryParameter("name_ar", name_ar)
                .appendQueryParameter("name_en",name_en)
                .appendQueryParameter("email",email)
                .appendQueryParameter("mail_box",mail_box)
                .appendQueryParameter("mail_symbol",mail_symbol)
                .appendQueryParameter("fax",fax)
                .appendQueryParameter("site",site)
                .appendQueryParameter("manager",manager)
                .appendQueryParameter("representative",representative)
                .appendQueryParameter("address",address)
                .appendQueryParameter("password",password)
                .appendQueryParameter("id",id);
        myUrl= builder.build().toString();
        String tag_string_req = "req_register";




        StringRequest stringRequest = new StringRequest(Request.Method.POST, myUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Toast.makeText(getApplicationContext(), "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();


                        startActivity(new Intent(getApplicationContext(),  LOgActivty.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterCompletThree.this,"errrrorrr", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,username);
                params.put(KEY_name_ar,name_ar);
                params.put(KEY_name_en,name_en);
                params.put(KEY_email,email);
                params.put(kEY_mail_box,mail_box);
                params.put(kEY_mail_symbol,mail_symbol);
                params.put(kEY_phone,phone);
                params.put(kEY_fax,fax);
                params.put(kEY_site,site);
                params.put(kEY_manager,manager);
                params.put(kEY_representative,representative);
                params.put(kEY_address,address);
                params.put(kEY_password,password);
                params.put(kEY_id,id);
                  return params;
            }

        };






        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest, tag_string_req);



    }



    private String check_the_EditText(EditText Text){
        String the_returnData="";
        if(TextUtils.isEmpty(Text.getText().toString())){
            Text.setError("Requier");
        }else{
            the_returnData= Text.getText().toString()   ;

        }
        return the_returnData;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(   RegisterCompletThree.this, RegisterCompletTwo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();;
    }
}
