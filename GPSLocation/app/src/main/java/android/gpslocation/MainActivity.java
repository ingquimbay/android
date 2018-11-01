package android.gpslocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnSuccessListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final int LOCATION_PERMISSION_CONSTANT = 5;
    private LocationProvider mLocationClient;
    private FusedLocationProviderClient mFusedLocationClient;
    protected LocationManager locationManager;
    Location auxloc, location;
    private double latitud, longitud, altitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView latiTV = (TextView) findViewById(R.id.latitudTV);
        TextView longTV = (TextView) findViewById(R.id.longitudTV);
        TextView altiTV = (TextView) findViewById(R.id.altitudTV);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            location = getLocation();

            latitud = location.getLatitude();
            longitud = location.getLongitude();
            altitud = location.getAltitude();

            latiTV.append(Double.toString(latitud));
            latiTV.setText(Double.toString(latitud));
            longTV.append(Double.toString(longitud));
            altiTV.append(Double.toString(altitud));

        }
    }

    private void askPermissionLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "I need your position", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_CONSTANT);
        }
    }

    private Location getLocation() {
        final LocationManager locMng = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (locMng != null){


            }
        return auxloc;
    }
}
