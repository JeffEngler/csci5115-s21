package io.moonen.charles.greengrocery.ui.gardenessentials;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.R;

public class EssentialsFragment extends Fragment implements View.OnClickListener, PurchaseDialog.PurchaseDialogListener {

    private List<PlantRow> plants;
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private int points;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        sharedPreferences = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        points = sharedPreferences.getInt("points", 0);
        gson = new Gson();
        String jsonText = sharedPreferences.getString("plants", null);
        plants = Arrays.asList(gson.fromJson(jsonText, PlantRow[].class));

        View root = inflater.inflate(R.layout.fragment_essentials, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.essentials);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Fragment fragment = this;

        adapter = new CustomAdapter(root.getContext(), plants);

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String plantName = plants.get(position).getTitle();
                boolean purchaseable = points >= plants.get(position).getPrice();
                boolean water = plants.get(position).isPurchased();

                Bundle args = new Bundle();
                args.putString("name", plantName);
                args.putInt("position", position);
                args.putBoolean("purchaseable", purchaseable);
                args.putBoolean("water", water);

                PurchaseDialog dialog = new PurchaseDialog();

                dialog.setArguments(args);
                dialog.setTargetFragment(fragment,0);
                dialog.show(getFragmentManager(), "purchase dialog");
            }
        });

        TextView pointsView = root.findViewById(R.id.points);
        pointsView.setText(String.valueOf(points));

        return root;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void sendPurchase(boolean purchase, int position) {

        PlantRow plant = plants.get(position);

        if (plant.isPurchased()) {
            plant.waterPlant();
        }
        else {
            plant.purchase();
        }

        points -= plant.getPrice();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", points);
        String jsonText = gson.toJson(plants);
        editor.putString("plants", jsonText);
        editor.apply();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().detach(this).attach(this).commit();

    }

    @Override
    public void returnToGarden() {
        //FragmentManager fm = getActivity().getSupportFragmentManager();
        //fm.popBackStack();
    }


}
