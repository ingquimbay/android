package android.concam;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class cameraActivity extends AppCompatActivity {

    public static final int IMAGE_REQUEST = 10;
    public static final int CAMERA_REQUEST = 20;
    private Bitmap image = null, photo = null;
    private ImageView imgVw;
    private Button selectButton, camButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imgVw = (ImageView) findViewById(R.id.imgCameraView);
        camButton = (Button) findViewById(R.id.cameraImgButton);
        selectButton = (Button) findViewById(R.id.selectImgButton);

        boolean hasPermissionGallery = (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermissionGallery) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    IMAGE_REQUEST);
        } else {
            selectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, IMAGE_REQUEST);
                }
            });
        }

        boolean hasPermissionCamera = (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermissionCamera) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_REQUEST);
        } else {
            camButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST);
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case IMAGE_REQUEST:{
                if (resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        image = BitmapFactory.
                                decodeStream(getContentResolver().openInputStream(imageUri));
                        imgVw.setImageBitmap(image);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            case CAMERA_REQUEST:{
                if (resultCode == RESULT_OK){
                    photo = (Bitmap) data.getExtras().get("data");
                    imgVw.setImageBitmap(photo);
                }

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case IMAGE_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                    //reload my activity with permission granted or use the features what required the permissionf
                } else
                {
                    Toast.makeText(this, "The app was not allowed to get your phone gallery. Hence, " +
                            "it cannot function properly. Please consider granting it this permission",
                            Toast.LENGTH_LONG).show();
                }
            }
            case CAMERA_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                    //reload my activity with permission granted or use the features what required the permission

                } else
                {
                    Toast.makeText(this, "The app was not allowed to get your location. Hence, " +
                            "it cannot function properly. Please consider granting it this permission",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
