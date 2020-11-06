package com.example.trash2treasure;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.DataSetObserver;
import android.location.LocationManager;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.util.LogPrinter;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;



import java.util.ArrayList;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.trash2treasure.R.string.Categories;
import static com.example.trash2treasure.R.string.Distances;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Map<String, Object> users;
    LocationManager locationManager;
    double myLatitude;
    double myLongitude;
    Context context = this;


    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        try {

        } catch (Exception error) {
            System.out.println(error.toString());
        }
        TextView cateText = findViewById(R.id.cateText);
        cateText.setText("Categories");
        TextView distanceText = findViewById(R.id.distanceText);
        distanceText.setText("Distances");
        //category Spinner
        Spinner cateSpin = (Spinner) findViewById(R.id.spinner);
        //Spinner click listener
        cateSpin.setOnItemSelectedListener(this);

        //Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("  ");
        categories.add("Furniture");
        categories.add("Books");
        categories.add("Electronics");
        categories.add("Clothes");
        categories.add("Others");

        //Creating adapter for spinner
        ArrayAdapter<String> cateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        //Drop down layout style
        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cateSpin.setAdapter(cateAdapter);
        //---------------------
        //Distance Spinner

        Spinner distanceSpin = findViewById(R.id.spinner2);
        distanceSpin.setOnItemSelectedListener(this);
        List<String> distances = new ArrayList<>();
        distances.add("5");
        distances.add("10");
        distances.add("20");
        distances.add("40");

        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, distances);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distanceSpin.setAdapter(distanceAdapter);
        DataBaseHelper db = new DataBaseHelper(this);
        db.onUpgrade(db.getWritableDatabase(),1,2);
        db.addData("40.5259701","-74.4637077","Books",3,"null",0);
        db.addData("40.5247221","-74.4643517","Clothes",2,"Electronics",1);
        db.addData("40.5238671","-74.3712937","Furniture",3,"null",0);
        db.addData("40.5259701","-74.4737077","Books",1,"null",0);
        db.addData("40.5247221","-74.4443517","Clothes",1,"Electronics",1);
        db.addData("40.5238671","-74.3713937","Furniture",1,"null",0);
        String cate = (String) cateSpin.getSelectedItem();
        Log.d("cate",cate);
        myLatitude = 40.5246411;
        myLongitude = -74.4642227;
        List<String> list = new ArrayList<>();
        list.add("Categoies | Qty | Latitude | Longitude ");
        ListView lv = findViewById(R.id.list);

        //button
        Button searchBtn = findViewById(R.id.searchButton);
        searchBtn.setText("Search");
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cate = (String) cateSpin.getSelectedItem().toString();
              //  Log.d("cate",cate);
                String distance = (String) distanceSpin.getSelectedItem().toString();
             //   Log.d("distance",distance);
                Cursor cursor;
                double targetDistance = Double.valueOf(distance);
                if(!cate.trim().isEmpty()){
                    cursor= db.getDataByFilter(cate);
                }else{
                    cursor = db.getData();
                }

                cursor.moveToFirst();
                boolean atLast = cursor.isAfterLast();


                while(cursor.isAfterLast() == false ) {
                    double desLat = cursor.getDouble(1);
                    double desLot = cursor.getDouble(2);
                    String cateName = cursor.getString(3);
                    int cateNum = cursor.getInt(4);
                    Log.d("lat",String.valueOf(desLat));
                    Log.d("l0n",String.valueOf(desLot));
                    double itemDistance = distance(myLatitude,myLongitude,desLat,desLot,"K");
                    if(itemDistance <= targetDistance){
                        list.add(cateName + " | " + cateNum +" | " + desLat + " | " + desLot );
                    }
                    Log.d("List",list.toString());
                    cursor.moveToNext();

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,list);
                lv.setAdapter(adapter);
                lv.setVisibility(View.VISIBLE);


            }


        });




}
    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }




    //TODO get the users with the selected choice
    private ArrayList<Object> getUserByDistance(double location, String cate) {
        return null;
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}