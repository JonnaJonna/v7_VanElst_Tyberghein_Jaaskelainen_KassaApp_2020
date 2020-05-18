package model;

import controller.ShoppingCartController;
import javafx.collections.ObservableList;

/**
 * @author Ruben T
 */

public class Shop {

    private ShoppingCartController shoppingCartController;
    private ObservableList<Article> articles;

    public Shop(LoadSaveContext loadSaveContext) {
        shoppingCartController = new ShoppingCartController(loadSaveContext);
        articles = loadSaveContext.load();
    }

    public ShoppingCartController getShoppingCartController() {
        return shoppingCartController;
    }

    public ObservableList<Article> getArticles() {
        return articles;
    }
}
