package android.concam;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.w3c.dom.Text;

public class gpsActivity extends AppCompatActivity implements LocationListener {

    public static final int PERMISSION_REQUEST_LOCATION = 5;
    private static final int REQUEST_CHECK_SETTINGS = 2;
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView latTV, lonTV, altTV, bolTV;
    private Button saveLocationButton;
    private LocationManager locMan;
    private LocationListener locLis;
    private LocationRequest mLocReq;
    private LocationCallback mLocCallback;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        askPermission();

        latTV = (TextView) findViewById(R.id.lattextView);
        lonTV = (TextView) findViewById(R.id.lontextView);
        altTV = (TextView) findViewById(R.id.alttextView);
        bolTV = (TextView) findViewById(R.id.disttextView);

        locMan = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locMan.getBestProvider(criteria, true);
        Location loc = locMan.getLastKnownLocation(provider);
        if (loc != null) {
            latTV.setText(String.valueOf(loc.getLatitude()));
            lonTV.setText(String.valueOf(loc.getLongitude()));
            altTV.setText(String.valueOf(loc.getAltitude()));
            distance2PlazaBolivar(loc);
        } else Toast.makeText(this, "Unable to get Location", Toast.LENGTH_LONG).show();

        mLocReq = createLocationRequest();

        mLocCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location = locationResult.getLastLocation();
                Log.i("LOCATION", "Location update in the callback: " + location);
                if (locationResult != null) {
                    latTV.setText("Latitude: " + String.valueOf(location.getLatitude()));
                    lonTV.setText("Longitude: " + String.valueOf(location.getLongitude()));
                    altTV.setText("Altitude: " + String.valueOf(location.getAltitude()));
                }
            }
        };
    }

    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000); //tasa de refresco en milisegundos
        mLocationRequest.setFastestInterval(5000); //máxima tasa de refresco
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Acceso a GPS!", Toast.LENGTH_LONG).show();
                    LocationSettingsRequest.Builder builder = new
                    LocationSettingsRequest.Builder().addLocationRequest(mLocReq);
                    SettingsClient client = LocationServices.getSettingsClient(this);
                    Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
                    task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                        @Override
                        public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                            startLocationUpdates(); //Todas las condiciones para recibir localizaciones
                        }
                    });
                    task.addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            int statusCode = ((ApiException) e).getStatusCode();
                            switch (statusCode) {
                                case CommonStatusCodes.RESOLUTION_REQUIRED:
// Location settings are not satisfied, but this can be fixed by showing the user a dialog.
                                    try {// Show the dialog by calling startResolutionForResult(), and check the result in onActivityResult().
                                        ResolvableApiException resolvable = (ResolvableApiException) e;
                                        resolvable.startResolutionForResult(gpsActivity.this, REQUEST_CHECK_SETTINGS);
                                    } catch (IntentSender.SendIntentException sendEx) {
// Ignore the error.
                                    } break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
// Location settings are not satisfied. No way to fix the settings so we won't show the dialog.
                                    break;
                            }
                        }
                    });
                } else {
                    Toast.makeText(this, "Acceso denegado", Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    private void startLocationUpdates() {
    }

    private void askPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Acceso a GPS requerido", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_LOCATION);
        }
    }

    // Plaza de Bolivar 4.6012731,-74.0808802
    public void distance2PlazaBolivar(Location loc) {
        Location pBolivar = new Location(provider);
        pBolivar.setLatitude(4.6012731);
        pBolivar.setLongitude(-74.0808802);
        if (loc != null)
            bolTV.setText(String.valueOf(loc.distanceTo(pBolivar)));
        else
            bolTV.setText("No se puede calcular :(");
    }

    @Override
    public void onLocationChanged(Location loc) {
        latTV.setText(String.valueOf(loc.getLatitude()));
        lonTV.setText(String.valueOf(loc.getLongitude()));
        altTV.setText(String.valueOf(loc.getAltitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
