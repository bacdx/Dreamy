package com.example.dreamy.UI.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.example.dreamy.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.maps.ViewAnnotationOptions;
import com.mapbox.maps.extension.style.StyleInterface;
import com.mapbox.maps.plugin.LocationPuck;
import com.mapbox.maps.plugin.delegates.MapDelegateProvider;
import com.mapbox.maps.plugin.locationcomponent.LocationComponentPlugin;
import com.mapbox.maps.plugin.locationcomponent.LocationProvider;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener;
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener;
import com.mapbox.maps.plugin.locationcomponent.PuckLocatedAtPointListener;
import com.mapbox.maps.plugin.locationcomponent.generated.LocationComponentSettings;


import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MapActivity extends AppCompatActivity {
    private String[] permission={"android.permission.ACCESS_COARSE_LOCATION,android.permission.ACCESS_FINE_LOCATION"};
    private Map<View, ViewAnnotationOptions> annotation;
private MapView mapView;
private MapboxMap mapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION")==PackageManager.PERMISSION_GRANTED&&
                checkSelfPermission("android.permission.ACCESS_FINE_LOCATION")==PackageManager.PERMISSION_GRANTED){

        }else {
            requestPermissions(permission,80);
        }

        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.mapView);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);

        mapboxMap= mapView.getMapboxMap();
        annotation=mapView.getViewAnnotationManager().getAnnotations();
        LocationComponentPlugin locationComponentPlugin=new LocationComponentPlugin() {
            @Override
            public void setLocationProvider(@NonNull LocationProvider locationProvider) {

            }

            @Nullable
            @Override
            public LocationProvider getLocationProvider() {
                return null;
            }

            @Override
            public void addOnIndicatorPositionChangedListener(@NonNull OnIndicatorPositionChangedListener onIndicatorPositionChangedListener) {

            }

            @Override
            public void removeOnIndicatorPositionChangedListener(@NonNull OnIndicatorPositionChangedListener onIndicatorPositionChangedListener) {

            }

            @Override
            public void addOnIndicatorBearingChangedListener(@NonNull OnIndicatorBearingChangedListener onIndicatorBearingChangedListener) {

            }

            @Override
            public void removeOnIndicatorBearingChangedListener(@NonNull OnIndicatorBearingChangedListener onIndicatorBearingChangedListener) {

            }

            @Override
            public void isLocatedAt(@NonNull Point point, @NonNull PuckLocatedAtPointListener puckLocatedAtPointListener) {

            }

            @Override
            public void bind(@NonNull Context context, @Nullable AttributeSet attributeSet, float v) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void initialize() {

            }

            @Override
            public void cleanup() {

            }

            @Override
            public void onDelegateProvider(@NonNull MapDelegateProvider mapDelegateProvider) {

            }

            @Override
            public void onStyleChanged(@NonNull StyleInterface styleInterface) {

            }

            @NonNull
            @Override
            public LocationComponentSettings getSettings() {
                return null;
            }

            @Override
            public void updateSettings(@NonNull Function1<? super LocationComponentSettings, Unit> function1) {

            }

            @Override
            public boolean getEnabled() {
                return false;
            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean getPulsingEnabled() {
                return false;
            }

            @Override
            public void setPulsingEnabled(boolean b) {

            }

            @Override
            public int getPulsingColor() {
                return 0;
            }

            @Override
            public void setPulsingColor(int i) {

            }

            @Override
            public float getPulsingMaxRadius() {
                return 0;
            }

            @Override
            public void setPulsingMaxRadius(float v) {

            }

            @Nullable
            @Override
            public String getLayerAbove() {
                return null;
            }

            @Override
            public void setLayerAbove(@Nullable String s) {

            }

            @Nullable
            @Override
            public String getLayerBelow() {
                return null;
            }

            @Override
            public void setLayerBelow(@Nullable String s) {

            }

            @NonNull
            @Override
            public LocationPuck getLocationPuck() {
                return null;
            }

            @Override
            public void setLocationPuck(@NonNull LocationPuck locationPuck) {

            }
        };


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       switch (requestCode){
           case 80:
               if (grantResults.length > 0 &&
                       grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   // Permission is granted. Continue the action or workflow
                   // in your app.
               }  else {
                   // Explain to the user that the feature is unavailable because
                   // the feature requires a permission that the user has denied.
                   // At the same time, respect the user's decision. Don't link to
                   // system settings in an effort to convince the user to change
                   // their decision.
               } return;

        }
    }

    @Override
    protected void onStart() {
        mapView.onStart();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

}