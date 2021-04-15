package io.moonen.charles.greengrocery.ui.faqs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import io.moonen.charles.greengrocery.R;


public class FaqsFragment extends Fragment {

    public FaqsFragment(){
        super(R.layout.fragment_faqs);
    }

    public View onCreate(@NonNull LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_faqs, container,false);
        return view;
    }


}
