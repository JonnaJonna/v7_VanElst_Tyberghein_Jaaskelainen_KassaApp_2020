package model.discountStrategy;

import javafx.collections.ObservableList;
import model.Article;

/**
 * @author Jonna J.
 * Discount calculate for whole shopping cart that has value more than thresholdValue
 */
public class ThresholdDiscount extends DiscountStrategy {

    private double thresholdValue;

    public ThresholdDiscount(String args) {
        super(args);
        String[] values = args.split("/");
        setThresholdValue(Double.parseDouble(values[1]));
    }

    @Override
    public double calculateDiscount(double totalPrice, ObservableList<Article> cart) {
        if(totalPrice >= thresholdValue){
            return totalPrice - (totalPrice * getPercentage() /100);
        }
        return totalPrice;
    }

    public void setThresholdValue(double thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public double getThresholdValue(){
        return this.thresholdValue;
    }
}
