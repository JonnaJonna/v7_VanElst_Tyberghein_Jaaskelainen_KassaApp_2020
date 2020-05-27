package controller;

import javafx.collections.ObservableList;
import model.Article;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;
import model.shoppingCart.ShoppingCart;
import model.shoppingCart.ShoppingCartListener;

/**
 * @author Ruben T.
 */
public class ShoppingCartController {

    private LoadSaveContext context;
    private DiscountContext discountContext;
    private ShoppingCart cart;


    public ShoppingCartController(LoadSaveContext context, DiscountContext discount) {
        this.context = context;
        this.discountContext = discount;
        this.cart = new ShoppingCart(discountContext);
    }

    public ObservableList<Article> getCartContents() {
        return cart.getContents();
    }

    public void putCartOnHold() {
        cart.putCartOnHold();
    }

    public ObservableList<Article> getCartFromHold() {
        return cart.getCartFromHold();
    }

    public void sellCart() {
        cart.sellCart();
    }

    public void cancelCart(){cart.cancelCart();}

    public void registerObserver(ShoppingCartObserver observer) {
        cart.addListener(new ShoppingCartListener() {
            @Override
            public void cartChanged(ShoppingCart cart) {
                observer.update(cart.getTotalPrice(), getCartContents(), cart.getTotalAfterDiscount());
            }
        });
    }

    public boolean addArticle(String codeString) {
        int code = Integer.parseInt(codeString);
        ObservableList<Article> articles = context.load();
        for (Article article : articles) {
            if (code == article.getArticleCode()) {
                Article copy = article.copy();
                copy.setStock(1);
                cart.addArticle(copy);
                return true;
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
