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
    }

    @Override
    protected void onResume() {
        super.onResume();

        //ViewCardHolder
        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, getMyList());
        recyclerView.setAdapter(myAdapter);

    }

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



    private ArrayList<CardViewModel> getMyDummyList(){
       ArrayList<CardViewModel> models = new ArrayList<>();

       CardViewModel m = new CardViewModel();
       m.setTitle("Test Titel");
       m.setDescription("Dies ist eine Test Beschreibung");
       m.setImg(R.drawable.motorcycle_picture_square);
       models.add(m);

        CardViewModel m1 = new CardViewModel();
        m1.setTitle("Test Titel1");
        m1.setDescription("Dies ist eine Test Beschreibung");
        m1.setImg(R.drawable.motorcycle_picture_square);
        models.add(m1);

        CardViewModel m2 = new CardViewModel();
        m2.setTitle("Test Titel2");
        m2.setDescription("Dies ist eine Test Beschreibung");
        m2.setImg(R.drawable.motorcycle_picture_square);
        models.add(m2);

        CardViewModel m3 = new CardViewModel();
        m3.setTitle("Test Titel3");
        m3.setDescription("Dies ist eine Test Beschreibung");
        m3.setImg(R.drawable.motorcycle_picture_square);
        models.add(m3);

        CardViewModel m4 = new CardViewModel();
        m4.setTitle("Test Titel4");
        m4.setDescription("Dies ist eine Test Beschreibung");
        m4.setImg(R.drawable.motorcycle_picture_square);
        models.add(m4);

        CardViewModel m5 = new CardViewModel();
        m5.setTitle("Test Titel5");
        m5.setDescription("Dies ist eine Test Beschreibung");
        m5.setImg(R.drawable.motorcycle_picture_square);
        models.add(m5);

        CardViewModel m6 = new CardViewModel();
        m6.setTitle("Test Titel6");
        m6.setDescription("Dies ist eine Test Beschreibung");
        m6.setImg(R.drawable.motorcycle_picture_square);
        models.add(m6);

        CardViewModel m7 = new CardViewModel();
        m7.setTitle("Test Titel7");
        m7.setDescription("Dies ist eine Test Beschreibung");
        m7.setImg(R.drawable.motorcycle_picture_square);
        models.add(m7);

        CardViewModel m8 = new CardViewModel();
        m8.setTitle("Test Titel8");
        m8.setDescription("Dies ist eine Test Beschreibung");
        m8.setImg(R.drawable.motorcycle_picture_square);
        models.add(m8);

        CardViewModel m9 = new CardViewModel();
        m9.setTitle("Test Titel9");
        m9.setDescription("Dies ist eine Test Beschreibung");
        m9.setImg(R.drawable.motorcycle_picture_square);
        models.add(m9);


       return models;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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

    private void openCreateTourActivity() {
        Log.i(TAG, "Open the HomeActivity");
        //Change to Home-Activity
        Intent createTourActivityIntent = new Intent(getApplicationContext(), CreateTourActivity.class);
        startActivity(createTourActivityIntent);
    }

    private void printDataBase(){
        List<Tour> tourList = tourDao.getAll();

        for (Tour tour : tourList){
            Log.d(TAG, "Printing out the databse");
            Log.i(TAG, "printDataBase: " + tour.toString());
            System.out.println(tour);
        }
    }

}