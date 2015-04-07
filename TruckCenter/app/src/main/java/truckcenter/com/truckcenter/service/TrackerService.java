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
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import truckcenter.com.truckcenter.model.PositionNotification;


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
                // TODO set real truck id
                PositionNotification position = new PositionNotification();
                position.setDriverId(String.valueOf(1));
                position.setLatitude(mLocation.getLatitude());
                position.setLongitude(mLocation.getLongitude());
                position.setDate(Calendar.getInstance().getTime().getTime());
                new PostPositionTask(getBaseContext()).execute(position);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
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
