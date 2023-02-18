package fr.vegeto52.retrofitnearbysearchtest.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;

import fr.vegeto52.retrofitnearbysearchtest.R;
import fr.vegeto52.retrofitnearbysearchtest.data.LocationRepository;
import fr.vegeto52.retrofitnearbysearchtest.data.RetrofitService;
import fr.vegeto52.retrofitnearbysearchtest.data.NearbySearchApi;
import fr.vegeto52.retrofitnearbysearchtest.model.Restaurant;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NearbySearchApi mNearbySearchApi;

    private static final String TAG = "MainActivity";
    FusedLocationProviderClient mFusedLocationProviderClient;
    Location mLocation;
    double mCurrentLatitude;
    double mCurrentLongitude;

    String location;
    int radius = 1500;
    String type = "restaurant";
    String map_key = "AIzaSyArVUpejXwZw7QhmdFpVY9rHai7Y2adWrI";

    TextView mTextView;

    NearbySearchViewModel mNearbySearchViewModel = new NearbySearchViewModel();
    LocationRepository mLocationRepository = new LocationRepository();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.Text);

        Log.d("Verif onCreate", "Success");

        enableMyLocation();
        mNearbySearchViewModel.getListRestaurantName();
    }

//    @SuppressLint("MissingPermission")
//    public void getLocation(){
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//        Task<Location> task = mFusedLocationProviderClient.getLastLocation();
//        task.addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                if (location != null){
//                    mLocation = location;
//                    mCurrentLatitude = mLocation.getLatitude();
//                    mCurrentLongitude = mLocation.getLongitude();
//                    Log.d("Test Location", " " + mCurrentLatitude + " " + mCurrentLongitude);
//                    //getJsonFromRetrofit();
//                    getRestaurantsList();
//                }
//            }
//        });
//    }

    private void enableMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
                mLocationRepository.getLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d(TAG, "onRequestPermissionsResult: called--grantResults.length: " + grantResults.length);
        // Si permission, alors location
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationRepository.getLocation();
            }
        }
    }

//   public void getJsonFromRetrofit() {
//
//        Log.d("Test Location 2", "" + mCurrentLatitude + "," + mCurrentLongitude);
//
//       location = "" + mCurrentLatitude + "," + mCurrentLongitude;
//
//       mNearbySearchApi = RetrofitService.getRetrofitInstance().create(NearbySearchApi.class);
//
//       mNearbySearchApi.getRestaurantsTest(location, radius, type, map_key).enqueue(new Callback<ResponseBody>() {
//           @Override
//           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//               try {
//                   Log.d("Test ResponseBody", "onResponse: " + response.body().string());
//               } catch (IOException e){
//                   e.printStackTrace();
//               }
//               mTextView.setText("coucou");
//           }
//
//           @Override
//           public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//           }
//       });
//    }
//
//    public void getRestaurantsList() {
//
//        location = "" + mCurrentLatitude + "," + mCurrentLongitude;
//
//        mNearbySearchApi = RetrofitService.getRetrofitInstance().create(NearbySearchApi.class);
//
//        Log.d("Test Liste Restaurants", "Coucou");
//
//        mNearbySearchApi.getObjectRestaurant(location, radius, type, map_key).enqueue(new Callback<Restaurant>() {
//            @Override
//            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
//
//                for (Restaurant.Results results : response.body().getResults()) {
//                    Log.d("Test Results", "Super !" + results.getPhotos());
//                }
//                if (!response.isSuccessful()) {
//                    mTextView.setText("Coucou");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Restaurant> call, Throwable t) {
//                mTextView.setText("Zut !");
//            }
//        });
//    }
}