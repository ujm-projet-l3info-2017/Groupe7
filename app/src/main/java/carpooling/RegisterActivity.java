package carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smail.testapp.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button bRegister;
    EditText etUsername, etMail, etPassword, etTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    public void onClick(View v) {
        int r = 0;
        switch (v.getId()) {
            case R.id.bRegister:

                String pseudo = etUsername.getText().toString();
                String mdp = etPassword.getText().toString();
                String mail = etMail.getText().toString();
                String telephone = etTelephone.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (pseudo.trim().equals("") | pseudo.length() < 4) {
                    etUsername.setError("iL faut remplir ce champ : minimum 4 caractere");
                }
                else if (mdp.trim().equals("") || mdp.length() < 4) {
                    etPassword.setError("il faut remplir ce champ : minimum 4 caractere");
                }
                else if (mail.trim().equals("")) {
                    etMail.setError("il faut remplir ce champ");
                }
                else if (!mail.matches(emailPattern)) {

                    etMail.setError("exemple format: adel.23000@hotmail.fr");

                }
                else if (telephone.trim().equals("") || telephone.length() < 10) {

                    etTelephone.setError("il faut remplir ce champ : 10 chiffres");
                }
                else {
                    Log.d("d_Iden", pseudo);
                    Log.d("d_Mdp", mdp);
                    Log.d("d_Mail", mail);
                    Log.d("d_Tel", telephone);
                    try {

                        String[] response;
                        ServerCon con = new ServerCon();
                        con.send(new String[]{con.TYPE_REGISTER, pseudo, mdp, mail, telephone});
                        response = con.receive();

                        if (response.length != 2) {
                            // TODO: error
                        }
                        switch (Integer.parseInt(response[1])) {
                            case 0:
                                etUsername.setError("Erreur RegisterActivity");
                                break;
                            case 1:
                                startActivity(new Intent(this, MapActivity.class));
                                break;
                            default:
                                // TODO: error inconnue
                                break;
                        }

                        con.closeCon();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        etUsername.setError("Can not connect to server");
                    }
                }
                    break;




        }

    }

}


