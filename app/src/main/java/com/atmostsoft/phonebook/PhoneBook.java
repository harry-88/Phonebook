package com.atmostsoft.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class PhoneBook extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    RecyclerView recyclerViewAccount;
    RecyclerView.LayoutManager layoutManagerAccount;
    RecyclerView.Adapter adapterAccount;

    ImageView ivAdd;
    EditText etSearch;
    ImageView btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonebook);
        init();

        recyclerView = findViewById(R.id.list1);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<AccountInfo> list = getFavAccounts(this);

        adapter = new FavAdaptor(this,list);
        recyclerView.setAdapter(adapter);


        ArrayList<AccountInfo> arrayList =  readFromFile(PhoneBook.this);
        recyclerViewAccount = findViewById(R.id.accountList);
        recyclerViewAccount.setHasFixedSize(true);
        layoutManagerAccount = new LinearLayoutManager(this);
        recyclerViewAccount.setLayoutManager(layoutManagerAccount);
        adapterAccount = new AccountAdapter(this,arrayList);
        recyclerViewAccount.setAdapter(adapterAccount);



    }

    public void init()
    {
        etSearch = findViewById(R.id.etSearch);

        btn = findViewById(R.id.btnFlt);
        btn.setImageResource(R.drawable.ic_baseline_edit_24);
        ivAdd = findViewById(R.id.addBtn);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneBook.this,com.atmostsoft.phonebook.AddAccount.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private ArrayList<AccountInfo> readFromFile(Context context) {

        String ret = "";
        ArrayList<AccountInfo>  arrayList = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("accounts.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                String token[] = null;
                int i = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    token = receiveString.split(",");
                    arrayList.add(new AccountInfo(token[0],token[1],token[3],token[4]));
                    arrayList.get(i++).setPref(Integer.parseInt(token[2]));
                    stringBuilder.append("\n").append(receiveString);

                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return arrayList;

    }


    private ArrayList<AccountInfo> getFavAccounts(Context context) {

        ArrayList<AccountInfo>  arrayList = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("accounts.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                String token[] = null;
                int i = 0;
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    token = receiveString.split(",");
                    System.out.println(receiveString);
                    if (token.length == 6)

                        if (token[5].equals("fav") )
                        {
                            Toast.makeText(context, receiveString, Toast.LENGTH_SHORT).show();
                            arrayList.add(new AccountInfo(token[0], token[1], token[3], token[4]));
                            arrayList.get(i++).setPref(Integer.parseInt(token[2]));
                            stringBuilder.append("\n").append(receiveString);
                        }

                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return arrayList;

    }
}