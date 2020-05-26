package model.discountStrategy;

import javafx.collections.ObservableList;
import model.Article;

/**
 * @author Jonna J.
 * Discont calculated only for the most expensive item in the basket
 */

public class ExpensiveDiscount extends DiscountStrategy {

    public ExpensiveDiscount(String args) {
        super(args);
    }

    @Override
    public double calculateDiscount(double totalPrice, ObservableList<Article> cart) {
        double max = 0;
        for(Article article : cart){
            if(article.getPrice() > max){
                max = article.getPrice();
            }
        }
        return totalPrice - (max * getPercentage() / 100);
    }
}
