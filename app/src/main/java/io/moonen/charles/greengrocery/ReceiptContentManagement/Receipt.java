package io.moonen.charles.greengrocery.ReceiptContentManagement;

import java.util.List;

import io.moonen.charles.greengrocery.ReceiptContentManagement.Product;

//Class to store receipt info
public class Receipt {

    //receipt attributes
    private int number;  //receipt number
    private List<Product> receiptProducts;  //list of products on receipt

    //getting the context and product list with constructor
    public Receipt(int number, List<Product> receiptProducts) {
        this.number = number;
        this.receiptProducts = receiptProducts;
    }
    //get receipt number
    public int getNumber() { return number; }

    //gets product list
    public List<Product> getProducts() { return receiptProducts; }

    //add product to product list
    public void addItem(Product product_to_add){
        receiptProducts.add(product_to_add);
    }
    //remove item from product list
    public void removeItem(int product_position){  //pass in position of product in list
        receiptProducts.remove(product_position);
    }
    //gets overall receipt grade
    public String getOverallGrade() {
        int numProducts = receiptProducts.size();
        //average grades
        double average = 0.0;
        for (int i = 0; i < numProducts; i++){
            double currGradeVal = receiptProducts.get(i).getSustainGrade().getGradeValue();
            average += currGradeVal;
        }
        average = average / numProducts;

        //convert to letter grade
        if (average<=1.8){ return "A";}
        else if (average<=2.6){ return "B";}
        else if (average<=3.4){ return "C";}
        else if (average<4.2){ return "D";}
        else if (average<=5){ return "F";}
        else{ return "G"; } //error
     }

    //gets points earned from receipt
    public String getOverallPoints() {
        int numProducts = receiptProducts.size();
        //add up points
        int points = 0;
        for (int i = 0; i < numProducts; i++) {
            String currGrade = receiptProducts.get(i).getSustainGrade().getGrade();
            switch (currGrade) {
                case "A":
                    points += 10;
                    break;
                case "B":
                    points += 8;
                    break;
                case "C":
                    points += 6;
                    break;
                case "D":
                    points += 3;
                    break;
                case "F":
                    points += 0;
                    break;
                default:
                    points += 0;
                    break;
            }
        }
        return String.valueOf(points);
    }
}

