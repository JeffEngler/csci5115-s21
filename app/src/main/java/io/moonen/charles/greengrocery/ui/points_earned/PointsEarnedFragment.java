package io.moonen.charles.greengrocery.ui.points_earned;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import io.moonen.charles.greengrocery.MainActivity;
import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;

//POINTS EARNED FRAGMENT
public class PointsEarnedFragment extends Fragment implements View.OnClickListener {
    //number of scanned receipt
    private int receipt_num;

    public PointsEarnedFragment(){  //pass in number of scanned receipt
        this.receipt_num =  1;
    }

    public PointsEarnedFragment(int scanned_receipt_num){  //pass in number of scanned receipt
        this.receipt_num =  scanned_receipt_num;
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_points_earned, container, false);

        //grow garden button
        Button growButton = (Button) root.findViewById(R.id.growButton);
//        growButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        //get receipt data
        MainActivity activity = (MainActivity) getActivity();
        Receipt receipt = activity.getReceiptData(receipt_num);
        //String pts = receipt.getOverallPoints();
        String pts = "1000";

        //set points value
        TextView pointsEarned = v.findViewById(R.id.pointsEarned);
        pointsEarned.setText("You Earned " + pts + " points!");
    }
    @Override
    public void onClick(View v){
        Fragment fragment = null;
        if(v.getId()==R.id.growButton){
            //test
            Toast.makeText(v.getContext(), "Grow Garden Fragment", Toast.LENGTH_LONG).show();
            //fragment = new **insert grow garden fragment here**
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
