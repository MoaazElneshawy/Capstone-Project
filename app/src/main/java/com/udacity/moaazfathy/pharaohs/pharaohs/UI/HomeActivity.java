package com.udacity.moaazfathy.pharaohs.pharaohs.UI;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.FirebaseDatabase;
import com.udacity.moaazfathy.pharaohs.pharaohs.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.card_maps)
    CardView mMapsCard;
    @BindView(R.id.card_pyramids)
    CardView mPyramidsCard;
    @BindView(R.id.card_gods)
    CardView mGodsCard;
    @BindView(R.id.card_popular_pharaohs)
    CardView mPharaohsCard;

    @BindView(R.id.home_pyramids_img)
    ImageView mPyramidsIMG;
    @BindView(R.id.home_map_img)
    ImageView mMapIMG;
    @BindView(R.id.home_pharaohs_img)
    ImageView mPharaohsIMG;
    @BindView(R.id.home_god_img)
    ImageView mGodIMG;
    @BindView(R.id.nestedScroll)
    NestedScrollView nestedScroll;

    final static String CURRENT_SCROLLING_STATE = "scrolling";
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            position = savedInstanceState.getInt(CURRENT_SCROLLING_STATE);
            nestedScroll.scrollTo(0, position);
        }

        Fabric.with(this, new Crashlytics());

    }


    @OnClick(R.id.card_maps)
    void goToMaps() {
        moveToMapsActivity();
    }

    private void moveToMapsActivity() {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this,
                mMapIMG, mMapIMG.getTransitionName()).toBundle();
        Intent intent = new Intent(HomeActivity.this, MapToAcientActivity.class);
        startActivity(intent, bundle);
    }

    @OnClick(R.id.card_pyramids)
    void goToPyramids() {
        moveToPyramidsActivity();
    }

    private void moveToPyramidsActivity() {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this,
                mPyramidsIMG, mPyramidsIMG.getTransitionName()).toBundle();
        Intent intent = new Intent(HomeActivity.this, PyramidsActivity.class);
        startActivity(intent, bundle);
    }

    @OnClick(R.id.card_gods)
    void goToGods() {
        moveToGodsActivity();
    }

    private void moveToGodsActivity() {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this,
                mGodIMG, mGodIMG.getTransitionName()).toBundle();
        Intent intent = new Intent(HomeActivity.this, GodsActivity.class);
        startActivity(intent, bundle);
    }

    @OnClick(R.id.card_popular_pharaohs)
    void goToPharaohs() {
        moveToPharaohsActivity();
    }

    private void moveToPharaohsActivity() {
        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this,
                mPharaohsIMG, mPharaohsIMG.getTransitionName()).toBundle();
        Intent intent = new Intent(HomeActivity.this, PharaohsActivity.class);
        startActivity(intent, bundle);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt(CURRENT_SCROLLING_STATE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_SCROLLING_STATE, nestedScroll.getVerticalScrollbarPosition());
    }
}
