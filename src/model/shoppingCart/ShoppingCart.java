package model.shoppingCart;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import model.Article;
import model.discountStrategy.DiscountContext;
import model.states.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruben T and Jonna J.
 */

public class ShoppingCart implements Observable {

    private ObservableList<Article> contents = FXCollections.observableArrayList(new Callback<Article, Observable[]>() {
        @Override
        public Observable[] call(Article param) {
            return new Observable[]{param.stockProperty()};
        }
    });
    private List<InvalidationListener> listeners = new ArrayList<>();
    private List<ShoppingCartListener> cartListeners = new ArrayList<>();
    private ObservableList<Article> cartOnHold;
    private DiscountContext discount;
    private double totalAfterDiscount = 0;
    private DecimalFormatSymbols dec = DecimalFormatSymbols.getInstance();

    State onHoldState;
    State cancelledState;
    State activeState;
    State soldState;

    State state = null;

    public ShoppingCart(DiscountContext discountContext){
        this.discount = discountContext;
        onHoldState = new OnHoldState(this);
        cancelledState = new CancelledState(this);
        activeState = new ActiveState(this);
        soldState = new SoldState(this);
        state = activeState;
    }

    public void addArticle(Article article) {
        contents.add(article);
        fireListeners();
    }

    public boolean removeArticle(Article articleSearch){
        for (Article article : contents) {
            if (article.getArticleCode() == articleSearch.getArticleCode()){
                article.setStock(1);
                contents.remove(article);
                fireListeners();
                return true;
            }
        }
        return false;
    }

    public ObservableList<Article> getContents() {
        return contents;
    }

    public void putCartOnHold(){
        try{
            state.placeOnHold();
        }catch (Exception e){
            e.printStackTrace();
        }
        cartOnHold = FXCollections.observableArrayList(contents);
        contents.clear();
        fireListeners();
    }

    public ObservableList<Article> getCartFromHold(){
        try{
            state.activate();
        }catch (Exception e){
            e.printStackTrace();
        }
        contents = FXCollections.observableArrayList(cartOnHold);
        fireListeners();
        return contents;
    }

    public void sellCart(){
        try{
            state.finishSale();
        }catch (Exception e){
            e.printStackTrace();
        }
        contents.clear();
        fireListeners();
    }

    public void cancelCart(){
        try{
            state.cancel();
        }catch (Exception e){
            e.printStackTrace();
        }
        fireListeners();
    }

    private void fireListeners() {
        calculateDiscounts();
        for (ShoppingCartListener listener : cartListeners) {
            listener.cartChanged(this);
        }
    }

    public double getTotalPrice() {
        double price = 0;
        for (Article article : contents) {
            price += article.getPrice() * article.getStock();
        }
        dec.setDecimalSeparator('.');
        DecimalFormat decimal = new DecimalFormat("#.##", dec);
        String priceRounded = decimal.format(price);
        return Double.parseDouble(priceRounded);
    }

    public ShoppingCart getShoppingCart(){
        return this;
    }

    public double getTotalAfterDiscount(){
        dec.setDecimalSeparator('.');
        DecimalFormat decimal = new DecimalFormat("#.##", dec);
        String discount = decimal.format(totalAfterDiscount);
        return Double.parseDouble(discount);
    }

    public void calculateDiscounts(){
        this.totalAfterDiscount = discount.calculateDiscount(getTotalPrice(), contents);
    }

    public void addListener(ShoppingCartListener listener) {
        cartListeners.add(listener);
    }

    public void removeListener(ShoppingCartListener listener) {
        cartListeners.remove(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    public void setState(State state){this.state = state;}

    public State getActiveState(){return activeState;}

    public State getOnHoldState(){return onHoldState;}

    public State getCancelledState(){return cancelledState;}

    public State getSoldState(){return soldState;}

    public State getState() {
        return state;
    }
}
