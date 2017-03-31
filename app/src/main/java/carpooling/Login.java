package carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smail.testapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button bLogin, clickregist;
    EditText etUsername, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bclickLogin);
        clickregist = (Button) findViewById(R.id.clickRegist);

        bLogin.setOnClickListener(this);
        clickregist.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bclickLogin:
                String identifiant = etUsername.getText().toString();
                String mdp = etPassword.getText().toString();

                if (identifiant.trim().equals(""))
                {
                    etUsername.setError("il faut remplir ce champ");

                }
                else if(mdp.trim().equals("")){
                    etPassword.setError("il faut remplir ce champ");
                }
                else
                {



                }

                 break;

            case R.id.clickRegist:
                startActivity(new Intent(this,Register.class));
                break;
        }
    }
}