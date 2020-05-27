package model.states;

import model.DomainException;
import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class ActiveState extends State{
    ShoppingCart shoppingCart;

    public ActiveState(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
        System.out.println(shoppingCart.getState());}

    public void activate(){
        System.out.println(shoppingCart.getState());
        throw new DomainException("This shopping cart is already active!");
    }

    public void cancel(){
        shoppingCart.setState(shoppingCart.getCancelledState());
        System.out.println(shoppingCart.getState());
    }

    public void placeOnHold(){
        shoppingCart.setState(shoppingCart.getOnHoldState());
        System.out.println(shoppingCart.getState());
    }

    public void finishSale(){
        shoppingCart.setState(shoppingCart.getSoldState());
        System.out.println(shoppingCart.getState());
    }
}
