package model.states;

import model.DomainException;
import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class CancelledState extends State{
    ShoppingCart shoppingCart;

    public CancelledState(ShoppingCart shoppingCart){this.shoppingCart = shoppingCart;}

    public void activate(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("This sale was already cancelled, it can't be activated anymore!");
    }

    public void cancel(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("This sale was already cancelled!");
    }

    public void placeOnHold(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("This sale was already cancelled, it can't be put on hold anymore!");
    }

    public void finishSale(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("This sale was already cancelled, it can't be finished anymore!");
    }
}
