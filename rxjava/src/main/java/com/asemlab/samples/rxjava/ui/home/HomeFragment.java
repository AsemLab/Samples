package com.asemlab.samples.rxjava.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.asemlab.samples.rxjava.databinding.FragmentHomeBinding;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        CountriesAdapter countriesAdapter = new CountriesAdapter(new ArrayList<>(), country -> {
            Navigation.findNavController(binding.getRoot()).navigate(HomeFragmentDirections.actionHomeFragmentToBaseFragment(country));
        });
        binding.listRV.setAdapter(countriesAdapter);

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.homeState.observe(
                getViewLifecycleOwner(),
                state -> {
                    countriesAdapter.updateItems(state.getCountries());
                    if (state.getLoading())
                        binding.loadingIndicator.setVisibility(View.VISIBLE);
                    else
                        binding.loadingIndicator.setVisibility(View.INVISIBLE);

                    binding.error.setText(state.getErrorMessage());

                }
        );
        if (countriesAdapter.getItemCount() == 0)
            homeViewModel.getAllCountriesObservable();
//            homeViewModel.getAllCountries();

        return binding.getRoot();
    }


}
