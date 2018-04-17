package com.udacity.moaazfathy.pharaohs.pharaohs.UI;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacity.moaazfathy.pharaohs.pharaohs.Adapters.GodsAdapter;
import com.udacity.moaazfathy.pharaohs.pharaohs.Models.Model;
import com.udacity.moaazfathy.pharaohs.pharaohs.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GodsActivity extends AppCompatActivity {

    @BindView(R.id.gods_rv)
    RecyclerView godsRV;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ChildEventListener childEventListener;
    GodsAdapter adapter;
    List<Model> models;

    private Parcelable layoutManagerSavedState;
    final static String CURRENT_SCROLLING_STATE = "scrolling";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gods);
        ButterKnife.bind(this);

        models = new ArrayList<>();
        adapter = new GodsAdapter(models, this);
        godsRV.setLayoutManager(new LinearLayoutManager(this));
        godsRV.setAdapter(adapter);

        if (savedInstanceState != null){
            Log.e("saveGodsState",savedInstanceState.toString());
            layoutManagerSavedState = savedInstanceState.getParcelable(CURRENT_SCROLLING_STATE);
            restoreLayoutManagerPosition();
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("gods");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Model model = dataSnapshot.getValue(Model.class);
                models.add(model);
                adapter.notifyDataSetChanged();
                restoreLayoutManagerPosition();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addChildEventListener(childEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        layoutManagerSavedState = savedInstanceState.getParcelable(CURRENT_SCROLLING_STATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_SCROLLING_STATE,godsRV.getLayoutManager().onSaveInstanceState());
    }

    private void restoreLayoutManagerPosition() {
        if (layoutManagerSavedState != null) {
            godsRV.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
        }
    }
}
