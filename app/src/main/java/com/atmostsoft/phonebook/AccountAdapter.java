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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.zip.Inflater;

public class AccountAdapter  extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    Context context;
    LinearLayout linearLayout;
    private ArrayList<AccountInfo> list;

    public AccountAdapter(Context context, ArrayList<AccountInfo> list) {
        this.list = list;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPerson,ivCall;
        TextView tvName,tvNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPerson = itemView.findViewById(R.id.tvPerson);
            ivCall = itemView.findViewById(R.id.tvCall);
            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);

            linearLayout = itemView.findViewById(R.id.listId);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvNumber.setText(list.get(position).getNumber());

        if (list.get(position).getPref() == 1)
            holder.ivPerson.setImageResource(R.drawable.man);
        else if(list.get(position).getPref() == 2)
            holder.ivPerson.setImageResource(R.drawable.pic1);
        else
//            holder.ivPerson.setImageResource(R.drawable.pic5);
        holder.ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+holder.tvNumber.getText().toString()));
                context.startActivity(intent);
            }
        });


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,com.atmostsoft.phonebook.AccountDetailActivirt.class);
                intent.putExtra("name",holder.tvName.getText().toString());
                intent.putExtra("number",holder.tvNumber.getText().toString());
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
