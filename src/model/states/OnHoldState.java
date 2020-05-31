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
    }

    public void cancel(){
        shoppingCart.setState(shoppingCart.getCancelledState());
    }

    public void placeOnHold(){
        throw new DomainException("This item is already on hold!");
    }

    public void finishSale(){
        throw new DomainException("Reactive the shopping cart in order to finish the sale!");
    }

    @Override
    public State getState() {
        return super.getState();
    }
}
