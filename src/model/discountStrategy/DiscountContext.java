package model.discountStrategy;

import model.loadSaveStrategy.LoadSaveEnum;

import java.util.ArrayList;
import java.util.List;

public class DiscountContext {
    private DiscountStrategy strategy;

    public DiscountContext(){}

    public DiscountStrategy getStrategy(){return this.strategy;}

    public void setStrategy(DiscountStrategy strategy) { this.strategy = strategy; }

    public void calculateDiscount(){ strategy.calculateDiscount();}

    public double getTotalDiscount(){
        return strategy.getTotalDiscount();
    }

    public double getTotalAfterDiscount(){
        return strategy.getTotalAfterDiscount();
    }

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
