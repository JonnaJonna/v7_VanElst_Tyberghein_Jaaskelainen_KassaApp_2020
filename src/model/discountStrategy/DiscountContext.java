package model.discountStrategy;
import javafx.collections.ObservableList;
import model.Article;

import java.util.ArrayList;
import java.util.List;

public class DiscountContext {
    private DiscountStrategy strategy;

    public DiscountContext(){}

    public DiscountStrategy getStrategy(){return this.strategy;}

    public void setStrategy(DiscountStrategy strategy) { this.strategy = strategy; }

    public double calculateDiscount(double totalPrice, ObservableList<Article> cart){ return strategy.calculateDiscount(totalPrice, cart);}

    public double getPercentage(){
        return strategy.getPercentage();
    }

    public void setPercentage(double percentage) {
        this.strategy.setPercentage(percentage);
    }

    public static List<String> getStrategyList(){
        List<String> list = new ArrayList<>();
        for(DiscountEnum strategy:DiscountEnum.values()){
            list.add(strategy.toString());
        }
        return list;
    }
}
