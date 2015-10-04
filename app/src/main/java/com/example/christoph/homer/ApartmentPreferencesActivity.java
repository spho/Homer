package com.example.christoph.homer;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by marcel on 10/4/15.
 */
public class ApartmentPreferencesActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);

    }
}
