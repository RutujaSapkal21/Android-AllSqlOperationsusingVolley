package com.example.sqlopusingserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewALLMembers extends AppCompatActivity {
    String viwall="http://192.168.43.41/android/viewallmem.php";
    List<Modulw> modulwList;
    MemAdaptor memAdaptor;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_a_l_l_members);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        modulwList=new ArrayList<>();

        getviewallmem();

    }
    public void getviewallmem(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, viwall, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int success=jsonObject.getInt("success");
                    if (success==1){
                        JSONArray jsonArray=jsonObject.getJSONArray("userlist");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=new JSONObject(jsonArray.getString(i));
                            String namee=jsonObject1.getString("name");
                            Modulw module=new Modulw();

                            module.setName(namee);

                            modulwList.add(module);


                            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                            recyclerView.setHasFixedSize(true);
                            memAdaptor=new MemAdaptor(modulwList,getBaseContext());
                            recyclerView.setAdapter(memAdaptor);
                            memAdaptor.notifyDataSetChanged();
                        }
                    }
                }
                catch (Exception e){
                    Toast.makeText(ViewALLMembers.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewALLMembers.this,""+error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
