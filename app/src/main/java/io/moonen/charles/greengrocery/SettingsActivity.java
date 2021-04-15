/*
package io.moonen.charles.greengrocery;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import android.widget.Button;
import android.view.View;

import io.moonen.charles.greengrocery.ui.faqs.FaqsFragment;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button faqsButton =  findViewById(R.id.button);
        faqsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FaqsFragment nextFrag = new FaqsFragment();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.settings, nextFrag, "findThisFragment").
                        addToBackStack(null).
                        commit();
            }

        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }
}
*/