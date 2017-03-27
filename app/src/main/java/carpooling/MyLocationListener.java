package carpooling;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class MyLocationListener implements LocationListener
{

    private static final long DISTANCE_CHANGE_FOR_UPDATES = 0; // 10 meters
    private static final long MIN_TIME_BW_UPDATES = 0;  // The min time beetwen updates (milliseconds)


    private static MyLocationListener instance = null;

    private LocationManager locationManager;
    private Location location;
    private boolean locationServiceAvailable;


    private boolean gpsEnabled()
    {
        if (locationManager != null)
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        else
            return false;
    }

    private boolean netEnabled()
    {
        if (locationManager != null)
            return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        else
            return false;
    }

    public Location getLocation()
    {
        return location;
    }

    public boolean serviceAvailable()
    {
        return locationServiceAvailable;
    }


    public static MyLocationListener getLocationManager(Context context, Activity act)
    {
        if (instance == null)
        {
            instance = new MyLocationListener(context, act);
        }
        return instance;
    }


    private MyLocationListener(Context context, Activity act)
    {
        initLocationListener(context, act);
        Log.d("d_GPS", "MyLocationListener created.");
    }

    private void initLocationListener(Context context, Activity act)
    {

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(act, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            //return  ;
            //TODO check if not allowed
        }

        try
        {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            if ((!netEnabled() && !gpsEnabled()) || locationManager == null)
            {
                this.locationServiceAvailable = false;
            } else
            {
                this.locationServiceAvailable = true;

                if (netEnabled())
                {
                    Log.d("d_GPS", "net enabled !");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            DISTANCE_CHANGE_FOR_UPDATES, this);
                    if (locationManager != null)
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        Log.d("d_GPS", " (net) lat =  " + location.getLatitude() + " long = " + location.getLongitude());
                    }
                }

                if (gpsEnabled())
                {
                    if (locationManager != null)
                    {
                        Log.d("d_GPS", "gps enabled !");
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                DISTANCE_CHANGE_FOR_UPDATES, this);


                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        Log.d("d_GPS", " (gps) lat =  " + location.getLatitude() + " long = " + location.getLongitude());
                    } else
                    {
                        Log.d("d_GPS", "locationManager = null");
                    }
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
            Log.d("d_GPS", "(initLocationListerner()) Exception : " + e.getMessage());
        }

    }


    @Override
    public void onLocationChanged(Location location)
    {


        Log.d("d_GPS", "Location changed : (" + location.getLatitude() + "," + location.getLongitude() + ")");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }


}