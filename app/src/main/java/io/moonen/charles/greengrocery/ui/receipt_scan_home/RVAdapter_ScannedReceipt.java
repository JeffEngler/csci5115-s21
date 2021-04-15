package io.moonen.charles.greengrocery.ui.receipt_scan_home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import io.moonen.charles.greengrocery.R;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.points_earned.PointsEarnedFragment;

//RecycleView Adapter
public class RVAdapter_ScannedReceipt extends RecyclerView.Adapter<RVAdapter_ScannedReceipt.ReceiptViewHolder> {
    private Context mCtx;
    private Receipt receipt;

    //getting the context and receipt list with constructor
    public RVAdapter_ScannedReceipt(Context mCtx, Receipt receipt) {
        this.mCtx = mCtx;
        this.receipt = receipt;
    }
    //code below modified from android hw3
    @Override
    public ReceiptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_receipt_preview, parent, false);

        return new ReceiptViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ReceiptViewHolder holder, int position) {
        //getting the receipt of the specified position
        Product product = receipt.getProducts().get(position);

        //binding the data with the viewholder views
        holder.productName.setText(product.getName());  //product name
    }
    @Override
    public int getItemCount() { return receipt.getProducts().size(); }

    class ReceiptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView productName;
        ImageButton editButton;
        ImageButton deleteButton;

        public ReceiptViewHolder(View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productName);

            editButton = itemView.findViewById(R.id.editButton);
            editButton.setOnClickListener(this);

            deleteButton = itemView.findViewById(R.id.deleteButton);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int currentPosition = getAdapterPosition();  //pass in position of clicked product
            if(v.getId()==R.id.editButton){  //edit item
                //test
                Toast.makeText(v.getContext(), "Edit/Add Receipt Item Fragment", Toast.LENGTH_LONG).show();
                //((FragmentActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                    //.replace(R.id.fragment_container, new **insert edit/add item fragment**(currentPosition))
                    //.commit();
            }
            else if(v.getId()==R.id.deleteButton){  //delete item
                //remove item from list
                receipt.removeItem(currentPosition);
                //notify adapter
                notifyItemRemoved(currentPosition);
            }
        }
    }
}


