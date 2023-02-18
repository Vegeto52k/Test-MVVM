package fr.vegeto52.retrofitnearbysearchtest.data;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import fr.vegeto52.retrofitnearbysearchtest.model.Restaurant;
import fr.vegeto52.retrofitnearbysearchtest.ui.MainActivity;

/**
 * Created by Vegeto52-PC on 10/02/2023.
 */
public class LocationRepository {

    double mCurrentLatitude;
    double mCurrentLongitude;
    MutableLiveData<Location> mLocationMutableLiveData = new MutableLiveData<>();

    FusedLocationProviderClient mFusedLocationProviderClient;
    MainActivity mMainActivity;
    private static final String TAG = "MainActivity";
    Location mLocation;

    @SuppressLint("MissingPermission")
    public void getLocation() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mMainActivity);
        Log.d("Verif Search", "For moment, it's ok");
        Task<Location> task = mFusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                mLocation = location;
                mCurrentLatitude = mLocation.getLatitude();
                mCurrentLongitude = mLocation.getLongitude();

                mLocationMutableLiveData.setValue(mLocation);

                Log.d("Verif getLocation", "Objectif Atteint");
            }
        });
    }

    public MutableLiveData<Location> getLocationFromRepo() {
        return mLocationMutableLiveData;
    }

}
