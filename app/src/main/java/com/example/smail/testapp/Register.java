package com.example.smail.testapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Register extends AppCompatActivity implements View.OnClickListener{
    Button bRegister;
    EditText etName,etUsername,etAge,etPassword,etErreur;
     ConnexionDB conexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*try {
            conexion = new ConnexionDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        etName =(EditText)findViewById(R.id.etName);
        etUsername =(EditText)findViewById(R.id.etUsername);
        etAge=(EditText)findViewById(R.id.etAge);
        etPassword=(EditText)findViewById(R.id.etPassword);
       // etErreur = (EditText)findViewById(R.id.erreur);
        bRegister = (Button)findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

    }

    @Override



    public void onClick(View v)
    { int r =0;
        switch (v.getId())
        {
            case R.id.bRegister:
               int agee = 0;
                String nom = etName.getText().toString();
                String prenom = etUsername.getText().toString();
                String mdp = etPassword.getText().toString();
           //   agee =  Integer.parseInt(etAge.getText().toString());

              if(nom.trim().equals("")||prenom.trim().equals("")||mdp.trim().equals("")){

                  r=1;
              }
              if (r==1){
                  etName.setError("iL faut remplir tout les champs");
              }
/*

           else     {
                      try {
                             String query = "INSERT into Utilisateur('name, 'username','password','age') VALUES(nom,prenom,mdp,agee)";
                              conexion.reqSQL(query, 'm');
                               System.out.print(" reussi");

                         } catch (SQLException e) {
                          e.printStackTrace();
                        }

                }
                */
                break;
        }



    }






}
