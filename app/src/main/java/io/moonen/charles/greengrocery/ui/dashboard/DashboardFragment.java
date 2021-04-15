package io.moonen.charles.greengrocery.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ui.gardenessentials.EssentialsFragment;
import io.moonen.charles.greengrocery.ui.gardenessentials.PlantRow;

// Garden
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private DashboardViewModel dashboardViewModel;
    private ConstraintLayout constraintLayout;
    private List<PlantRow> plants;
    private boolean emptyGarden = true;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        gson = new Gson();
        String jsonText = sharedPreferences.getString("plants", null);
        plants = Arrays.asList(gson.fromJson(jsonText, PlantRow[].class));

        if (emptyGarden) {
            for (PlantRow plant : plants) {
                if (plant.isPurchased()) {
                    emptyGarden = false;
                    TextView emptyGarden = root.findViewById(R.id.noGarden);
                    emptyGarden.setVisibility(View.GONE);

                }
            }
        }

        Button growYourGarden = root.findViewById(R.id.growyourgarden);
        growYourGarden.setOnClickListener(this);

        for (PlantRow plant : plants) {
            ImageView imageView = root.findViewById(plant.getViewID());
            if (plant.isPurchased()) {
                imageView.setVisibility(View.VISIBLE);
            }
            imageView.setImageResource(plant.getImage());
        }

        constraintLayout = root.findViewById(R.id.dashboard);

        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.growyourgarden:

                SharedPreferences.Editor editor = sharedPreferences.edit();
                String jsonText = gson.toJson(plants);
                editor.putString("plants", jsonText);

                editor.apply();

                EssentialsFragment essentials = new EssentialsFragment();
                FragmentTransaction fragmentTransaction = getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.dashboard, essentials);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
        }
    }
}