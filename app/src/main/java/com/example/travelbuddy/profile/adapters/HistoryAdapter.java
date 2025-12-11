package com.example.travelbuddy.profile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.R;
import com.example.travelbuddy.model.Place;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder> {

    private List<Place> list;

    public HistoryAdapter(List<Place> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Place place = list.get(position);

        // Safe name
        String name = place.getName();

        // Coordinates from lat/lon
        String coords = place.getLat() + ", " + place.getLon();

        // Safe categories/kinds
        String kinds = place.getCategories() != null ? place.getCategories() : "No details";

        holder.tvName.setText(name);
        holder.tvKinds.setText(coords + " | " + kinds);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView tvName, tvKinds;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPlaceName);
            tvKinds = itemView.findViewById(R.id.tvPlaceKinds);
        }
    }
}
