package controller;

import javafx.collections.ObservableList;
import model.Article;
import model.shoppingCart.ShoppingCart;

/**
 * @author Ruben T.
 */
public interface ShoppingCartObserver {
    void update(double totalPrice, ShoppingCart cart, double discountPrice);
}
