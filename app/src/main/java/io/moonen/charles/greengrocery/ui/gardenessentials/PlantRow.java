package io.moonen.charles.greengrocery.ui.gardenessentials;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import io.moonen.charles.greengrocery.R;

public class PlantRow {
    private String title;
    private int price;
    private String type;
    private int viewID;
    private boolean purchased;
    private int growth;

    private int sapling; // middle 'sapling' plant
    private int tree;

    private int image; // stores sprout
    private int icon;

    public PlantRow(String title, int price, String type, int viewID, int sprout, int sapling, int tree) {
        this.title = title;
        this.price = price;
        this.type = type;
        this.viewID = viewID;
        this.purchased = false;
        this.growth = 0;

        this.sapling = sapling;
        this.tree = tree;

        this.image = sprout;
        this.icon = tree;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return this.price;
    }

    public int getImage() {
        return this.image;
    }

    public int getIcon() { return this.icon; }

    public String getType() {
        return this.type;
    }

    public int getViewID() { return this.viewID; }

    public boolean isPurchased() { return purchased; }

    public int plantGrowth() { return growth; }

    public boolean fullyGrown() {
        if (growth < 2) return false;
        else return true;
    }

    public void waterPlant() {
        growth++;

        if (growth == 1) {
            this.image = this.sapling;
        }
        else if (growth == 2) {
            this.image = this.tree;
        }
    }

    public void purchase() {
        this.purchased = true;
        this.type = "Water";
        this.icon = R.drawable.wateringcan;
    }

}
