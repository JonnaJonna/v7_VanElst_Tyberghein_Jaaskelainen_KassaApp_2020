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
    private DiscountContext discountContext;
    private LoadSaveContext loadSaveContext;
    private ObservableList<Article> articles;

    public Shop(LoadSaveContext loadSaveContext, DiscountContext discountContext) {
        shoppingCartController = new ShoppingCartController(loadSaveContext, discountContext, this);
        articles = loadSaveContext.load();
        this.discountContext = discountContext;
        this.loadSaveContext = loadSaveContext;
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

    public LoadSaveContext getLoadSaveContext(){return this.loadSaveContext;}

    public void updateArticle(Article newArticle){
        for(int i=0; i<articles.size(); i++){
            if(articles.get(i).getArticleCode() == newArticle.getArticleCode()){
                articles.set(i, newArticle);
            }
        }
    }
}
