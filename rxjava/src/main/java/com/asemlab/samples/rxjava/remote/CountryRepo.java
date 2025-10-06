package com.asemlab.samples.rxjava.remote;

import com.asemlab.samples.rxjava.models.CountryResponseItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;

public class CountryRepo {

    private final CountryService countryService;

    @Inject
    public CountryRepo(CountryService countryService) {
        this.countryService = countryService;
    }

    public Observable<List<CountryResponseItem>> getAllCountriesObservable() {
        return countryService.getAllCountriesObservable();
    }

    public Call<List<CountryResponseItem>> getAllCountries() {
        return countryService.getAllCountries();
    }

}
