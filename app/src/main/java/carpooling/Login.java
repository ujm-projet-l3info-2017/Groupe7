package carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smail.testapp.R;

public class Login extends AppCompatActivity implements View.OnClickListener
{
    Button bLogin, clickregist;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bclickLogin:
                String pseudo = etUsername.getText().toString();
                String mdp = etPassword.getText().toString();

                Log.d("d_Iden", pseudo);
                Log.d("d_Mdp", mdp);
                if (pseudo.trim().equals(""))


                if (pseudo.isEmpty())

                {
                    etUsername.setError("This field is required");
                }
               else if (mdp.isEmpty()) {
                        etPassword.setError("This field is required");
                    }
                else
                {
                    String[] response;
                    ServerCon con = new ServerCon();
                    con.send(new String[] {con.TYPE_AUTH, pseudo, mdp});
                    response = con.receive();

                    if (response.length != 2)
                    {
                        // TODO: error
                    }
                    switch(Integer.parseInt(response[1]))
                    {
                        case 0:
                            // TODO: login impossible
                            break;
                        case 1:
                            // TODO: Login OK
                            break;
                        default:
                            // TODO: error inconnue
                            break;
                    }

                    con.closeCon();
                }
                  break;

            case R.id.clickRegist:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}