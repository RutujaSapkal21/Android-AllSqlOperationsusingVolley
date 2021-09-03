package com.example.sqlopusingserver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemAdaptor extends RecyclerView.Adapter<MemAdaptor.MemHolder> {
    List<Modulw> userList;
    Context mContext;

    public MemAdaptor(List<Modulw> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        myview=layoutInflater.inflate(R.layout.customdesign,parent,false);

        return new MemHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MemHolder holder, int position) {
        Modulw module=userList.get(position);
        holder.txt1.setText(module.getName());

        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ViewData.class);
                intent.putExtra("NAMee",module.getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MemHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        public MemHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.disname);
        }
    }
}
