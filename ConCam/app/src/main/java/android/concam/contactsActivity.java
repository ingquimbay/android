package android.concam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class contactsActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_READ_CONTACTS = 5;
    private ListView listaContactos;
    private static String [] mProjection;
    private Cursor mContactsCursor;
    private ContactCursor mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        listaContactos = (ListView) findViewById(R.id.listContacts);
        mProjection = new String[] {ContactsContract.Profile._ID,
                                ContactsContract.Profile.DISPLAY_NAME_PRIMARY};
                mCursorAdapter = new ContactCursor(this, null, 0);
        listaContactos.setAdapter(mCursorAdapter);
        askPermission();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){

        }
        loadlistContacts();
    }

    private void loadlistContacts() {
        mContactsCursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                mProjection, null, null, null);
        mCursorAdapter.changeCursor(mContactsCursor);
    }

    public void onRequestPermissionsResults(int requestCode, @NonNull String[] permissions,
                                            @NonNull int[] grantResults){
        switch (requestCode){
            case PERMISSION_REQUEST_READ_CONTACTS:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Acceso a contactos!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Acceso denegado", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void askPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)){
                Toast.makeText(this, "Acceso a Contacts requerido", Toast.LENGTH_LONG).show();
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQUEST_READ_CONTACTS);
        }
    }

}
