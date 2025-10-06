package com.asemlab.samples.rxjava.ui.home;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.asemlab.samples.rxjava.databinding.ItemCountryBinding;
import com.asemlab.samples.rxjava.models.CountryResponseItem;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesVH> {

    private final ArrayList<CountryResponseItem> data;
    private final Consumer<CountryResponseItem> clickListener;

    public CountriesAdapter(List<CountryResponseItem> items, Consumer<CountryResponseItem> listener) {
        data = new ArrayList<>(items);
        clickListener = listener;
    }

    public void updateItems(List<CountryResponseItem> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CountriesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return CountriesVH.inflate(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesVH holder, int position) {
        holder.bind(data.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class CountriesVH extends RecyclerView.ViewHolder {

        private ItemCountryBinding itemView;

        private CountriesVH(ItemCountryBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        static CountriesVH inflate(LayoutInflater inflater, ViewGroup parent) {
            return new CountriesVH(ItemCountryBinding.inflate(inflater, parent, false));
        }

        public void bind(CountryResponseItem item, Consumer<CountryResponseItem> clickListener) {
            itemView.setCountry(item);
            itemView.getRoot().setOnClickListener(v -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    clickListener.accept(item);
                }
            });
        }
    }

}
