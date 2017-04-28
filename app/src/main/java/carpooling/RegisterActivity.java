package carpooling;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smail.testapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    Button bRegister;
    EditText etUsername, etMail, etPassword, etTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etMail = (EditText) findViewById(R.id.etMail);
        etTelephone = (EditText) findViewById(R.id.etTel);

        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        boolean registered = false;

        switch (v.getId())
        {
            case R.id.bRegister:
                String pseudo = etUsername.getText().toString();
                String passwd = etPassword.getText().toString();
                String mail = etMail.getText().toString();
                String tel = etTelephone.getText().toString();

                try
                {
                    AsyncTask<String, Void, Boolean> lt = new RegisterTask();
                    registered = lt.execute(pseudo, passwd, mail, tel).get();

                    if (registered)
                    {
                        Toast.makeText(getApplicationContext(), "Registration successful !", Toast.LENGTH_LONG).show();
                        /* TODO: redirect to proper activity */
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Registration failed, double check your input !", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "An unknown error happened !", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}


