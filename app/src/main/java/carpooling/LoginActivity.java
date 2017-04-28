package carpooling;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smail.testapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    Button bLogin, clickregist;
    EditText etUsername, etPassword;
    private boolean loggedIn = false;

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
                String passwd = etPassword.getText().toString();

                try {
                    AsyncTask<String, Void, Boolean> lt = new LoginTask();
                    this.loggedIn = lt.execute(pseudo, passwd).get();

                    if (this.loggedIn)
                    {
                        Toast.makeText(getApplicationContext(), "Successfully logged in !", Toast.LENGTH_LONG).show();
                        /* TODO: redirect to proper activity */
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No such user !", Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "An unknown error happened !", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.clickRegist:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }
}