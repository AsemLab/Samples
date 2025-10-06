package com.asemlab.samples.rxjava.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asemlab.samples.rxjava.models.CountryResponseItem;
import com.asemlab.samples.rxjava.remote.ApiResponseCallback;
import com.asemlab.samples.rxjava.remote.CountryRepo;
import com.asemlab.samples.rxjava.ui.models.HomeState;
import com.asemlab.samples.rxjava.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final CountryRepo countryRepo;
    public MutableLiveData<HomeState> homeState = new MutableLiveData<>(new HomeState());

    @Inject
    public HomeViewModel(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    public void getAllCountriesObservable() {

        homeState.postValue(homeState.getValue().setLoading(true));

        // TODO Network call asynchronously
        Disposable disposable = countryRepo.getAllCountriesObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        users -> {
                            homeState.postValue(homeState.getValue().setCountries(users).setLoading(false));
                        },
                        error -> {
                            homeState.postValue(homeState.getValue().setLoading(false).setErrorMessage(error.getMessage()));
                            Log.e("Retrofit", "Error: " + error.getMessage());
                        }
                );

    }

    public void getAllCountries() {

        homeState.postValue(homeState.getValue().setLoading(true));

        NetworkUtils.enqueueWithCallback(countryRepo.getAllCountries(), new ApiResponseCallback<>() {
            @Override
            public void onSuccess(List<CountryResponseItem> response) {
                homeState.postValue(homeState.getValue().setCountries(response).setLoading(false));
            }

            @Override
            public void onError(int responseCode, String message) {
                homeState.postValue(homeState.getValue().setLoading(false).setErrorMessage(responseCode + ", " + message));
                Log.e("Retrofit", "Error: " + responseCode + ", " + message);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Retrofit", "Failure: " + t.getMessage());
                homeState.postValue(homeState.getValue().setLoading(false).setErrorMessage(t.getMessage()));
            }
        });
    }

}
