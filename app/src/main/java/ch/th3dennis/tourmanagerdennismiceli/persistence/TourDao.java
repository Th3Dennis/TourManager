package ch.th3dennis.tourmanagerdennismiceli.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ch.th3dennis.tourmanagerdennismiceli.model.Tour;

/**
 * The Dao, used to access the database
 */
@Dao
public interface TourDao {

    @Query("SELECT * FROM tours")
    List<Tour> getAll();

    @Insert
    void insertTour(Tour tour);

    @Insert
    void insertTours(List<Tour> tours);


    @Query("SELECT * from tours where id = :id")
    Tour getTour(int id);

    @Query("Delete from tours")
    void deleteAll();

}
