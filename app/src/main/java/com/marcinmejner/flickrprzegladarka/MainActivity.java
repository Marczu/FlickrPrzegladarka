package com.marcinmejner.flickrprzegladarka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetFlickerJsonData.OnDataAvailable {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        GetRawData getRawData = new GetRawData(this);
//        getRawData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=oreo,android&tagmode=any&format=json&nojsoncallback=1");


        Log.d(TAG, "onCreate: ENDS");

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onPostResume: Starts");
        super.onResume();
        GetFlickerJsonData getFlickerJsonData = new GetFlickerJsonData(this, "https://api.flickr.com/services/feeds/photos_public.gne", "en-us", true);
        getFlickerJsonData.executeOnSameThread("android, nougat");
        Log.d(TAG, "onPostResume: ends");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
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
        Log.d(TAG, "onOptionsItemSelected() returned: returned");
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnDataAvailable(List<Photo> data, DownloadStatus stats) {
        if (stats == DownloadStatus.OK) {
            Log.d(TAG, "OnDataAvailable: Data is : " + data);
        } else {
            Log.e(TAG, "OnDataAvailable failed with status " + stats);
        }

    }


}