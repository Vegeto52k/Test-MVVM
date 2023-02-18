package fr.vegeto52.retrofitnearbysearchtest.ui;

import android.location.Location;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.vegeto52.retrofitnearbysearchtest.data.LocationRepository;
import fr.vegeto52.retrofitnearbysearchtest.data.NearbySearchRepository;
import fr.vegeto52.retrofitnearbysearchtest.model.Restaurant;

/**
 * Created by Vegeto52-PC on 08/02/2023.
 */
public class NearbySearchViewModel extends ViewModel {

        NearbySearchRepository mNearbySearchRepository = new NearbySearchRepository();
        LocationRepository mLocationRepository = new LocationRepository();
        MutableLiveData<List<Restaurant>> mListMutableLiveData;

        MainActivity mMainActivity;

        public NearbySearchViewModel() {

                //mLocationRepository.getLocation();
                Log.d("Verif Search", "For moment, it's ok");
                mLocationRepository.getLocationFromRepo().observeForever(new Observer<Location>() {
                        @Override
                        public void onChanged(Location location) {
                               mNearbySearchRepository.getRestaurantsList(location);
                                Log.d("Verif Search", "For moment, it's ok");
                        }
                });
        }

        public void getListRestaurantName(){

        }
}
