package truckcenter.com.truckcenter.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;


public class TrackerService extends Service {

    private boolean started = false;
    private Handler handler = new Handler();
    LocationManager locationManager;
    Location mLocation;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mLocation == null) {
                mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (mLocation == null) {
                mLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            if (mLocation != null) {
                Toast.makeText(getBaseContext(), mLocation.toString(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "No location", Toast.LENGTH_SHORT).show();
            }
            if(started) {
                start();
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        start();
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        started = false;
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void start() {
        started = true;
        handler.postDelayed(runnable, 5000);
    }

}
