package view.panels;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Article;
import model.Shop;

/**
 * @author Ruben T
 */

public class CustomerPane extends GridPane {
    protected Label info;
    protected TableView cartView;
    protected Label bedrag, discount, infoDiscount, infoSaved, saved;

    public CustomerPane(Shop shop) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        cartView = new TableView<>(shop.getShoppingCartController().getCartContentsCondensed());
        this.add(cartView, 0, 1);

        TableColumn<Article, String> colDescription = new TableColumn<Article, String>("Description");
        colDescription.setMinWidth(100);
        colDescription.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
        TableColumn<Article, String> colPrice = new TableColumn<Article, String>("Price");
        colPrice.setMinWidth(100);
        colPrice.setCellValueFactory(new PropertyValueFactory<Article, String>("price"));
        TableColumn<Article, Integer> colAantal = new TableColumn<Article, Integer>("Amount");
        colAantal.setMinWidth(100);
        colAantal.setCellValueFactory(new PropertyValueFactory<Article, Integer>("stock"));

        cartView.getColumns().addAll(colDescription, colPrice, colAantal);

        info = new Label("Current amount is: €");
        bedrag = new Label("0");
        infoSaved = new Label("You saved: € ");
        saved = new Label("0");
        infoDiscount = new Label("Total after discount: € ");
        discount = new Label("0");
        this.add(info, 0, 2);
        this.add(bedrag, 1, 2);
        this.add(infoSaved, 0, 3);
        this.add(saved, 1, 3);
        this.add(infoDiscount, 0, 4);
        this.add(discount, 1, 4);

        ObserverPriceAndContents observerPriceAndContents = new ObserverPriceAndContents(bedrag, cartView, discount,
                saved, shop.getDiscountContext());
        shop.getShoppingCartController().addObserver(observerPriceAndContents);
    }
}
