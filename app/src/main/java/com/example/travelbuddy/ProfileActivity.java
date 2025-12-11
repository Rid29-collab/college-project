package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelbuddy.profile.adapters.HistoryAdapter;
import com.example.travelbuddy.model.Place;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvNoHistory;
    private Button btnLogout;
    private RecyclerView rvHistory;
    private HistoryAdapter historyAdapter;
    private List<Place> history = new ArrayList<>();
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNoHistory = findViewById(R.id.tvNoHistory);
        btnLogout = findViewById(R.id.btnLogout);
        rvHistory = findViewById(R.id.rvHistory);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            tvEmail.setText(currentUser.getEmail());

            // Fetch the actual name from Firestore
            db.collection("users").document(currentUser.getUid())
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String name = documentSnapshot.getString("name");
                            tvName.setText(name != null ? name : "No Name");
                        }
                    })
                    .addOnFailureListener(e -> {
                        tvName.setText("No Name");
                    });
        }

        // Setup RecyclerView
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(history);
        rvHistory.setAdapter(historyAdapter);

        // Logout action
        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            finish();
        });

        loadHistory();
    }

    private void loadHistory() {
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) return;

        String uid = currentUser.getUid();

        db.collection("users").document(uid).collection("history")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        history.clear();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            Place place = new Place();

                            place.setName(doc.getString("name") != null ? doc.getString("name") : "Unknown Place");
                            String cat = doc.getString("category");
                            place.setCategories(Collections.singletonList(cat != null ? cat : "Unknown Category"));

                            // Use setLat / setLon instead of setDetails
                            place.setLat(doc.getDouble("lat") != null ? doc.getDouble("lat") : 0.0);
                            place.setLon(doc.getDouble("lon") != null ? doc.getDouble("lon") : 0.0);

                            history.add(place);
                        }

                        if (history.isEmpty()) {
                            tvNoHistory.setVisibility(View.VISIBLE);
                            rvHistory.setVisibility(View.GONE);
                        } else {
                            tvNoHistory.setVisibility(View.GONE);
                            rvHistory.setVisibility(View.VISIBLE);
                        }

                        historyAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Could not load history", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
