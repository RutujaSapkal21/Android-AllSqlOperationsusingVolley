package com.example.sqlopusingserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Updatedata extends AppCompatActivity {
    EditText edtname,edtemail,edtmob,edtadd;
    Button btnup;
    String weburlup="http://192.168.43.41/android/Update.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedata);
        edtname=findViewById(R.id.getuser);
        edtemail=findViewById(R.id.getemail);
        edtadd=findViewById(R.id.getaddress);
        edtmob=findViewById(R.id.getmob);
        btnup=findViewById(R.id.getupdate);

        Intent intent=getIntent();
        String name=intent.getStringExtra("NAMee");
        String mail=intent.getStringExtra("mail");
        String mobb=intent.getStringExtra("mobb");
        String add=intent.getStringExtra("add");

        edtname.setText(name);
        edtadd.setText(add);
        edtemail.setText(mail);
        edtmob.setText(mobb);

        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpdate(name);
            }
        });

    }

    public void getUpdate(String name) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, weburlup, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int success=jsonObject.getInt("success");
                    if (success==1){
                        Toast.makeText(Updatedata.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Updatedata.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(Updatedata.this,""+e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Updatedata.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> maps=new HashMap<>();
                maps.put("Name",name);
                maps.put("name",edtname.getText().toString());
                maps.put("email",edtemail.getText().toString());
                maps.put("address",edtadd.getText().toString());
                maps.put("mobile",edtmob.getText().toString());
                return maps;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Intent intent=new Intent(Updatedata.this,ViewData.class);
        intent.putExtra("NAMee",edtname.getText().toString());
        startActivity(intent);

    }
}