package model.states;

/**
 * @author Wouter V.E.
 */

public abstract class State {

    public void placeOnHold(){}

    public void activate(){}

    public void cancel(){}

    public void finishSale(){}

    public State getState(){
        return this.getState();
    }
}
