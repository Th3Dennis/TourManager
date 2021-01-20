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

    /**
     * The Method that gets called, when this Activity is first shown
     * @param savedInstanceState
     */
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


    /**
     * Saves all the data typed in to the database
     * @return
     */
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

    /**
     * Converter from String to date
     * @param date used to convert
     * @return {@Link Date}
     * @throws WrongDateFormatException Exception created by Dennis gets thrown
     */
    public Date getDateFromString(String date) throws WrongDateFormatException {
        String[] strings = date.split("\\.");
        if (strings.length != 3) {
            throw new WrongDateFormatException("Wrong Date format used");
        }
        return new Date(Integer.parseInt(strings[2]), Integer.parseInt(strings[1]) - 1, Integer.parseInt(strings[0]));
    }
}