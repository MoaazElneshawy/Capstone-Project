package com.udacity.moaazfathy.pharaohs.pharaohs.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.moaazfathy.pharaohs.pharaohs.Models.City;
import com.udacity.moaazfathy.pharaohs.pharaohs.R;

import java.util.List;

/**
 * Created by MoaazFathy on 16-Apr-18.
 */

public class CitiesAdapter extends BaseAdapter {

    List<City> cities;
    Context context;

    public CitiesAdapter(List<City> cities, Context context) {
        this.cities = cities;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (cities != null)
            return cities.size();
        else return 0;
    }

    @Override
    public Object getItem(int i) {
        return cities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_city, viewGroup, false);

        TextView city = view.findViewById(R.id.city_name);
        ImageView map = view.findViewById(R.id.city_location);

        city.setText(cities.get(i).getCity());

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:0,0?q=" + cities.get(i).getLatitude() + "," + cities.get(i).getLongitude());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
