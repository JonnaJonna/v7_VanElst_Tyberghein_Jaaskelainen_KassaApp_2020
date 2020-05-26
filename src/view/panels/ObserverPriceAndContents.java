package view.panels;

import controller.ShoppingCartObserver;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import model.Article;
import model.discountStrategy.DiscountContext;

import java.text.DecimalFormat;

/**
 * @author Ruben T.
 */
public class ObserverPriceAndContents implements ShoppingCartObserver {
    private static DecimalFormat dec = new DecimalFormat("0.00");
    private Label prijsLabel;
    private Label discountPrice;
    private Label savedPrice;
    private TableView tableView;
    private DiscountContext discount;

    public ObserverPriceAndContents(Label prijsLabel, TableView tableView, Label discountPrice,
                                    Label savedPrice, DiscountContext discountContext) {
        this.prijsLabel = prijsLabel;
        this.tableView = tableView;
        this.discountPrice = discountPrice;
        this.discount = discountContext;
        this.savedPrice = savedPrice;
    }

    @Override
    public void update(double totalPrice, ObservableList<Article> cart, double discountP) {
        prijsLabel.setText(dec.format(totalPrice) );
        tableView.setItems(cart);
        discountPrice.setText(dec.format(discountP));
        savedPrice.setText("- " + dec.format((totalPrice-discountP)));
    }
}
