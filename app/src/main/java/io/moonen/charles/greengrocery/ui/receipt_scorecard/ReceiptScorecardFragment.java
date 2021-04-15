package io.moonen.charles.greengrocery.ui.receipt_scorecard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.moonen.charles.greengrocery.MainActivity;
import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.points_earned.PointsEarnedFragment;

//RECEIPT SCORECARD FRAGMENT
public class ReceiptScorecardFragment extends Fragment implements View.OnClickListener {
    //recyclerview for receipt scorecard
    RecyclerView rvReceiptScorecard;
    //number of scanned receipt
    private int receipt_num;

    public ReceiptScorecardFragment() {receipt_num = 1;}

    public ReceiptScorecardFragment(int scanned_receipt_num){  //pass in number of scanned receipt
        this.receipt_num =  scanned_receipt_num;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receipt_scorecard, container, false);

        //next button
        Button nextButton = (Button) root.findViewById(R.id.receiptScorecardNext);
//        nextButton.setOnClickListener(this);

        return root;
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        v.setBackgroundColor(Color.WHITE);
        //recycler View
        rvReceiptScorecard = (RecyclerView) getView().findViewById(R.id.rvReceiptScorecard);
        rvReceiptScorecard.setHasFixedSize(true);
        rvReceiptScorecard.setLayoutManager(new LinearLayoutManager(getActivity()));

        //get receipt data
        MainActivity activity = (MainActivity) getActivity();
        Receipt receipt = activity.getReceiptData(receipt_num);

        //creating recyclerview adapter for receipt
        RVAdapter_ReceiptScorecard adapter = new RVAdapter_ReceiptScorecard(getActivity(), receipt);
        //setting adapter to recyclerview
        rvReceiptScorecard.setAdapter(adapter);

        //set overall receipt grade
        String overallGrade = receipt.getOverallGrade();
        TextView receiptGrade = v.findViewById(R.id.receiptGrade);
        receiptGrade.setText(overallGrade);
    }
    @Override
    public void onClick(View v){
        Fragment fragment = null;
        if(v.getId()==R.id.receiptScorecardNext){
            fragment = new PointsEarnedFragment(receipt_num);
            replaceFrag(fragment);
        }
    }
    public void replaceFrag(Fragment frag){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

