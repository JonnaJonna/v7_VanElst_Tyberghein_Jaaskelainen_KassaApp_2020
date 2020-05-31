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

    }

    public void activate(){
        throw new DomainException("This shopping cart is already active!");
    }

    public void cancel(){
        shoppingCart.setState(shoppingCart.getCancelledState());
    }

    public void placeOnHold(){
        shoppingCart.setState(shoppingCart.getOnHoldState());
    }

    public void finishSale(){
        shoppingCart.setState(shoppingCart.getSoldState());
    }

    @Override
    public State getState() {
        return super.getState();
    }
}
