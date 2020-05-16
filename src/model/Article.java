package model;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Jonna J.
 */

public class Article {
    private int articleCode;
    private String description;
    private String articleGroup;
    private double price;

    // Amount in storage or amount in shopping cart
    private IntegerProperty stock = new SimpleIntegerProperty();

    public Article(int _articleCode, String _description, String _articleGroup, double _price, int _stock){
        setArticleCode(_articleCode);
        setDescription(_description);
        setArticleGroup(_articleGroup);
        setPrice(_price);
        setStock(_stock);
    }

    public IntegerProperty getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public Observable getStockObservable() {
        return stock;
    }

    public int getArticleCode() {
        return articleCode;
    }
    private void setArticleCode(int articleCode) {
        this.articleCode = articleCode;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public String getArticleGroup() {
        return articleGroup;
    }

    private void setArticleGroup(String articleGroup) {
        this.articleGroup = articleGroup;
    }

    public double getPrice() {
        return price;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    public Article copy() {
        return new Article(articleCode, description, articleGroup, price, stock.get());
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleCode=" + articleCode +
                ", description='" + description + '\'' +
                ", articleGroup='" + articleGroup + '\'' +
                ", price=" + price +
                ", stock=" + stock.get() +
                '}';
    }

}
