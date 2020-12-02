package online.miantiao.bean;

import java.util.Date;
import java.util.Objects;

/**
 * @author: miantiao
 * @date: 2020/12/1
 */
public class Point {

    private String latitude;
    private String longitude;
    private Date time;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Point point = (Point) o;
        return Objects.equals(latitude, point.latitude) &&
                Objects.equals(longitude, point.longitude) &&
                Objects.equals(time, point.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude, time);
    }

    @Override
    public String toString() {
        return "Point{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", time=" + time +
                '}';
    }
}
