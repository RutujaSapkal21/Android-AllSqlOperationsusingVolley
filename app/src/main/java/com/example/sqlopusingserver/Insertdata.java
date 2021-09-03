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

public class Insertdata extends AppCompatActivity {
    EditText edtname,edtemail,edtmob,edtadd;
    String weburlin="http://192.168.43.41/android/Insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertdata);

        edtname=findViewById(R.id.getuser);
        edtemail=findViewById(R.id.getemail);
        edtadd=findViewById(R.id.getaddress);
        edtmob=findViewById(R.id.getmob);
    }

    public void getInsert(View view) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, weburlin, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int success=jsonObject.getInt("success");
                    if (success==1){
                        Toast.makeText(Insertdata.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Insertdata.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                                    }
                catch (Exception e){
                    Toast.makeText(Insertdata.this,""+e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Insertdata.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> maps=new HashMap<>();
                maps.put("NAME",edtname.getText().toString());
                maps.put("EMAIL",edtemail.getText().toString());
                maps.put("ADDRESS",edtadd.getText().toString());
                maps.put("MOBILE",edtmob.getText().toString());
                return maps;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Intent intent=new Intent(Insertdata.this,MainActivity.class);
        startActivity(intent);
    }
}