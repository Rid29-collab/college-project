package com.example.travelbuddy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelbuddy.R;
import com.example.travelbuddy.model.Place;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.Holder> {

    public interface OnPlaceClickListener {
        void onPlaceClick(Place place);
    }

    private List<Place> list;
    private OnPlaceClickListener listener;

    public PlacesAdapter(List<Place> list, OnPlaceClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_place, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder h, int position) {
        Place p = list.get(position);

        h.name.setText(p.getName());
        h.address.setText(p.getFormatted() != null ? p.getFormatted() : p.getCategories());

        h.itemView.setOnClickListener(v -> listener.onPlaceClick(p));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class Holder extends RecyclerView.ViewHolder {
        TextView name, address;

        Holder(View v) {
            super(v);
            name = v.findViewById(R.id.tvPlaceName);
            address = v.findViewById(R.id.tvPlaceKinds);
        }
    }
}



//package com.example.travelbuddy.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.travelbuddy.R;
//import com.example.travelbuddy.model.Place;
//
//import java.util.List;
//
//public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.Holder> {
//
//    // Listener for item click
//    public interface OnPlaceClickListener {
//        void onPlaceClick(Place place);
//    }
//
//    private List<Place> placeList;
//    private OnPlaceClickListener listener;
//
//    public PlacesAdapter(List<Place> placeList, OnPlaceClickListener listener) {
//        this.placeList = placeList;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_place, parent, false);
//        return new Holder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Holder holder, int position) {
//        Place place = placeList.get(position);
//
//        // VALID Place model fields
//        String name = place.getName() != null ? place.getName() : "Unnamed Place";
//        String category = place.getCategories() != null ? place.getCategories() : "No category";
//
//        // Coordinates (safe)
//        double lat = place.getLat();
//        double lon = place.getLon();
//
//        // Display name
//        holder.tvName.setText(name);
//
//        // Show either category or coordinates
//        holder.tvAddress.setText(category + "\nLat: " + lat + " | Lon: " + lon);
//
//        // Handle click
//        holder.itemView.setOnClickListener(v -> {
//            if (listener != null) listener.onPlaceClick(place);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return placeList != null ? placeList.size() : 0;
//    }
//
//    static class Holder extends RecyclerView.ViewHolder {
//        TextView tvName, tvAddress;
//
//        public Holder(@NonNull View itemView) {
//            super(itemView);
//            tvName = itemView.findViewById(R.id.tvPlaceName);
//            tvAddress = itemView.findViewById(R.id.tvPlaceKinds);
//        }
//    }
//}

//package com.example.travelbuddy.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.travelbuddy.R;
//import com.example.travelbuddy.model.Place;
//
//import java.util.List;
//
//public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.Holder> {
//
//    // Listener interface for item clicks
//    public interface OnPlaceClickListener {
//        void onPlaceClick(Place place);
//    }
//
//    private List<Place> placeList;
//    private OnPlaceClickListener listener;
//
//    // Updated constructor with listener
//    public PlacesAdapter(List<Place> placeList, OnPlaceClickListener listener) {
//        this.placeList = placeList;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_place, parent, false);
//        return new Holder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Holder holder, int position) {
//        Place place = placeList.get(position);
//
//        String name = place.getName() != null ? place.getName() : "Unnamed Place";
//        String address = place.getFormatted() != null ? place.getFormatted() :
//                (place.getCategories() != null ? place.getCategories() : "No address");
//
//        holder.tvName.setText(name);
//        holder.tvAddress.setText(address);
//
//        // Handle item click
//        holder.itemView.setOnClickListener(v -> {
//            if (listener != null) listener.onPlaceClick(place);
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return placeList != null ? placeList.size() : 0;
//    }
//
//    static class Holder extends RecyclerView.ViewHolder {
//        TextView tvName, tvAddress;
//
//        public Holder(@NonNull View itemView) {
//            super(itemView);
//            tvName = itemView.findViewById(R.id.tvPlaceName);
//            tvAddress = itemView.findViewById(R.id.tvPlaceKinds);
//        }
//    }
//}
