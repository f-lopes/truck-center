package truckcenter.com.truckcenter.model;

import java.util.Date;

/**
 * Created by martin on 30/03/15.
 */
public class PositionNotification {
    private int truck_id;
    private double latitude;
    private double longitude;
    private Date date;

    public int getTruck_id() {
        return truck_id;
    }

    public void setTruck_id(int truck_id) {
        this.truck_id = truck_id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
