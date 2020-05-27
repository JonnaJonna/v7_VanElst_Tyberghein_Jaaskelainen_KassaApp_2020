package model.states;

import model.DomainException;
import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class OnHoldState extends State {
    ShoppingCart shoppingCart;

    public OnHoldState(ShoppingCart shoppingCart){this.shoppingCart = shoppingCart;}

    public void activate(){
        shoppingCart.setState(shoppingCart.getActiveState());
        System.out.println(shoppingCart.getState());
    }

    public void cancel(){
        shoppingCart.setState(shoppingCart.getCancelledState());
        System.out.println(shoppingCart.getState());
    }

    public void placeOnHold(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("This item is already on hold!");
    }

    public void finishSale(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("Reactive the shopping cart in order to finish the sale!");
    }
}
