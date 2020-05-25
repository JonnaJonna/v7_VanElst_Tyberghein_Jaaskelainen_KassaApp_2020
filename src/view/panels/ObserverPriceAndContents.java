package view.panels;

import controller.ShoppingCartObserver;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Article;

/**
 * @author Ruben T.
 */
public class ObserverPriceAndContents implements ShoppingCartObserver {

    private Label prijsLabel;
    private TableView tableView;

    public ObserverPriceAndContents(Label prijsLabel, TableView tableView) {
        this.prijsLabel = prijsLabel;
        this.tableView = tableView;
    }

    @Override
    public void update(double totalPrice, ObservableList<Article> cart) {
        prijsLabel.setText(Double.toString(totalPrice));
        tableView.setItems(cart);
    }
}
