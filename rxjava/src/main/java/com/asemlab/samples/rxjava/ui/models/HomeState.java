package com.asemlab.samples.rxjava.ui.models;

import com.asemlab.samples.rxjava.models.CountryResponseItem;

import java.util.Collections;
import java.util.List;

public class HomeState {

    private List<CountryResponseItem> countries;
    private Boolean isLoading;
    private String errorMessage;

    public HomeState() {
        countries = Collections.emptyList();
        isLoading = false;
        errorMessage = "";
    }

    public HomeState(List<CountryResponseItem> list, Boolean isLoading, String errorMessage) {
        countries = list;
        this.isLoading = isLoading;
        this.errorMessage = errorMessage;
    }

    public List<CountryResponseItem> getCountries() {
        return countries;
    }

    public HomeState setCountries(List<CountryResponseItem> countries) {
        this.countries = countries;
        return this;
    }

    public Boolean getLoading() {
        return isLoading;
    }

    public HomeState setLoading(Boolean loading) {
        isLoading = loading;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HomeState setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
