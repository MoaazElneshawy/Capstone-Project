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

public class PharaohsAdapter
        extends RecyclerView.Adapter<PharaohsAdapter.PharaohsViewHolder> {

    List<Model> models;
    Context context;

    public PharaohsAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public PharaohsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_pharaohs, parent, false);
        return new PharaohsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PharaohsViewHolder holder, int position) {
        String name = models.get(position).getName();
        if (name.equals("Amenhotep")) {
            holder.image.setImageResource(R.drawable.amenhotep);
        } else if (name.equals("Djoser")) {
            holder.image.setImageResource(R.drawable.djoser);
        } else if (name.equals("Khufu")) {
            holder.image.setImageResource(R.drawable.khufu_statue);
        } else if (name.equals("Thutmose")) {
            holder.image.setImageResource(R.drawable.thutmose);
        } else if (name.equals("Cleopatra")) {
            holder.image.setImageResource(R.drawable.cleopatra);
        } else if (name.equals("Ramses II")) {
            holder.image.setImageResource(R.drawable.ramses2_pylon);
        } else if (name.equals("Ramses III")) {
            holder.image.setImageResource(R.drawable.ramses3_pylon);
        } else if (name.equals("Tutankhamun")) {
            holder.image.setImageResource(R.drawable.tutankhamun_coffin);
        } else if (name.equals("Hatshepsut")) {
            holder.image.setImageResource(R.drawable.hatshepsut);
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

    class PharaohsViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView image;

        public PharaohsViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_pharaohs_name);
            description = itemView.findViewById(R.id.item_pharaohs_bio);
            image = itemView.findViewById(R.id.item_pharaohs_img);
        }
    }
}
