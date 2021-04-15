package io.moonen.charles.greengrocery.ui.gardenessentials;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.navigation.Navigation;

import java.util.Objects;

import io.moonen.charles.greengrocery.MainActivity;
import io.moonen.charles.greengrocery.R;

public class PurchaseDialog extends AppCompatDialogFragment {

    private PurchaseDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Bundle mArgs = getArguments();
        String name = mArgs.getString("name");
        int position = mArgs.getInt("position");
        boolean purchase = mArgs.getBoolean("purchaseable");
        boolean water = mArgs.getBoolean("water");

        AlertDialog.Builder error = new AlertDialog.Builder(getActivity());
        error.setTitle("Purchase Not Completed")
                .setMessage("You do not have enough points to make this purchase." +
                        "\n\nMake more sustainable receipt scans or choose a different item.")
                .setPositiveButton("Return to Garden Essentials", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        AlertDialog.Builder completed = new AlertDialog.Builder(getActivity());
        completed.setTitle("Purchase Completed!")
                .setMessage("Thank you for your purchase! Continue making sustainable choices to earn more points!")
                .setPositiveButton("Return to Garden Essentials", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.returnToGarden();
                    }
                });


        String message = "Would you like to ";
        if (water) {
            message += "water ";
        }
        else {
            message += "purchase ";
        }
        message += (name + "?");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Garden Essentials")
                .setMessage(message)
                .setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!purchase) {
                            error.create();
                            error.show();
                        }
                        else {
                            completed.create();
                            completed.show();
                            listener.sendPurchase(true, position);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = ((PurchaseDialogListener) getTargetFragment());
        }
        catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Must implement PurchaseDialogListener");
        }

    }

    public interface PurchaseDialogListener {
        void sendPurchase(boolean purchase, int position);
        void returnToGarden();
    }
}
