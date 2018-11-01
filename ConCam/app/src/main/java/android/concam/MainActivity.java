package android.concam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.contactimgButton).setOnClickListener(this);
        findViewById(R.id.cameraimgButton).setOnClickListener(this);
        findViewById(R.id.gpsimgButton).setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        int x = view.getId();
        if (x == R.id.contactimgButton){
            Intent intent = new Intent(getBaseContext(),contactsActivity.class);
            startActivity(intent);
        } else if (x == R.id.cameraimgButton){
            Intent intent = new Intent(getBaseContext(),cameraActivity.class);
            startActivity(intent);
        } else if (x == R.id.gpsimgButton){
            Intent intent = new Intent(getBaseContext(),gpsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
