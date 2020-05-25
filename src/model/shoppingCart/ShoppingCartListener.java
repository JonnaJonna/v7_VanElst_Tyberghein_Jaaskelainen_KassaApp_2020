package model.shoppingCart;

import model.shoppingCart.ShoppingCart;

/**
 * @author Ruben T.
 */
public interface ShoppingCartListener {

    void cartChanged(ShoppingCart cart);
}
