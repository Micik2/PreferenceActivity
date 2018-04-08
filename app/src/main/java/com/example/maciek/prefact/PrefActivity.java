package com.example.maciek.prefact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragment()).commit();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public static class PrefFragment extends PreferenceFragment {
        private ListPreference listPreference;
        private SharedPreferences sharedPref;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            PreferenceManager.setDefaultValues(getActivity(), R.xml.preferences, false);

            addPreferencesFromResource(R.xml.preferences);

            sharedPref = this.getActivity().getSharedPreferences("Jednostka", Context.MODE_PRIVATE);

            listPreference = (ListPreference) findPreference("changeUnit");
            listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    SharedPreferences.Editor preferenceEditor = sharedPref.edit();
                    preferenceEditor.putString("Jednostka", (String) newValue);
                    preferenceEditor.commit();
                    //Log.d("Unit", (String) newValue);
                    return false;
                }
            });
        }
    }
}