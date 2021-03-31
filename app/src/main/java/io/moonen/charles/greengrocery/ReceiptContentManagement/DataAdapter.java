package io.moonen.charles.greengrocery.ReceiptContentManagement;

import java.util.ArrayList;
import java.util.List;

//Class used to convert row of data to a Product object
public class DataAdapter {
    private String[] currRow;

    public DataAdapter(String[] incomingRow){
        this.currRow = incomingRow;
    }

    public Product createProduct(){
        //product and grade lists
        List grades = new ArrayList<>();

        //gor through given row
        //row format:
        //name, brand, location, location grade, in season, in season grade, emissions, emissions grade
        //water consump, water consump grade, packaging, packaging grade, price, quality, overall sustain grade

        //store product name and brand
        String product_name = currRow[0];
        String product_brand= currRow[1];


        //create grade objects
        String[] categories = {"Locality", "Seasonality", "Emissions","Water", "Packaging"};
        int c = 0; //counter for categories
        int endOfGrades = 11; //end of sustainability grades
        for(int i = 2 ; i < endOfGrades; i+=2){
            grades.add(new Grade(categories[c], currRow[i+1], currRow[i]));
            c++; //increment category label
        }
        //add overall sustainability grade
        grades.add(new Grade("Price", currRow[currRow.length-2], ""));

        //store price and quality
        String price = currRow[currRow.length-4];
        String quality = currRow[currRow.length-3];

        //create product
        Product newProduct = new Product(product_name, product_brand, price, quality, grades);

        return newProduct;
    }
}
