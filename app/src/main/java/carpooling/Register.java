package carpooling;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smail.testapp.R;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button bRegister;
    EditText etName, etUsername, etAge, etPassword, etErreur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etAge = (EditText) findViewById(R.id.etAge);
        etPassword = (EditText) findViewById(R.id.etPassword);
        // etErreur = (EditText)findViewById(R.id.erreur);
        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);

    }

    @Override


    public void onClick(View v) {
        int r = 0;
        switch (v.getId()) {
            case R.id.bRegister:
                int agee = -1;
                String nom = etName.getText().toString();
                String prenom = etUsername.getText().toString();
                String mdp = etPassword.getText().toString();
                agee = Integer.parseInt(etAge.getText().toString());

                if (nom.trim().equals(""))
                {
                    etName.setError("iL faut remplir tout les champs");
                }
                else if (prenom.trim().equals(""))
                {
                    etUsername.setError("il faut remplir ce champ ");
                }
                else if (mdp.trim().equals(""))
                {
                    etPassword.setError("il faut remplir ce champ");
                }


               else
                {



                }
                break;

         }

    }

}




