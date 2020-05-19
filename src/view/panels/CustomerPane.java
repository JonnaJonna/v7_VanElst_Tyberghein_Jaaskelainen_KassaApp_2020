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

        TableColumn<Article, Void> removeButton = createRemoveButton(shop.getShoppingCartController());

        cartView.getColumns().addAll(colDescription, colPrice, colAantal, removeButton);

        Label info = new Label("De huidige prijs is: €");
        Label bedrag = new Label("0");
        this.add(info, 0, 2);
        this.add(bedrag, 1, 2);

        ObserverTotalPrice observerTotalPrice = new ObserverTotalPrice(bedrag);
        shop.getShoppingCartController().registerObserver(observerTotalPrice);
    }

    private TableColumn<Article, Void> createRemoveButton(ShoppingCartController shoppingCartController) {
        TableColumn<Article, Void> removeButton = new TableColumn<Article, Void>("Remove");
        removeButton.setMinWidth(60);
        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> cellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            shoppingCartController.removeArticle(article);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        removeButton.setCellFactory(cellFactory);
        return removeButton;
    }
}
