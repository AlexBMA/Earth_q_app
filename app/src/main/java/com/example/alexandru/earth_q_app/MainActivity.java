package com.example.alexandru.earth_q_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.EarthquakeAdapter;
import model.Earthquake;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list of earthquakes
        List<Earthquake> listEarthquake = new ArrayList<>();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse("2009-12-31");
            date2 = sdf.parse("2010-01-31");

        } catch (ParseException e) {
            e.printStackTrace();
        }


        listEarthquake.add(new Earthquake(4.5, date1, "Roma"));
        listEarthquake.add(new Earthquake(5.6, date2, "Copenhaga"));
        listEarthquake.add(new Earthquake(4.3, date1, "Berlin"));





        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<Earthquake> adapter1 = new EarthquakeAdapter(this, listEarthquake);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter1);
    }
}
