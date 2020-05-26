package model.discountStrategy;

import javafx.collections.ObservableList;
import model.Article;

public abstract class DiscountStrategy {
    private double totalDiscount = 0;
    private double totalAfterDiscount = 0;
    private double percentage = 0;
    private ObservableList<Article> shoppingCart;

    DiscountStrategy(String args){
        String[] values = args.split("/");
        double perc = Double.parseDouble(values[0]);
        setPercentage(perc);
    }

    public abstract void calculateDiscount();

    public double getTotalDiscount(){
        return this.totalDiscount;
    }

    public double getTotalAfterDiscount(){
        return this.totalAfterDiscount;
    }

    public double getPercentage(){
        return this.percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
