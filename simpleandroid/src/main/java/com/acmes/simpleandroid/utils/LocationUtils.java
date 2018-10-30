package com.acmes.simpleandroid.utils;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by fishyu on 2018/10/30.
 */

public class LocationUtils {


    public static String getLastLocation(Application context) {
        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null && location.hasAltitude()) {
                return toString(location);
            }
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null && location.hasAltitude()) {
                return toString(location);
            }
        }
        return "Nah";
    }


    public static String toString(Location location) {
        return "lon:" + location.getLongitude() + " lat:" + location.getLatitude();
    }


}
