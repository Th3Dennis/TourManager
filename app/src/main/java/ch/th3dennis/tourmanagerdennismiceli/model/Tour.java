package ch.th3dennis.tourmanagerdennismiceli.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import ch.th3dennis.tourmanagerdennismiceli.persistence.Converters;


@Entity(tableName = "tours")
@TypeConverters(Converters.class)
public class Tour {


    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @NonNull
    private String title;

    private Integer distance;

    private Integer days;

    private String weather;

    private Date date;

    private String description;


    public Tour(@NonNull String title, Integer distance, Integer days, String weather, Date date, String description) {
        this.title = title;
        this.distance = distance;
        this.days = days;
        this.weather = weather;
        this.date = date;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
