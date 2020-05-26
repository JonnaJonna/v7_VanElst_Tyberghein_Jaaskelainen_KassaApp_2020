package model.discountStrategy;

import javafx.collections.ObservableList;
import model.Article;

/**
 * @author Jonna J.
 * Discount calculated only for items belonging to a certain group
 */
public class GroupDiscount extends DiscountStrategy {
    private String groupName;

    public GroupDiscount(String args) {
        super(args);
        String[] values = args.split("/");
        setGroupName(values[1]);
    }

    @Override
    public double calculateDiscount(double totalPrice, ObservableList<Article> cart) {
        double discount = 0;
        for (Article article : cart) {
            if(article.getArticleGroup().equals(groupName)){
                discount = discount + (article.getPrice() - (article.getPrice() * getPercentage()/100));
            } else{
                discount = discount + article.getPrice();
            }
        }
        return discount;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName;
    }

    public String getGroupName(){
        return this.groupName;
    }
}
