package io.moonen.charles.greengrocery.ui.receipt_scan_home;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.moonen.charles.greengrocery.MainActivity;
import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.receipt_scorecard.ReceiptScorecardFragment;

//RECEIPT SCORECARD FRAGMENT
public class ReceiptScanHomeFragment extends Fragment implements View.OnClickListener {
    //recyclerview for receipts view on scan home
    RecyclerView rvReceiptPreview;
    private int receipt_num;  //number of receipt that was scanned

    public ReceiptScanHomeFragment() {receipt_num = 1;}

    public ReceiptScanHomeFragment(int scanned_receipt_num){  //pass in number of scanned receipt
        receipt_num = scanned_receipt_num;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receipt_scan_home, container, false);

        //scan receipt button
        Button scanReceiptButton = (Button) root.findViewById(R.id.scanReceiptButton);
        scanReceiptButton.setOnClickListener(this);

        //add item to receipt button
        Button addItemButton = (Button) root.findViewById(R.id.addItemButton);
        addItemButton.setOnClickListener(this);

        //score my receipt button
        Button scoreMyReceiptButton = (Button) root.findViewById(R.id.scoreMyReceiptButton);
//        scoreMyReceiptButton.setOnClickListener(this);

        return root;
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        //recycler View
        rvReceiptPreview = (RecyclerView) getView().findViewById(R.id.rvReceiptPreview);
        rvReceiptPreview.setHasFixedSize(true);
        rvReceiptPreview.setLayoutManager(new LinearLayoutManager(getActivity()));

        //get receipt data of scanned receipt
        MainActivity activity = (MainActivity) getActivity();
        Receipt receipt = activity.getReceiptData(receipt_num);

        //creating recyclerview adapter for receipt
        RVAdapter_ScannedReceipt adapter = new RVAdapter_ScannedReceipt(getActivity(), receipt);
        //setting adapter to recyclerview
        rvReceiptPreview.setAdapter(adapter);
    }
    @Override
    public void onClick(View v){
        Fragment fragment = null;
        if(v.getId()==R.id.scoreMyReceiptButton){
            fragment = new ReceiptScorecardFragment(receipt_num);
            replaceFrag(fragment);
        }
        else if(v.getId()==R.id.addItemButton){
            //test
            Toast.makeText(v.getContext(), "Add/Edit Receipt Item Fragment", Toast.LENGTH_LONG).show();
            //fragment = new **insert add item fragment here**;
            //replaceFrag(fragment);
        }
        //TO DO: Fill in
        else if(v.getId()==R.id.scanReceiptButton){
            //test
            Toast.makeText(v.getContext(), "Scan Receipt Fragment", Toast.LENGTH_LONG).show();
            //fragment = new **insert scan receipt fragment here**;
            //replaceFrag(fragment);
        }
    }
    public void replaceFrag(Fragment frag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
