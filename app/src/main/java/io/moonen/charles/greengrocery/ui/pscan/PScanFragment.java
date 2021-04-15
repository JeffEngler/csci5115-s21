package io.moonen.charles.greengrocery.ui.pscan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ui.gardenessentials.CustomAdapter;
import io.moonen.charles.greengrocery.ui.gardenessentials.EssentialsViewModel;
import io.moonen.charles.greengrocery.ui.gardenessentials.PlantRow;
import io.moonen.charles.greengrocery.ui.gardenessentials.PurchaseDialog;

public class PScanFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_productscansearch, container, false);
    }
}