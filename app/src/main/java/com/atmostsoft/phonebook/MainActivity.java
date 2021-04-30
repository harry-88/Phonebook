package com.atmostsoft.phonebook;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.Account;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<AccountInfo> arrayList =  new ArrayList<>();
        arrayList.add(new AccountInfo("haris","03044195933","haris","09-12-2000"));
        arrayList.add(new AccountInfo("saad","03044195345","saad","09-12-2000"));
        arrayList.add(new AccountInfo("saqib","03044195764","haris","09-12-2000"));
        arrayList.add(new AccountInfo("jawad","03044195456","haris","09-12-2000"));

        adapter = new FavAdaptor(this,arrayList);
        recyclerView.setAdapter(adapter);
    }
}