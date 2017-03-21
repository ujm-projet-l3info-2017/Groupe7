package carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.sql.ResultSet;

import com.example.smail.testapp.R;

import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button bLogin, clickregist;
    EditText etUsername, etPassword;
    ConnexionDB conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        try {
            conexion = new ConnexionDB();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bclickLogin);
        clickregist = (Button) findViewById(R.id.clickRegist);

        bLogin.setOnClickListener(this);
        clickregist.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
       int r =0;
        ResultSet rs;

        switch (v.getId()) {
            case R.id.bclickLogin:
                String identifiant = etUsername.getText().toString();
                String mdp = etPassword.getText().toString();

                if (identifiant.trim().equals("")||mdp.trim().equals("")) {

                    r = 1;
                }
                if (r==1){
                    etUsername.setError("il faut remplir tout les champs");

                }
                else
                    {
                        try
                        {
                            String query = "select * from Utilasateur where username = " + identifiant + " password = " + mdp;
                            rs = conexion.reqSelect(query);

                            try {
                                if (rs.next())
                                {
                                    // startActivity(new Intent(this,PageAccueil.class));
                                }

                                }catch (SQLException e)
                               {
                                    e.printStackTrace();
                                   System.out.print("Compte inexistant");
                               }
                        } catch (SQLException e)
                        {
                            e.printStackTrace();
                        }
                    }
                 break;

            case R.id.clickRegist:
                startActivity(new Intent(this,Register.class));
                break;
        }
    }
}