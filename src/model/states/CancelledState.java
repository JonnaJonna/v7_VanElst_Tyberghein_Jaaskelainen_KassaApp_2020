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
        throw new DomainException("This sale was already cancelled, it can't be activated anymore!");
    }

    public void cancel(){
        throw new DomainException("This sale was already cancelled!");
    }

    public void placeOnHold(){
        throw new DomainException("This sale was already cancelled, it can't be put on hold anymore!");
    }

    public void finishSale(){
        throw new DomainException("This sale was already cancelled, it can't be finished anymore!");
    }

    @Override
    public State getState() {
        return super.getState();
    }
}
