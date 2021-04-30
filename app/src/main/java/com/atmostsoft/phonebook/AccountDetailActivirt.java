package com.atmostsoft.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AccountDetailActivirt extends AppCompatActivity {

    ImageView ivBack, ivEdit,ivCall;
    TextView etName,etNumber,etEmail,etDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        init();
    }

    public void init() {


        System.out.println("in detail activity");
        Intent intent = getIntent();
        etName = findViewById(R.id.tvNameDetil);
        etName.setText(intent.getStringExtra("name"));
        etNumber = findViewById(R.id.number1);
        etNumber.setText(intent.getStringExtra("number"));
        etEmail = findViewById(R.id.tvEmail);
        etEmail.setText(intent.getStringExtra("email"));
        etDob = findViewById(R.id.tvDob);
        etDob.setText(intent.getStringExtra("dob"));

        ivCall = findViewById(R.id.ivCallDetail);
        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+etNumber.getText().toString()));
                startActivity(intent1);
            }
        });
        ivBack = findViewById(R.id.icBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountDetailActivirt.this, com.atmostsoft.phonebook.PhoneBook.class);
                startActivity(intent);
                finish();
            }
        });
        ivEdit = findViewById(R.id.icEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccountDetailActivirt.this, com.atmostsoft.phonebook.AddAccount.class);
                intent.putExtra("name",etName.getText().toString());
                intent.putExtra("number",etNumber.getText().toString());
                intent.putExtra("email",etEmail.getText().toString());
                intent.putExtra("dob",etDob.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}
