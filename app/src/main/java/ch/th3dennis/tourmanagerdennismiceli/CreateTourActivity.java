package ch.th3dennis.tourmanagerdennismiceli;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.List;

import ch.th3dennis.tourmanagerdennismiceli.exception.WrongDateFormatException;
import ch.th3dennis.tourmanagerdennismiceli.model.Tour;
import ch.th3dennis.tourmanagerdennismiceli.persistence.AppDatabase;
import ch.th3dennis.tourmanagerdennismiceli.persistence.TourDao;

public class CreateTourActivity extends AppCompatActivity {

    private static final String TAG = "ch.th3dennis.tourmanagerdennismiceli.CreateTourActivity";
    private TourDao tourDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tour);

        tourDao = AppDatabase.getAppDb(getApplicationContext()).getTourDao();

        Button saveButton = findViewById(R.id.create_tour_save_button);

        saveButton.setOnClickListener(v -> {
            if (saveData()) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        addTestData();
    }

    private void addTestData() {
        //tourDao.insertTour(new Tour("New Tour",56,1, "good", new Date(2020,7,2), "Test Description"));
        //Log.d(TAG, "addTestData: Added a test tour to the database");
        List<Tour> tours = tourDao.getAll();

        for (Tour tour : tours) {
            Log.i(TAG, tour.getId() + " " + tour.getTitle());
        }
    }

    private boolean saveData() {
        EditText titleEditText = findViewById(R.id.create_tour_titel);
        EditText locationEditText = findViewById(R.id.create_tour_location);
        EditText distanceEditText = findViewById(R.id.create_tour_distance);
        EditText daysEditText = findViewById(R.id.create_tour_days);
        EditText weatherEditText = findViewById(R.id.create_tour_weather);
        EditText descriptionEditText = findViewById(R.id.create_tour_description);
        EditText dateEditText = findViewById(R.id.create_tour_date);

        String title = titleEditText.getText().toString();
        String location = locationEditText.getText().toString();
        Integer distance = null;
        Integer days = null;
        String weather = weatherEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        Date date = null;

        try {
            distance = Integer.parseInt(distanceEditText.getText().toString());
            days = Integer.parseInt(daysEditText.getText().toString());
            date = getDateFromString(dateEditText.getText().toString());
        } catch (NumberFormatException | WrongDateFormatException e) {
            return false;
        }

        Tour tour = new Tour(title, location, distance, days, weather, date, description);

        tourDao.insertTour(tour);
        return true;
    }

    private Date getDateFromString(String date) throws WrongDateFormatException {
        String[] strings = date.split("\\.");
        if (strings.length != 3) {
            throw new WrongDateFormatException("Wrong Date format used");
        }
        return new Date(Integer.parseInt(strings[2]), Integer.parseInt(strings[1]) - 1, Integer.parseInt(strings[0]));
    }
}