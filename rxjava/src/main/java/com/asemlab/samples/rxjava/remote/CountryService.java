package com.asemlab.samples.rxjava.remote;

import com.asemlab.samples.rxjava.models.CountryResponseItem;
import java.util.List;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryService {

    // TODO Use Observable from RxJava3 to get the data
    @GET(HttpRoutes.GET_ALL + "?fields=name,flags,region")
    Observable<List<CountryResponseItem>> getAllCountriesObservable();

    // Use Call from retrofit to get get the data
    @GET(HttpRoutes.GET_ALL + "?fields=name,flags,region")
    Call<List<CountryResponseItem>> getAllCountries();

}
