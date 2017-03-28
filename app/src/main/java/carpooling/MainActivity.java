package carpooling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.smail.testapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button bLogin, bReg;
    ConnexionClientServer conServer;
    MyLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conServer = new ConnexionClientServer();
        bReg = (Button) findViewById(R.id.bRegisterBegin);
        bLogin = (Button) findViewById(R.id.bLoginBegin);
        bReg.setOnClickListener(this);
        bLogin.setOnClickListener(this);

        locationListener = new MyLocationListener(this.getApplicationContext(), this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bLoginBegin:
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.bRegisterBegin:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.bMap:
                startActivity(new Intent(this, MapActivity.class));
        }
    }
}
