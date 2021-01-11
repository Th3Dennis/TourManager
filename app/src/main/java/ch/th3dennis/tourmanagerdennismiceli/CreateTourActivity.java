package ch.th3dennis.tourmanagerdennismiceli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.Date;
import java.util.List;

import ch.th3dennis.tourmanagerdennismiceli.model.Tour;
import ch.th3dennis.tourmanagerdennismiceli.persistence.AppDatabase;
import ch.th3dennis.tourmanagerdennismiceli.persistence.TourDao;

public class CreateTourActivity extends AppCompatActivity {

    private TourDao tourDao;
    private static final String TAG = "ch.th3dennis.tourmanagerdennismicei.CreateTourActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tour);

        tourDao = AppDatabase.getAppDb(getApplicationContext()).getUserDao();



    }

    @Override
    protected void onResume() {
        super.onResume();
        addTestData();
    }

    private void addTestData(){
        //tourDao.insertTour(new Tour("New Tour",56,1, "good", new Date(2020,7,2), "Test Description"));
        //Log.d(TAG, "addTestData: Added a test tour to the database");
        List<Tour> tours = tourDao.getAll();

        for (Tour tour : tours){
            Log.i(TAG, tour.getId() + " " + tour.getTitle());
        }
    }
}