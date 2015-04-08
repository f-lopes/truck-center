package truckcenter.com.truckcenter.model;

import java.util.Date;

/**
 * Created by martin on 30/03/15.
 */
public class PositionNotification {
    private String driverId;
    private double latitude;
    private double longitude;
    private long date;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
