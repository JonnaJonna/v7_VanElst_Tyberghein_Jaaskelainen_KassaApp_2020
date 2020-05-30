package controller;

import javafx.collections.ObservableList;
import model.Article;
import model.DomainException;
import model.Shop;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;
import model.shoppingCart.ShoppingCart;
import model.shoppingCart.ShoppingCartListener;
import model.states.State;
import view.panels.ProductOverviewPane;

import java.util.ArrayList;

/**
 * @author Ruben T.
 */
public class ShoppingCartController {
    private ArrayList<ShoppingCartObserver> observers = new ArrayList<>();

    private LoadSaveContext context;
    private DiscountContext discountContext;
    private ShoppingCart cart;

    public ShoppingCartController(LoadSaveContext context, DiscountContext discount){
        this.context = context;
        this.discountContext = discount;
        this.cart = new ShoppingCart(discountContext);
    }

    public ObservableList<Article> getCartContents(){
        return cart.getContents();
    }

    public void putCartOnHold(){
        cart.putCartOnHold();
    }

    public ObservableList<Article> getCartFromHold(){
        return cart.getCartFromHold();
    }

    public void sellCart(){
        cart.sellCart();
        createNewCart();
        registerCartListeners();
    }

    public void cancelCart(){
        cart.cancelCart();
        createNewCart();
        registerCartListeners();
    }

    public State getCartState(){
        return cart.getState();
    }

    public void createNewCart(){
        this.cart = new ShoppingCart(discountContext);
    }

    public void registerCartListeners() {
        for(ShoppingCartObserver observer : observers) {
            cart.addListener(new ShoppingCartListener() {
                @Override
                public void cartChanged(ShoppingCart cart) {
                    System.out.println("Update");
                    observer.update(cart.getTotalPrice(), getCartContents(), cart.getTotalAfterDiscount());
                }
            });
        }
    }

    public void registerCartListener(ShoppingCartObserver observer) {
            cart.addListener(new ShoppingCartListener() {
                @Override
                public void cartChanged(ShoppingCart cart) {
                    System.out.println("Update");
                    observer.update(cart.getTotalPrice(), getCartContents(), cart.getTotalAfterDiscount());
                }
            });
    }
    /*
    public void removeListener() {
        for(ShoppingCartObserver observer : observers){
            cart.removeListener(observer);
        }
    } */

    public ArrayList<ShoppingCartObserver> getObservers(){
        return this.observers;
    }
    //name addObserver
    public void addObserver(ShoppingCartObserver observer){
        registerCartListener(observer);
        observers.add(observer);
    }

    public boolean addArticle(String codeString) {
        int code = Integer.parseInt(codeString);
        ObservableList<Article> articles = context.load();
        for (Article article : articles) {
            if (code == article.getArticleCode() && article.getStock()>0) {
                Article copy = article.copy();
                copy.setStock(1);
                article.setStock(article.getStock()-1);
                cart.addArticle(copy);
                context.save(articles);
                System.out.println(article.getStock());
                //TODO update product overview
                return true;
            }
            else if(code == article.getArticleCode()){
                throw new DomainException("STOCK IS EMPTY!");
            }
        }
        return false;
    }

    public boolean removeArticle(Article article){
        return cart.removeArticle(article);
    }

    public double getArticlePrice(String codeString){
        int code = Integer.parseInt(codeString);
        ObservableList<Article> articles = context.load();
        for(Article a: articles){
            if(code == a.getArticleCode()){
                return a.getPrice();
            }
        }
        return 0;
    }
}
