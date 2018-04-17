package com.udacity.moaazfathy.pharaohs.pharaohs.UI;

import android.app.Dialog;
import android.content.Context;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.udacity.moaazfathy.pharaohs.pharaohs.Adapters.CitiesAdapter;
import com.udacity.moaazfathy.pharaohs.pharaohs.Models.City;
import com.udacity.moaazfathy.pharaohs.pharaohs.R;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapToAcientActivity extends AppCompatActivity {


    @BindView(R.id.map_constraint_Layout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.map_loading_progress_par)
    ProgressBar loading;
    @BindView(R.id.cities_list)
    ListView mCitiesLV;

    RequestQueue requestQueue;
    ArrayList<City> cities;
    CitiesAdapter adapter;

    int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_to_acient);

        ButterKnife.bind(this);
        cities = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        loading.setVisibility(View.VISIBLE);
        if (savedInstanceState != null) {
            mCitiesLV.setSelectionFromTop(x, y);
        }
        getCities();

        Snackbar snackbar = Snackbar.make(coordinatorLayout, getString(R.string.map_snakebar_text), Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void getCities() {

        JsonArrayRequest objectRequest = new JsonArrayRequest("http://khassmy.com/moaz", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                cities = new ArrayList<>();
                loading.setVisibility(View.GONE);

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


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapToAcientActivity.this, getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                error.printStackTrace();
            }
        });
        requestQueue.add(objectRequest);
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
}
