package com.udacity.moaazfathy.pharaohs.pharaohs;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by MoaazFathy on 18-Apr-18.
 */

public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

}
