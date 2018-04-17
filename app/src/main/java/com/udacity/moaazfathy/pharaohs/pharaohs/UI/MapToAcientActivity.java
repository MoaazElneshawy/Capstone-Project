package com.udacity.moaazfathy.pharaohs.pharaohs.UI;

import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.udacity.moaazfathy.pharaohs.pharaohs.Adapters.CitiesAdapter;
import com.udacity.moaazfathy.pharaohs.pharaohs.Models.City;
import com.udacity.moaazfathy.pharaohs.pharaohs.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapToAcientActivity extends AppCompatActivity {


    @BindView(R.id.map_constraint_Layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.map_loading_progress_par)
    ProgressBar loading;
    @BindView(R.id.cities_list)
    ListView mCitiesLV;

    ArrayList<City> cities;
    CitiesAdapter adapter;
    private final static String ST_URL = "http://khassmy.com/moaz";
    int x, y;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_to_acient);
        ButterKnife.bind(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        cities = new ArrayList<>();
        loading.setVisibility(View.VISIBLE);
        if (savedInstanceState != null) {
            mCitiesLV.setSelectionFromTop(x, y);
        }
        new CitiesAsyncTask().execute();
        Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.map_snakebar_text), Snackbar.LENGTH_LONG);
        snackbar.show();


    }

    private void getCities(JSONArray response) {
        try {

            for (int i = 0; i < response.length(); i++) {
                City city = new City();
                city.setCity(response.getJSONObject(i).getString("city"));
                city.setLatitude(response.getJSONObject(i).getString("latitude"));
                city.setLongitude(response.getJSONObject(i).getString("longitude"));
                cities.add(city);
            }
            adapter = new CitiesAdapter(cities, MapToAcientActivity.this);
            mCitiesLV.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, cities.get(0).getCity());
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int index = mCitiesLV.getFirstVisiblePosition();
        View v = mCitiesLV.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - mCitiesLV.getPaddingTop());
        outState.putInt("index", index);
        outState.putInt("top", top);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        x = savedInstanceState.getInt("index");
        y = savedInstanceState.getInt("top");
    }


    public class CitiesAsyncTask extends AsyncTask<Void, Void, JSONArray> {

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
//            super.onPostExecute(jsonArray);
            try {

                Log.e("asynctask cities", jsonArray.toString());

                getCities(jsonArray);
            } catch (Exception e) {
                e.printStackTrace();
                Crashlytics.logException(e);

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONArray doInBackground(Void... voids) {
            JSONArray array = null;
            try {
                URL url = new URL(ST_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String value = reader.readLine();
                array = new JSONArray(value);

                Log.e("asynctask cities", value);
            } catch (Exception e) {
                e.printStackTrace();
                Crashlytics.logException(e);

            }
            return array;
        }
    }
}
