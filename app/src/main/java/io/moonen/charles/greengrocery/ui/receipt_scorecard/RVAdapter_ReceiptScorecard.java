package io.moonen.charles.greengrocery.ui.receipt_scorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;
import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.product_scorecard.ProductScorecardFragment;

//RecycleView Adapter
public class RVAdapter_ReceiptScorecard extends RecyclerView.Adapter<RVAdapter_ReceiptScorecard.ProductViewHolder> {
    private FragmentActivity mCtx;
    private Receipt receipt;

    //getting the context and product list with constructor
    public RVAdapter_ReceiptScorecard(FragmentActivity mCtx, Receipt receipt) {
        this.mCtx = mCtx;
        this.receipt = receipt;
    }
    //code below modified from android hw3
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_receipt_scorecard, parent, false);

        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Product product = receipt.getProducts().get(position);

        //binding the data with the viewholder views
        holder.productName.setText(product.getName());  //product name
        holder.productGradeButton.setText(String.valueOf(product.getSustainGrade().getGrade()));  //sustainability grade
    }
    @Override
    public int getItemCount() { return receipt.getProducts().size(); }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productName;
        Button productGradeButton;

        public ProductViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);
            productGradeButton = itemView.findViewById(R.id.productGradeButton);
            productGradeButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //test
            //Toast.makeText(v.getContext(), "First Fragment", Toast.LENGTH_LONG).show();
            Fragment fragment = null;
            int currentPosition = getAdapterPosition();  //pass in position of clicked product
            if(v.getId()==R.id.productGradeButton){
                fragment = new ProductScorecardFragment(receipt.getNumber(), currentPosition);
                replaceFrag(fragment);
            }
//            int currentPosition = getAdapterPosition();  //pass in position of clicked product
//            ((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new ProductScorecardFragment(receipt.getNumber(), currentPosition))
//                    .commit();
        }
        public void replaceFrag(Fragment frag){
            FragmentTransaction transaction = mCtx.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, frag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}

