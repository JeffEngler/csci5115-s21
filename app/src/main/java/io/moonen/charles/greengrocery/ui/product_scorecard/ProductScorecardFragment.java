package io.moonen.charles.greengrocery.ui.product_scorecard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

import io.moonen.charles.greengrocery.ReceiptContentManagement.Grade;
import io.moonen.charles.greengrocery.MainActivity;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;
import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;

//PRODUCT SCORECARD FRAGMENT
public class ProductScorecardFragment extends Fragment {
    //position of product that was clicked on
    private int productPosition; //1
    //number of scanned receipt
    private int receipt_num;
    public ProductScorecardFragment() { //int scanned_receipt_num, int currPosition
        productPosition = 1;
        receipt_num = 1;
        //this.receipt_num = scanned_receipt_num;
        //this.productPosition = currPosition;
    }

    public ProductScorecardFragment(int scanned_receipt_num, int currPosition){  //pass in position of clicked product
        this.receipt_num = scanned_receipt_num;
        this.productPosition = currPosition;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product_scorecard, container, false);
        //root.setBackgroundColor(Color.WHITE);

        return root;
    }
    @Override
    public void onViewCreated(View v, Bundle savedInstanceState){
        super.onViewCreated(v, savedInstanceState);
        v.setBackgroundColor(Color.WHITE);
        //get receipt data
        MainActivity activity = (MainActivity) getActivity();
        Receipt receipt = activity.getReceiptData(receipt_num);
        List<Product> productList = receipt.getProducts();

        //test
        //Toast.makeText(v.getContext(), productList.get(productPosition).getName(), Toast.LENGTH_LONG).show();

        //Set SUSTAINABILITY FIELDS (for specific product)
        //locality
        Grade localGrade = productList.get(productPosition).getLocalGrade();
        //description
        TextView localCategory = v.findViewById(R.id.localCategory);
        localCategory.setText(localGrade.getShortDesc());
        //grade
        TextView localGradeView = v.findViewById(R.id.localGrade);
        localGradeView.setText(localGrade.getGrade());

        //seasonality
        Grade seasonGrade = productList.get(productPosition).getSeasonGrade();
        //description
        TextView seasonCategory = v.findViewById(R.id.seasonCategory);
        seasonCategory.setText(seasonGrade.getShortDesc());
        //grade
        TextView seasonGradeView = v.findViewById(R.id.seasonGrade);
        seasonGradeView.setText(seasonGrade.getGrade());

        //emissions
        Grade emissionGrade = productList.get(productPosition).getEmissionGrade();
        //description
        TextView emissionCategory = v.findViewById(R.id.emissionCategory);
        emissionCategory.setText(emissionGrade.getShortDesc());
        //grade
        TextView emissionGradeView = v.findViewById(R.id.emissionGrade);
        emissionGradeView.setText(emissionGrade.getGrade());

        //water
        Grade waterGrade = productList.get(productPosition).getWaterGrade();
        //description
        TextView waterCategory = v.findViewById(R.id.waterCategory);
        waterCategory.setText(waterGrade.getShortDesc());
        //grade
        TextView waterGradeView = v.findViewById(R.id.waterGrade);
        waterGradeView.setText(waterGrade.getGrade());

        //package
        Grade packageGrade = productList.get(productPosition).getPackageGrade();
        //description
        TextView packageCategory = v.findViewById(R.id.packageCategory);
        packageCategory.setText(packageGrade.getShortDesc());
        //grade
        TextView packageGradeView = v.findViewById(R.id.packageGrade);
        packageGradeView.setText(packageGrade.getGrade());

        //SET BOTTOM FIELDS (for specific product)
        //overall sustainability
        Grade sustainGrade = productList.get(productPosition).getSustainGrade();
        TextView sustainGradeView = v.findViewById(R.id.sustainGrade);
        sustainGradeView.setText(sustainGrade.getGrade());
        //overall price
        TextView priceView = v.findViewById(R.id.priceValue);
        priceView.setText("$" + productList.get(productPosition).getPrice());
        //overall quality
        TextView qualityView = v.findViewById(R.id.qualityValue);
        qualityView.setText(productList.get(productPosition).getQuality());
    }
}
