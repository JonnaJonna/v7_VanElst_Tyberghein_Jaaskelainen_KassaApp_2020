package controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import model.Article;
import model.DomainException;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;
import model.shoppingCart.ShoppingCart;
import model.shoppingCart.ShoppingCartListener;
import model.states.State;

import java.util.ArrayList;

/**
 * @author Ruben T.
 */
public class ShoppingCartController {
    private ArrayList<ShoppingCartObserver> observers = new ArrayList<>();

    private LoadSaveContext context;
    private DiscountContext discountContext;
    private ShoppingCart cart;
    private ShoppingCart cartOnHold;

    public ShoppingCartController(LoadSaveContext context, DiscountContext discount){
        this.context = context;
        this.discountContext = discount;
        this.cart = new ShoppingCart(discountContext);
    }

    public ObservableList<Article> getCartContents(){
        return cart.getContents();
    }

    // Find article index
    private int findArticle(ObservableList<Article> articles, Article article) {
        for (int idx = 0 ; idx < articles.size() ; idx++) {
            if (articles.get(idx).getArticleCode() == article.getArticleCode()) {
                return idx;
            }
        }
        return -1;
    }

    // Add an article to the list making sure that identical articles are merged
    private void addArticleCondensed(ObservableList<Article> articles, Article article) {
        int idx = findArticle(articles, article);
        if (idx == -1) {
            articles.add(article.copy());
        } else {
            articles.get(idx).setStock(articles.get(idx).getStock() + 1);
        }
    }

    private void removeArticleCondensed(ObservableList<Article> articles, Article article) {
        int idx = findArticle(articles, article);
        if (idx != -1) {
            if (articles.get(idx).getStock() <= 1) {
                articles.remove(idx);
            } else {
                articles.get(idx).setStock(articles.get(idx).getStock() - 1);
            }
        }
    }

    // Return the articles in the shopping cart so that identical articles are added together
    public ObservableList<Article> getCartContentsCondensed(){
        ObservableList<Article> condensedArticles = FXCollections.observableArrayList(new Callback<Article, Observable[]>() {
            @Override
            public Observable[] call(Article param) {
                return new Observable[]{param.stockProperty()};
            }
        });

        for (Article article : cart.getContents()) {
            addArticleCondensed(condensedArticles, article);
        }

        cart.getContents().addListener(new ListChangeListener<Article>() {
            @Override
            public void onChanged(Change<? extends Article> c) {
                while (c.next()) {
                    for (Article article : c.getAddedSubList()) {
                        addArticleCondensed(condensedArticles, article);
                    }
                    for (Article article : c.getRemoved()) {
                        removeArticleCondensed(condensedArticles, article);
                    }
                }
            }
        });
        return condensedArticles;
    }

    public void putCartOnHold(){
        cart.putCartOnHold();
        this.cartOnHold = cart.getShoppingCart();
        createNewCart();
        registerCartListeners();
    }

    public ObservableList<Article> getCartFromHold(){
        this.cart = cartOnHold;
        return cart.getCartFromHold();
    }

    public void sellCart(){
        cart.sellCart();
        createNewCart();
        registerCartListeners();
    }

    public void cancelCart(){
        while(cart.getContents().size()>0){
            Article toRemove = cart.getContents().get(0);
            removeArticle(toRemove);
        }
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
        int code = article.getArticleCode();
        ObservableList<Article> articles = context.load();
        for (Article a: articles){
            if(code == a.getArticleCode()){
                Article copy = article.copy();
                copy.setStock(1);
                a.setStock(a.getStock()+1);
                cart.removeArticle(copy);
                context.save(articles);
                System.out.println(a.getStock());
                //TODO update product overview
                return true;
            }
        }
        return false;
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
