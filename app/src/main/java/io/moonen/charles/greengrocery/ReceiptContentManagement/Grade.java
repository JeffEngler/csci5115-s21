package io.moonen.charles.greengrocery.ReceiptContentManagement;

//Class to store a grade for a given category
public class Grade {
    private String category;  //grade category
    private String grade;  // letter grade
    private String short_desc;  //short description of grade reasoning

    public Grade(String category, String grade, String short_desc) {
        this.category = category;
        this.grade = grade;
        this.short_desc = short_desc;
    }
    //returns letter grade
    public String getGrade() { return grade;}
    //returns double value for letter grade
    public double getGradeValue() {
        double gradeVal;
        switch (grade){
            case "A":
                gradeVal = 1.0;
                break;
            case "B":
                gradeVal = 2.0;
                break;
            case "C":
                gradeVal = 3.0;
                break;
            case "D":
                gradeVal = 4.0;
                break;
            case "F":
                gradeVal = 5.0;
                break;
            default:
                gradeVal = 0.0;
                break;
        }
        return gradeVal;
    }
    //returns grade category
    public String getCategory() {
        return category;
    }
    //returns grade description
    public String getShortDesc() {
        return short_desc;
    }
}
