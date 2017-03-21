package com.example.smail.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button bLogin,bReg;
    ConnexionClienServer conServer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conServer  = new ConnexionClienServer();



        bReg = (Button)findViewById(R.id.bRegisterBegin);
        bLogin = (Button)findViewById(R.id.bLoginBegin);


        bReg.setOnClickListener(this);
        bLogin.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bLoginBegin:
                startActivity(new Intent(this,Login.class));
                break;

            case R.id.bRegisterBegin:
                startActivity(new Intent(this,Register.class));
                break;

        }
    }


}
