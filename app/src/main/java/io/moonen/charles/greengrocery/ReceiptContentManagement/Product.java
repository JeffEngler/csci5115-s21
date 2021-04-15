package io.moonen.charles.greengrocery.ReceiptContentManagement;

import java.util.List;

import io.moonen.charles.greengrocery.ReceiptContentManagement.Grade;

//Class to store product info
public class Product {
    private String name;  //product name
    private String brand;  //product brand
    private String price;  //product price
    private String quality;  //product quality (star rating)

    //list of product grades
    //includes (in order): locality, seasonality, water, emission, packaging
    //overall sustainability, price & quality grades
    private List<Grade> grades;

    public Product(String name, String brand, String price, String quality, List<Grade> grades) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quality = quality;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }
    public String getBrand() { return brand; }
    public String getPrice() { return price; }
    public String getQuality() {return quality; }

    //individual category grades
    public Grade getLocalGrade() {
        return grades.get(0);
    }
    public Grade getSeasonGrade() {
        return grades.get(1);
    }
    public Grade getEmissionGrade() {
        return grades.get(2);
    }
    public Grade getWaterGrade() {
        return grades.get(3);
    }
    public Grade getPackageGrade() {
        return grades.get(4);
    }
    public Grade getSustainGrade() {
        return grades.get(5);
    }

}
