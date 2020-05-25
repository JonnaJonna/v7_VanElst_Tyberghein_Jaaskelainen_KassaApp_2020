package model.discountStrategy;

/**
 * @author Jonna J.
 * Discont calculated only for the most expensive item in the basket
 */

public class ExpensiveDiscount extends DiscountStrategy {


    public ExpensiveDiscount(String args) {
        super(args);
    }

    @Override
    public void calculateDiscount() {
        System.out.println("Calculating expensive discount for " + this.getPercentage() + " %");
    }

    public void setValues(double percentage){
        setPercentage(percentage);
    }
}
