package com.example.salesmonitoring_652019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Objects;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.content.Intent;
import android.location.LocationManager;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.content.Intent;
import android.location.LocationManager;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username,password;
    RadioGroup radioGroup;
    RadioButton admin;
    RadioButton salesman;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.submit);
        radioGroup = findViewById(R.id.radioGroup);
        admin=(RadioButton) findViewById(R.id.admin);
        salesman=(RadioButton)findViewById(R.id.salesman);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("Admin") && pass.equals("Admin")){
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    if(radioId==admin.getId()){
                        openActivityAdmin();
                    }
                    if(radioId==salesman.getId()){
                        openActivitySalesman();
                    }
                    Toast.makeText(getApplicationContext(),"Valid",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid",Toast.LENGTH_LONG).show();
                }

                if(password.getText().toString().equals("")){
                    password.setError("Enter Password");
                }

            }
        });


    }
    public void checkPerson(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
    }
    public void openActivityAdmin(){
        Intent intent = new Intent(this,ActivityAdmin.class);
        startActivity(intent);
    }
    public void openActivitySalesman(){
        Intent intent = new Intent(this,ActivitySalesman.class);
        startActivity(intent);
    }
}
