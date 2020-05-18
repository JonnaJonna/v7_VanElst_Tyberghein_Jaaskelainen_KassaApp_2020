package view.panels;

import controller.ShoppingCartController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Article;
import model.LoadSaveContext;

/**
 * @author Ruben T
 */

public class ShoppingCartPane extends GridPane {

    private LoadSaveContext loadSaveContext;
    private ShoppingCartController shoppingCartController;

    public ShoppingCartPane(LoadSaveContext context) {
        shoppingCartController = new ShoppingCartController(context);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        TextField code = new TextField();
        code.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shoppingCartController.addArticle(code.getText());
                code.setText("");
            }
        });
        this.add(code, 0, 0);

        TableView cartView = new TableView(shoppingCartController.getCartContents());
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
        shoppingCartController.registerObserver(observerTotalPrice);
    }
}
