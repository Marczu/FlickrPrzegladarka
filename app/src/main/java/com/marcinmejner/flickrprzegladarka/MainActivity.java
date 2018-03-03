package com.marcinmejner.flickrprzegladarka;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements GetFlickerJsonData.OnDataAvailable,
        RecyclerItemClickListener.OnRecyclerClickListener {
    FlickrRecyclerViewAdapter flickrRecyclerViewAdapter;

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, this));


        flickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(this, new ArrayList<Photo>());
        recyclerView.setAdapter(flickrRecyclerViewAdapter);

//        GetRawData getRawData = new GetRawData(this);
//        getRawData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=oreo,android&tagmode=any&format=json&nojsoncallback=1");


        Log.d(TAG, "onCreate: ENDS");

    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onPostResume: Starts");
        super.onResume();
        GetFlickerJsonData getFlickerJsonData = new GetFlickerJsonData(this, "https://api.flickr.com/services/feeds/photos_public.gne", "en-us", true);
//        getFlickerJsonData.executeOnSameThread("android, nougat");
        getFlickerJsonData.execute("android, nougat");
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
        Log.d(TAG, "OnDataAvailable: starts");
        if (stats == DownloadStatus.OK) {

            flickrRecyclerViewAdapter.loadNewData(data);

        } else {
            Log.e(TAG, "OnDataAvailable failed with status " + stats);
        }

        Log.d(TAG, "OnDataAvailable: ends");
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: starts");
        Toast.makeText(MainActivity.this, "Normal tap at position "+ position, Toast.LENGTH_LONG).show();


    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts ");
        Toast.makeText(MainActivity.this, "Long tap at position "+ position, Toast.LENGTH_LONG).show();

        
    }
}