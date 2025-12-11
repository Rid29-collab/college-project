package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class SplashAnimationActivity extends AppCompatActivity {

    private ImageView splashImg, appLogo, appName;
    private LottieAnimationView lottie;

    private static final long ANIMATION_DURATION = 900;
    private static final long ANIMATION_DELAY = 4200;
    private static final long SPLASH_DELAY = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen without ActionBar safely
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_animation);

        // -------------------- Bind Views --------------------
        splashImg = findViewById(R.id.splashImg);
        appLogo = findViewById(R.id.appLogo);
        appName = findViewById(R.id.appName);
        lottie = findViewById(R.id.lottie);

        // -------------------- Animations --------------------
        if (splashImg != null) {
            splashImg.animate()
                    .alpha(0f)
                    .setDuration(ANIMATION_DURATION)
                    .setStartDelay(ANIMATION_DELAY)
                    .start();
        }

        if (appLogo != null) {
            appLogo.animate()
                    .translationY(-1800)
                    .setDuration(ANIMATION_DURATION)
                    .setStartDelay(ANIMATION_DELAY)
                    .start();
        }

        if (appName != null) {
            appName.animate()
                    .translationY(1800)
                    .setDuration(ANIMATION_DURATION)
                    .setStartDelay(ANIMATION_DELAY)
                    .start();
        }

        if (lottie != null) {
            lottie.animate()
                    .translationY(1800)
                    .setDuration(ANIMATION_DURATION)
                    .setStartDelay(ANIMATION_DELAY)
                    .start();
        }

        // -------------------- Move to Home Screen --------------------
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashAnimationActivity.this, LoginActivity.class));
            finish();
        }, SPLASH_DELAY);
    }
}



//package com.example.travelbuddy;
//
//import android.animation.ObjectAnimator;
//import android.animation.ValueAnimator;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.animation.LinearInterpolator;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import org.osmdroid.config.Configuration;
//import org.osmdroid.util.GeoPoint;
//import org.osmdroid.views.MapView;
//import org.osmdroid.views.overlay.Marker;
//import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
//
//public class SplashAnimationActivity extends AppCompatActivity {
//
//    private MapView map;
//    private Marker marker;
//
//    private ImageView appLogo, appName;
//
//    private final GeoPoint startPoint = new GeoPoint(19.0760, 72.8777); // Mumbai
//    private final GeoPoint endPoint = new GeoPoint(28.7041, 77.1025); // Delhi
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Configuration.getInstance().setUserAgentValue(getPackageName());
//        setContentView(R.layout.activity_splash_animation);
//
//        map = findViewById(R.id.mapSplash);
//        appLogo = findViewById(R.id.appLogo);
//        appName = findViewById(R.id.appName);
//
//        map.setTileSource(TileSourceFactory.MAPNIK);
//        map.getController().setZoom(5.5);
//        map.getController().setCenter(startPoint);
//
//        marker = new Marker(map);
//        marker.setPosition(startPoint);
//        marker.setTitle("Traveling...");
//        map.getOverlays().add(marker);
//
//        // Old style animation
//        startLogoAnimation();
//
//        // Map animation
//        animateMarker(startPoint, endPoint, 3500);
//
//        new Handler().postDelayed(() -> {
//            startActivity(new Intent(SplashAnimationActivity.this, HomeActivity.class));
//            finish();
//        }, 4500);
//    }
//
//    private void startLogoAnimation() {
//        appLogo.animate()
//                .translationY(-2000)
//                .setDuration(1000)
//                .setStartDelay(3000);
//
//        appName.animate()
//                .translationY(2000)
//                .setDuration(1000)
//                .setStartDelay(3000);
//
//        map.animate()
//                .alpha(0f)
//                .setDuration(1000)
//                .setStartDelay(3000);
//    }
//
//    private void animateMarker(GeoPoint from, GeoPoint to, long duration) {
//        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
//        animator.setDuration(duration);
//        animator.setInterpolator(new LinearInterpolator());
//
//        animator.addUpdateListener(animation -> {
//            float fraction = (float) animation.getAnimatedValue();
//            double lat = from.getLatitude() + (to.getLatitude() - from.getLatitude()) * fraction;
//            double lon = from.getLongitude() + (to.getLongitude() - from.getLongitude()) * fraction;
//            GeoPoint p = new GeoPoint(lat, lon);
//
//            marker.setPosition(p);
//            map.getController().setCenter(p);
//        });
//
//        animator.start();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        map.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        map.onPause();
//    }
//}
