package aljentelhosting.ksaksafactory.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;

import aljentelhosting.ksaksafactory.R;
import aljentelhosting.ksaksafactory.data.CustomArrayAdapter;
import aljentelhosting.ksaksafactory.data.Factory_data;
import aljentelhosting.ksaksafactory.data.JSONResponse;
import aljentelhosting.ksaksafactory.interfaces.RequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addFactory extends AppCompatActivity {

    Button cuttong_adding  ,cuttongadding;

    EditText name_of_onwe , name_of_factory, telphone_of_factory,phone_numbe, email_adress,
            what_is_producted  ,site;
     String emailRegEx;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Spinner catogery_of_factory;
    ArrayList<Factory_data> data;
    ArrayAdapter<Factory_data> dataAdapter;
    String catogery_of_factory_item;
    CustomArrayAdapter adapter ;
    String category_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_factory);
        cuttong_adding =(Button)findViewById(R.id.cuttong_adding);
        name_of_onwe=(EditText)findViewById(R.id.name_of_onwe);
        name_of_factory=(EditText)findViewById(R.id.name_of_factory);
        telphone_of_factory=(EditText)findViewById(R.id.telphone_of_factory);
        phone_numbe=(EditText)findViewById(R.id.phone_numbe);
        email_adress=(EditText)findViewById(R.id.email_adress);
        what_is_producted=(EditText)findViewById(R.id.what_is_producted);
        cuttongadding=(Button)findViewById(R.id.cuttongadding);
        site=(EditText)findViewById(R.id.site);
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        catogery_of_factory=(Spinner)findViewById(R.id.catogery_of_factory);

        loadJSON();
        // Spinner click listener
        catogery_of_factory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                // On selecting a spinner item
//                catogery_of_factory_item= parent.getItemAtPosition(position).toString();
                category_id = ((TextView)view.findViewById(R.id.num_offers_txt)).getText().toString().trim();

//                String item2 = ((TextView)view.findViewById(R.id.max_discount_txt)).getText().toString();
                // Showing selected spinner item
 //                Toast.makeText(parent.getContext(), item2, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//
        cuttongadding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


 if (email_adress.getText().toString().matches(emailRegEx) && email_adress.getText().toString().length() > 0)
                {
                    Intent intent = new Intent(addFactory.this,CuntinoAddTWO.class);
                    intent.putExtra("name_of_onwe",name_of_onwe.getText().toString().trim());
                    intent.putExtra("name_of_factory",name_of_factory.getText().toString().trim());
                    intent.putExtra("telphone_of_factory",telphone_of_factory.getText().toString().trim());
                    intent.putExtra("phone_numbe",phone_numbe.getText().toString().trim());
                    intent.putExtra("email_adress",email_adress.getText().toString().trim());
                    intent.putExtra("what_is_producted",what_is_producted.getText().toString().trim());
                    intent.putExtra("site",site.getText().toString().trim());
                    intent.putExtra("site",site.getText().toString().trim());
                    intent.putExtra("category_id",category_id);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"الإيميل غير صالح", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }





    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ksafactory.com/API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));


//            dataAdapter = new ArrayAdapter<Factory_data>(addFactory.this, android.R.layout.simple_spinner_item, data);

                // Drop down layout style - list view with radio button
//                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                adapter = new CustomArrayAdapter(addFactory.this,
                        R.layout.customspinneritem, data);

                catogery_of_factory.setAdapter(adapter);
               }
//////
            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }





    @Override
    public void onBackPressed() {
        Intent intent = new Intent( addFactory.this,  SoadyFactory.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
