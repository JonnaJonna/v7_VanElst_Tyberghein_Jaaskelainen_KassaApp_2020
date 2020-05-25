package model.shoppingCart;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben T
 */

public class ShoppingCart implements Observable {

    private ObservableList<Article> contents = FXCollections.observableArrayList(new Callback<Article, Observable[]>() {
        @Override
        public Observable[] call(Article param) {
            return new Observable[]{param.stockProperty()};
        }
    });
    private List<InvalidationListener> listeners = new ArrayList<>();
    private List<ShoppingCartListener> cartListeners = new ArrayList<>();
    private ObservableList<Article> cartOnHold;

    //TODO, cashier needs all the items listed, only client needs to view the items listed once with updated stock
    public void addArticle(Article article) {
        // Look if the article is already in the shopping cart
        for (Article content : contents) {
            if (content.getArticleCode() == article.getArticleCode()) {
                content.setStock(content.getStock() + article.getStock());
                fireListeners();
                return;
            }
        }

        contents.add(article);
        fireListeners();
    }

    public boolean removeArticle(Article articleSearch){
        for (Article article : contents) {
            if (article.getArticleCode() == articleSearch.getArticleCode()){
                article.setStock(article.getStock()-1);
                if (article.getStock()==0){
                    contents.remove(article);
                }
                fireListeners();
                return true;
            }
        }
        return false;
    }

    public ObservableList<Article> getContents() {
        return contents;
    }

    public void putCartOnHold(){
        cartOnHold = FXCollections.observableArrayList(contents);
        contents.clear();
        fireListeners();
    }

    public ObservableList<Article> getCartFromHold(){
        contents = FXCollections.observableArrayList(cartOnHold);
        fireListeners();
        return contents;
    }

    public void clearCart(){
        contents.clear();
        fireListeners();
    }

    private void fireListeners() {
        for (ShoppingCartListener listener : cartListeners) {
            listener.cartChanged(this);
        }
    }

    public double getTotalPrice() {
        double price = 0;
        for (Article article : contents) {
            price += article.getPrice() * article.getStock();
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
