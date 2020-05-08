package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben T
 */

public class ShoppingCart implements Observable {

    private ObservableList<Article> contents = FXCollections.observableArrayList(new ArrayList<Article>());
    private List<InvalidationListener> listeners = new ArrayList<>();
    private List<ShoppingCartListener> cartListeners = new ArrayList<>();

    public void addArticle(Article article) {
        contents.add(article);
        fireListeners();
    }

    public ObservableList<Article> getContents() {
        return contents;
    }

    private void fireListeners() {
        for (ShoppingCartListener listener : cartListeners) {
            listener.cartChanged(this);
        }
    }

    public double getTotalPrice() {
        double price = 0;
        for (Article article : contents) {
            price += article.getPrice();
        }
        return price;
    }

    public void addListener(ShoppingCartListener listener) {
        cartListeners.add(listener);
    }

    public void removeListener(ShoppingCartListener listener) {
        cartListeners.remove(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

}
