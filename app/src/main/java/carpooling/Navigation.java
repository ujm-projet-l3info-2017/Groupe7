package carpooling;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.smail.testapp.R;

public class Navigation  extends AppCompatActivity implements View.OnClickListener {
    public static int Nature;
    Button etConducteur, etVoyageur;

    // Nature = 1 --> Conducteur , Nature = 0 --> Voyageur
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        etConducteur = (Button) findViewById(R.id.bConducteur);
        etVoyageur = (Button) findViewById(R.id.bVoyageur);

        etConducteur.setOnClickListener(this);
        etVoyageur.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConducteur:
                Nature = 1;

                try {
                    startActivity(new Intent(this, MapActivity.class));

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.bVoyageur:
               Nature = 0;

                try {
                    startActivity(new Intent(this, MapActivity.class));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }

}


