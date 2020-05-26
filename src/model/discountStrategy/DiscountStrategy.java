package model.discountStrategy;

import javafx.collections.ObservableList;
import model.Article;

public abstract class DiscountStrategy {
    private double percentage = 0;

    DiscountStrategy(String args){
        String[] values = args.split("/");
        double perc = Double.parseDouble(values[0]);
        setPercentage(perc);
    }

    public abstract double calculateDiscount(double totalPrice, ObservableList<Article> cart);

    public double getPercentage(){
        return this.percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
