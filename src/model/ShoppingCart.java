package model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben T
 */

public class ShoppingCart implements Observable {

    private ObservableList<Article> contents = FXCollections.observableArrayList(new Callback<Article, Observable[]>() {
        @Override
        public Observable[] call(Article param) {
            return new Observable[]{param.getStockObservable()};
        }
    });
    private List<InvalidationListener> listeners = new ArrayList<>();
    private List<ShoppingCartListener> cartListeners = new ArrayList<>();

    public void addArticle(Article article) {
        // Look if the article is already in the shopping cart
        for (Article content : contents) {
            if (content.getArticleCode() == article.getArticleCode()) {
                content.setStock(content.getStock().get() + article.getStock().get());
                fireListeners();
                return;
            }
        }

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
            price += article.getPrice() * article.getStock().get();
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
