package io.moonen.charles.greengrocery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.moonen.charles.greengrocery.ui.gardenessentials.PlantRow;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.moonen.charles.greengrocery.ReceiptContentManagement.CSVFile;
import io.moonen.charles.greengrocery.ReceiptContentManagement.DataAdapter;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;
import io.moonen.charles.greengrocery.ReceiptContentManagement.Receipt;
import io.moonen.charles.greengrocery.ui.product.ProductFragment;
import io.moonen.charles.greengrocery.ui.product_scorecard.ProductScorecardFragment;
import io.moonen.charles.greengrocery.ui.receipt_scorecard.ReceiptScorecardFragment;

public class MainActivity extends AppCompatActivity {
    Receipt receipt;
    NavController rootController;
    List<Receipt> receipts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<PlantRow> plants =  new ArrayList<>(Arrays.asList(
                new PlantRow("Evergreen Tree", 100, "Plant", R.id.evergreenView, R.drawable.evergreen1, R.drawable.evergreen2, R.drawable.evergreen3),
                new PlantRow("Maple Tree", 50,"Plant", R.id.mapleView, R.drawable.maple1, R.drawable.maple2, R.drawable.maple3),
                new PlantRow("Berry Bush", 20, "Plant", R.id.bushView, R.drawable.berrybush1, R.drawable.berrybush2, R.drawable.berrybush3),
                new PlantRow("Tulip", 10, "Plant", R.id.tulipView, R.drawable.tulip1, R.drawable.tulip2, R.drawable.tulip3)
        ));

        SharedPreferences sharedPreferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("points", 50);

        Gson gson = new Gson();
        String jsonText = gson.toJson(plants);
        editor.putString("plants", jsonText);

        editor.apply();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_rscan, R.id.navigation_garden, R.id.navigation_pscan)
//                .build();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        rootController = navController;
        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(navView, navController);
        //get example receipt data from csv file
        InputStream inputStream = getResources().openRawResource(R.raw.fake_database);
        CSVFile csvFile = new CSVFile(inputStream);
        List rowList = csvFile.readFile();

        //initialize receipt list
        receipts = new ArrayList<>();

        //create list of products for example receipt
        List receiptProducts = new ArrayList<>();
        DataAdapter adapter;
        int numRows = rowList.size();
        for (int i = 1; i < numRows; i++){  //skip first row
            adapter = new DataAdapter((String[]) rowList.get(i));
            Product currProduct = adapter.createProduct();
            receiptProducts.add(currProduct);
        }

        //create example receipt and add to receipt list
        int receipt_num = receipts.size() + 1;
        Receipt receipt1 = new Receipt(receipt_num, receiptProducts);
        receipts.add(receipt1);

        //test
        //String testing = ((Product) receiptProducts.get(1)).getName();

//        //ADDED BUTTON FOR RECEIPT PREV./SCORECARD TESTING
//        Button test = findViewById(R.id.test);
//        test.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                //test
//                //Toast.makeText(getApplicationContext(), testing, Toast.LENGTH_LONG).show();
//
//                ReceiptScanHomeFragment frag = new ReceiptScanHomeFragment(receipt_num);
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container,frag);
//                transaction.commit();
//            }
//        });
    }
    //add receipt to receipt list
    public void addReceipt(Receipt receipt){
        receipts.add(receipt);
    }
    //returns given receipt
    public Receipt getReceiptData(int receipt_num){
        return receipts.get(receipt_num - 1); //index of needed receipt
    }

    public void nav_product_scorecard(View view) {
        rootController.navigate(R.id.navigation_product_scorecard);
    }

    public void nav_product_productsearch(View view) {
        rootController.navigate(R.id.navigation_product_productsearch);
    }

    public void nav_faq(View view) {
        rootController.navigate(R.id.navigation_faq);
    }

    public void nav_rscore(View view) {
        rootController.navigate(R.id.navigation_rscore);
    }

    public void nav_garden(View view) {
        rootController.navigate(R.id.navigation_garden);
    }

    public void nav_points_earned(View view) {
        rootController.navigate(R.id.navigation_points_earned);
    }

    //returns list of receipts
    public List<Receipt> getReceiptList(){ return receipts; }
}
