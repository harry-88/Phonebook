package com.atmostsoft.phonebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.zip.Inflater;

public class FavAdaptor  extends RecyclerView.Adapter<FavAdaptor.ViewHolder> {

    Context context;
    LinearLayout favLayout;
    private ArrayList<AccountInfo> list;

    public FavAdaptor(Context context, ArrayList<AccountInfo> list) {
        this.list = list;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivFav;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.etName);
            ivFav = itemView.findViewById(R.id.ivFav);
            favLayout = itemView.findViewById(R.id.favId);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_number,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());


        if (list.get(position).getPref() == 1)
            holder.ivFav.setImageResource(R.drawable.man);
        else if(list.get(position).getPref() == 2)
            holder.ivFav.setImageResource(R.drawable.pic1);
        else
            holder.ivFav.setImageResource(R.drawable.pic2);
        favLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,com.atmostsoft.phonebook.AccountDetailActivirt.class);
                intent.putExtra("name",holder.tvName.getText().toString());
                intent.putExtra("number",list.get(position).getNumber());
                intent.putExtra("email",list.get(position).getEmail());
                intent.putExtra("dob",list.get(position).getDateOfBirth());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

