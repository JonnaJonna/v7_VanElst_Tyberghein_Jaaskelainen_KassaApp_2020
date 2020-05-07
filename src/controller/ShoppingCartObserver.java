package controller;

import model.ShoppingCart;

public interface ShoppingCartObserver {

    void update(ShoppingCart cart);
}
