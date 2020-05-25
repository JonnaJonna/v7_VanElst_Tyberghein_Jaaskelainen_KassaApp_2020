package controller;

import javafx.collections.ObservableList;
import model.Article;

/**
 * @author Ruben T.
 */
public interface ShoppingCartObserver {
    void update(double totalPrice, ObservableList<Article> cart);
}
