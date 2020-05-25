package model;

import controller.ShoppingCartController;
import javafx.collections.ObservableList;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;

/**
 * @author Ruben T
 */

public class Shop {

    private ShoppingCartController shoppingCartController;
    private ObservableList<Article> articles;
    private DiscountContext discountContext;

    public Shop(LoadSaveContext loadSaveContext, DiscountContext discountContext) {
        shoppingCartController = new ShoppingCartController(loadSaveContext);
        articles = loadSaveContext.load();
        this.discountContext = discountContext;
    }

    public ShoppingCartController getShoppingCartController() {
        return shoppingCartController;
    }

    public ObservableList<Article> getArticles() {
        return articles;
    }

    public DiscountContext getDiscountContext(){
        return this.discountContext;
    }
}
