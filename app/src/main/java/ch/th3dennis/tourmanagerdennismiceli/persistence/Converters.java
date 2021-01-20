package ch.th3dennis.tourmanagerdennismiceli.persistence;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Converts the date to a long and back which gets stored in the database
 */
public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
