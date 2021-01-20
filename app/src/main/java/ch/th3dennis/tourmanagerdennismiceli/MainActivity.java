package ch.th3dennis.tourmanagerdennismiceli;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.th3dennis.tourmanagerdennismiceli.CardView.CardViewModel;
import ch.th3dennis.tourmanagerdennismiceli.CardView.MyAdapter;
import ch.th3dennis.tourmanagerdennismiceli.model.Tour;
import ch.th3dennis.tourmanagerdennismiceli.persistence.AppDatabase;
import ch.th3dennis.tourmanagerdennismiceli.persistence.TourDao;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "ch.th3dennis.tourmanagerdennismiceli.CreateTourActivity.MainActivity";
    private TourDao tourDao;

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;


    /**
     * The Method that gets called, when this Activity is first shown
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tourDao = AppDatabase.getAppDb(getApplicationContext()).getTourDao();

        printDataBase();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateTourActivity();
            }
        });

        //insertDummies();
    }

    /**
     * The Method that gets called, when this Activity is shown first or again
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        //ViewCardHolder
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, getMyList());
        recyclerView.setAdapter(myAdapter);


        TextView kilometersTextView = findViewById(R.id.drivenKilometers);

        List<Tour> tours = tourDao.getAll();

        double drivenKilometers = 0;

        for (Tour tour : tours){
            drivenKilometers += tour.getDistance();
        }


        kilometersTextView.setText(getApplicationContext().getString(R.string.driven_kilometers) + " " + drivenKilometers);
    }

    /**
     * Method to get all Cards from the Database
     * @return
     */
    private ArrayList<CardViewModel> getMyList(){
        ArrayList<CardViewModel> models = new ArrayList<>();

        List<Tour> tours = tourDao.getAll();

        for (Tour tour : tours){
            CardViewModel m = new CardViewModel();
            m.setTitle(tour.getTitle());
            m.setDescription(tour.getDescription());
            m.setImg(R.drawable.motorcycle_picture_square);
            models.add(m);
        }

        return models;
    }


    /**
     * Method to fill in Dummy data
     *
     */
    private void insertDummies(){
        ArrayList<Tour> tours = new ArrayList<>();

        Tour tour = new Tour("Schwyz Tour");
        tour.setDescription("Tour um Schwyz");
        tour.setDistance(60);
        tours.add(tour);


        Tour tour1 = new Tour("Obersee Tour");
        tour1.setDescription("Eine Rundfahrt mit der Yamaha MT-125 um den Obersee");
        tour1.setDistance(30);
        tours.add(tour1);



        Tour tour2 = new Tour("Graubünden Pässe Tour");
        tour2.setDescription("3 Pässe Tour in Graubünden");
        tour2.setDistance(80);
        tours.add(tour2);



        Tour tour3 = new Tour("Deutschland Tour");
        tour3.setDescription("Eine Tour nach München und zurück");
        tour3.setDistance(400);
        tours.add(tour3);



        Tour tour4 = new Tour("Einsiedeln Tour");
        tour4.setDescription("Mit schönem Wetter über die Sattelegg nach Einsiedeln");
        tour4.setDistance(20);
        tours.add(tour4);

        Tour tour5 = new Tour("Zürichsee Tour");
        tour5.setDescription("Eine Tour um den Zürichsee mit schönem warmen Wetter");
        tour5.setDistance(80);
        tours.add(tour5);


        Tour tour6 = new Tour("Furka Pass");
        tour6.setDescription("Eine Tour über den Furka Pass");
        tour6.setDistance(200);
        tours.add(tour6);


        tourDao.insertTours(tours);

    }


    /**
     * Inflates the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Made by Android, when the item is selected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Method to switch to the CreateTourActivity
     */
    private void openCreateTourActivity() {
        Log.i(TAG, "Open the HomeActivity");
        //Change to Home-Activity
        Intent createTourActivityIntent = new Intent(getApplicationContext(), CreateTourActivity.class);
        startActivity(createTourActivityIntent);
    }

    /**
     * Debug method to print out the Database
     */
    private void printDataBase(){
        List<Tour> tourList = tourDao.getAll();

        for (Tour tour : tourList){
            Log.d(TAG, "Printing out the databse");
            Log.i(TAG, "printDataBase: " + tour.toString());
            System.out.println(tour);
        }
    }

}