package controller;

import javafx.collections.ObservableList;
import model.Article;
import model.LoadSaveContext;
import model.ShoppingCart;
import model.ShoppingCartListener;

public class ShoppingCartController {

    private LoadSaveContext context;
    private ShoppingCart cart = new ShoppingCart();


    public ShoppingCartController(LoadSaveContext context) {
        this.context = context;
    }

    public ObservableList<Article> getCartContents() {
        return cart.getContents();
    }

    public void registerObserver(ShoppingCartObserver observer) {
        cart.addListener(new ShoppingCartListener() {
            @Override
            public void cartChanged(ShoppingCart cart) {
                observer.update(cart.getTotalPrice());
            }
        });
    }

    public void addArticle(String codeString) {
        int code = Integer.parseInt(codeString);
        ObservableList<Article> articles = context.load();
        for (Article article : articles) {
            if (code == article.getArticleCode()) {
                Article copy = article.copy();
                copy.setStock(1);
                cart.addArticle(copy);
                return;
            }
        }

    }
}
