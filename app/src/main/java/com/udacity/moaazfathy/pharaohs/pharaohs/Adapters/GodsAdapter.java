package com.udacity.moaazfathy.pharaohs.pharaohs.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.moaazfathy.pharaohs.pharaohs.Models.Model;
import com.udacity.moaazfathy.pharaohs.pharaohs.R;

import java.util.List;

/**
 * Created by MoaazFathy on 11-Apr-18.
 */

public class GodsAdapter extends RecyclerView.Adapter<GodsAdapter.GodsViewHolder> {

    List<Model> models;
    Context context;

    public GodsAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public GodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pharaohs, parent, false);
        return new GodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GodsViewHolder holder, int position) {
        String name = models.get(position).getName();
        if (name.equals("Amun")) {
            holder.image.setImageResource(R.drawable.amun);
        } else if (name.equals("Ra")) {
            holder.image.setImageResource(R.drawable.ra);
        } else if (name.equals("Hathor")) {
            holder.image.setImageResource(R.drawable.hathor);
        } else if (name.equals("Horus")) {
            holder.image.setImageResource(R.drawable.horus);
        } else if (name.equals("Isis")) {
            holder.image.setImageResource(R.drawable.isis);
        } else if (name.equals("Maet")) {
            holder.image.setImageResource(R.drawable.maet);
        } else if (name.equals("Osiris")) {
            holder.image.setImageResource(R.drawable.osiris);
        }else if (name.equals("Seth")) {
            holder.image.setImageResource(R.drawable.set);
        }

        holder.name.setText(name);
        holder.description.setText(models.get(position).getDescription());

    }


    @Override
    public int getItemCount() {
        if (models != null)
            return models.size();
        else return 0;
    }

    class GodsViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView image;

        public GodsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_pharaohs_name);
            description = itemView.findViewById(R.id.item_pharaohs_bio);
            image = itemView.findViewById(R.id.item_pharaohs_img);
        }
    }
}
