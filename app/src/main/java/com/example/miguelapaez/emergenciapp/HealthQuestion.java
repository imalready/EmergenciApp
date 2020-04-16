package com.example.miguelapaez.emergenciapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class HealthQuestion extends AppCompatActivity {
    private double latitudUser = 0;
    private double longitudUser = 0;
    GridLayout mainGrid;
    private FusedLocationProviderClient mFusedLocationClient;
    String email;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_health_question );
        getSupportActionBar ().hide ();
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient ( this );
        email = getIntent ().getStringExtra ( "email" );
        mainGrid = (GridLayout) findViewById ( R.id.gridLayoutQuestion1 );
        if (checkSelfPermission ( Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && checkSelfPermission ( Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation ().addOnSuccessListener (
                this , new OnSuccessListener <Location> () {
                    @Override
                    public void onSuccess(Location location) {
                        Log.i ( "LOCATION" , "onSuccess location" );
                        if (location != null) {
                            longitudUser = location.getLongitude ();
                            latitudUser = location.getLatitude ();
                        }
                    }
                } );
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            LinearLayout cardView = (LinearLayout) mainGrid.getChildAt( i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText ( view.getContext (), "Seleccionaste a: "
                            +finalI, Toast.LENGTH_SHORT).show ();
                    String latUser = String.valueOf(latitudUser);
                    String lonUser = String.valueOf(longitudUser);
                    Intent intent = new Intent ( HealthQuestion.this, MedicalCenters.class);
                    intent.putExtra("info","This is activity from card item index  "+finalI);
                    intent.putExtra("latitud",latUser);
                    intent.putExtra("longitud",lonUser);
                    intent.putExtra("email",email);
                    startActivity(intent);

                }
            });
        }
    }

}
