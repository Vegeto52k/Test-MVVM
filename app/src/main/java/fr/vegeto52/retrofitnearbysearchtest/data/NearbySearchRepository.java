package fr.vegeto52.retrofitnearbysearchtest.data;

import android.location.Location;
import android.util.Log;

import java.io.IOException;

import fr.vegeto52.retrofitnearbysearchtest.model.Restaurant;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vegeto52-PC on 08/02/2023.
 */
public class NearbySearchRepository {

    LocationRepository mLocationRepository = new LocationRepository();


    private NearbySearchApi mNearbySearchApi;
    double mCurrentLatitude;
    double mCurrentLongitude;

    String mLatLng;
    int radius = 1500;
    String type = "restaurant";
    String map_key = "AIzaSyArVUpejXwZw7QhmdFpVY9rHai7Y2adWrI";



    public void getRestaurantsList(Location location) {
    //    mLocationRepository.enableMyLocation();

        mCurrentLatitude = location.getLatitude();
        mCurrentLongitude = location.getLongitude();

        mLatLng = "" + mCurrentLatitude + "," + mCurrentLongitude;

        mNearbySearchApi = RetrofitService.getRetrofitInstance().create(NearbySearchApi.class);

        Log.d("Test Liste Restaurants", "Coucou");

        mNearbySearchApi.getObjectRestaurant(mLatLng, radius, type, map_key).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                for (Restaurant.Results results : response.body().getResults()) {
                    Log.d("Test ResponseBody", "onResponse: " + results.getName());
                }
                if (!response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
            }
        });
    }
}
