package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import model.Article;
import model.LoadSaveContext;
import model.ShoppingCart;

public class ShoppingCartController implements ShoppingCartObserver {

    private LoadSaveContext context;
    private ShoppingCart cart = new ShoppingCart();


    public ShoppingCartController(LoadSaveContext context) {
        this.context = context;
    }

    public ObservableList<Article> getCartContents() {
        return cart.getContents();
    }

    @Override
    public void update(ShoppingCart cart) {

    }

    public void addArticle(String codeString) {
        int code = Integer.parseInt(codeString);
        ObservableList<Article> articles = context.load();
        for (Article article : articles) {
            if (code == article.getArticleCode()) {
                cart.addArticle(article);
                return;
            }
        }

    }
}
