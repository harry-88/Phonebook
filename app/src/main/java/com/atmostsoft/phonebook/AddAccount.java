
package com.atmostsoft.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Instant;
import java.util.ArrayList;

import static com.atmostsoft.phonebook.R.drawable.man_background;

public class AddAccount extends AppCompatActivity {

    private int imgPref = 1;
    private Button btnChgImg, btnSave, btnCancel;
    private ImageView ivImg;
    private EditText etName, etNumber, etEmail, etDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        init();
    }

    private void init() {

        Intent intent = getIntent();
        etName = findViewById(R.id.etName);
        etName.setText(intent.getStringExtra("name"));
        etNumber = findViewById(R.id.etNumber);
        etNumber.setText(intent.getStringExtra("number"));
        etEmail = findViewById(R.id.etEmail);
        etEmail.setText(intent.getStringExtra("email"));
        etDob = findViewById(R.id.etDob);
        etDob.setText(intent.getStringExtra("dob"));
        btnSave = findViewById(R.id.btnSaveAccount);
        btnCancel = findViewById(R.id.btnCancelSave);
        ivImg = findViewById(R.id.ivChgImg);
        btnChgImg = findViewById(R.id.btnChgImg);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AddAccount.this,com.atmostsoft.phonebook.PhoneBook.class);
                startActivity(intent1);
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;
                if (etName.getText().toString().isEmpty()) {
                    flag = false;
                    etName.setError("Name is required");
                }
                if (etNumber.getText().toString().isEmpty()) {
                    flag = false;
                    etNumber.setError("Number is required");
                }
                if (etEmail.getText().toString().isEmpty()) {
                    flag = false;
                    etNumber.setError("Email is required");
                }
                if (etDob.getText().toString().isEmpty()) {
                    flag = false;
                    etNumber.setError("Dob is required");
                }
                if (flag) {
                    String line = etName.getText().toString()+","+etNumber.getText().toString()+","+imgPref+","+etEmail.getText().toString()+","+etDob.getText().toString()+",fav";

                    if(getIntent().getStringExtra("number") == null)
                        writeToFile("accounts.txt",line+"\n",AddAccount.this);
                    else
                        updateFile(line,AddAccount.this);
                    AccountInfo accountInfo = new AccountInfo(etName.getText().toString(),etNumber.getText().toString());
                    accountInfo.setPref(imgPref);
                    setResult(Activity.RESULT_OK);
                }
            }
        });
        btnChgImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imgPref == 1) {
                    ivImg.setImageResource(R.drawable.pic1);
                    imgPref++;
                } else if (imgPref == 2) {
                    ivImg.setImageResource(R.drawable.pic2);
                    imgPref++;
                } else if (imgPref == 3) {
                    ivImg.setImageResource(R.drawable.man);
                    imgPref = 1;
                }
            }
        });
    }
    private void writeToFile(String fileName,String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void updateFile(String line,Context context) {

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

                    if (getIntent().getStringExtra("name").equals(token[0]) && getIntent().getStringExtra("number").equals(token[1])) {

                        line = etName.getText().toString()+","+etNumber.getText().toString()+","+imgPref+","+etEmail.getText().toString()+","+etDob.getText().toString()+",fav";
                        writeToFile("temp.txt", line + "\n", this);
                    }
                    else
                        writeToFile("temp.txt",line,this);
                    stringBuilder.append("\n").append(receiveString);

                }

                bufferedReader.close();
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        File filetoDelete = new File("accounts.txt");
        filetoDelete.delete();
        File toRename = new File("temp.txt");
        toRename.renameTo(new File("accounts.txt"));

    }

}