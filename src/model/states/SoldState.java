package model.states;

import model.DomainException;
import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class SoldState extends State{
    ShoppingCart shoppingCart;

    public SoldState(ShoppingCart shoppingCart){this.shoppingCart = shoppingCart;}

    public void activate(){
        throw new DomainException("This sale was already sold, it can't be activated anymore!");
    }

    public void cancel(){
        throw new DomainException("This sale was already sold, it can't be cancelled anymore!");
    }

    public void placeOnHold(){
        throw new DomainException("This sale was already sold, it can't be put on hold anymore!");
    }

    public void finishSale(){
        throw new DomainException("This sale was already sold!");
    }

    @Override
    public State getState() {
        return super.getState();
    }
}
