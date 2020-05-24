package view.panels;

import controller.ShoppingCartController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Article;
import model.Shop;

/**
 * @author Ruben T
 */

public class CustomerPane extends GridPane {

    public CustomerPane(Shop shop) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        TableView cartView = new TableView(shop.getShoppingCartController().getCartContents());
        this.add(cartView, 0, 1);

        TableColumn<Article, String> colDescription = new TableColumn<Article, String>("Description");
        colDescription.setMinWidth(100);
        colDescription.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
        TableColumn<Article, String> colPrice = new TableColumn<Article, String>("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<Article, String>("price"));
        TableColumn<Article, Integer> colAantal = new TableColumn<Article, Integer>("Aantal");
        colAantal.setMinWidth(100);
        colAantal.setCellValueFactory(new PropertyValueFactory<Article, Integer>("stock"));

        cartView.getColumns().addAll(colDescription, colPrice, colAantal);

        Label info = new Label("De huidige prijs is: €");
        Label bedrag = new Label("0");
        this.add(info, 0, 2);
        this.add(bedrag, 1, 2);

        ObserverTotalPrice observerTotalPrice = new ObserverTotalPrice(bedrag);
        shop.getShoppingCartController().registerObserver(observerTotalPrice);
    }

}
