package com.example.sqlopusingserver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class ViewData extends AppCompatActivity {
    TextView txtname,txtmail,txtmob,txtadd;
    String delweb="http://192.168.43.41/android/DeleteUser.php";
    String viewdata="http://192.168.43.41/android/Viewdata.php";

    Button upbtn,delbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        txtadd=findViewById(R.id.viewadd);
        txtmail=findViewById(R.id.viewemail);
        txtmob=findViewById(R.id.viewmob);
        txtname=findViewById(R.id.viewname);
        upbtn=findViewById(R.id.btnup);
        delbtn=findViewById(R.id.btndel);

        Intent intent=getIntent();
        String name=intent.getStringExtra("NAMee");

        getView(name);


        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdate(name);
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete(name);
            }
        });
    }
    public void getView(String name){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, viewdata, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int success=jsonObject.getInt("success");
                    if (success==1){
                        Toast.makeText(ViewData.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        String getname=jsonObject.getString("name");
                        String getmob=jsonObject.getString("mobno");
                        String getadd=jsonObject.getString("add");
                        String getem=jsonObject.getString("email");

                        txtname.setText(getname);
                        txtmail.setText(getem);
                        txtmob.setText(getmob);
                        txtadd.setText(getadd);
                    }
                    else {
                        Toast.makeText(ViewData.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(ViewData.this,""+e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewData.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> maps=new HashMap<>();
                maps.put("Name",name);
                return maps;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void onDelete(String name) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, delweb, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int success=jsonObject.getInt("success");
                    if (success==1){
                        Toast.makeText(ViewData.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(ViewData.this,""+jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(ViewData.this,""+e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewData.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("NAME",name);
                return map;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Intent intent=new Intent(ViewData.this,MainActivity.class);
        startActivity(intent);
    }

    public void onUpdate(String name) {
        Intent intent=new Intent(ViewData.this,Updatedata.class);
        intent.putExtra("NAMee",name);
        intent.putExtra("mail",txtmail.getText().toString());
        intent.putExtra("add",txtadd.getText().toString());
        intent.putExtra("mobb",txtmob.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(ViewData.this,ViewALLMembers.class);
        startActivity(intent);
    }
}

