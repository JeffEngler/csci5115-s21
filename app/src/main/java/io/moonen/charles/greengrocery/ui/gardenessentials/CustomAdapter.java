package io.moonen.charles.greengrocery.ui.gardenessentials;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.moonen.charles.greengrocery.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context mCtx;
    private List<PlantRow> plantRowList;
    private OnItemClickListener mListener;
    private SharedPreferences sharedPreferences;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(Context mCtx, List<PlantRow> dataSet) {
        this.mCtx = mCtx;
        this.plantRowList = dataSet;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewPrice;
        Button button;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textViewTitle = (TextView) view.findViewById(R.id.plantTitle);
            textViewPrice = (TextView) view.findViewById(R.id.plantPrice);
            button = (Button) view.findViewById(R.id.plantButton);
            imageView = view.findViewById(R.id.imageView);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!button.isClickable()) {
                        return;
                    }
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(mCtx)
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder viewHolder, int position) {

        PlantRow plant = plantRowList.get(position);

        String description = "Price: " + plant.getPrice() + " pt.";
        viewHolder.textViewTitle.setText(plant.getTitle());
        viewHolder.textViewPrice.setText(description);
        viewHolder.button.setText(plant.getType());

        if (plant.fullyGrown()) {
            viewHolder.button.setClickable(false);
            //viewHolder.button.setBackgroundColor(Color.GRAY);
            viewHolder.button.setTextColor(Color.GRAY);
        }

        viewHolder.imageView.
                setImageDrawable(mCtx.getResources().getDrawable(plant.getIcon()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return plantRowList.size();
    }
}
