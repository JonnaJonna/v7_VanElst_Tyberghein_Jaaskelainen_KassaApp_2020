package view.panels;

import controller.ShoppingCartObserver;
import javafx.scene.control.Label;
import model.discountStrategy.DiscountContext;
import model.shoppingCart.ShoppingCart;

import java.text.DecimalFormat;
import java.util.function.Consumer;

/**
 * @author Ruben T.
 */
public class ObserverPriceAndContents implements ShoppingCartObserver {
    private static DecimalFormat dec = new DecimalFormat("0.00");
    private Label prijsLabel;
    private Label discountPrice;
    private Label savedPrice;
    private DiscountContext discount;
    private Runnable contentsRefresher;

    public ObserverPriceAndContents(Label prijsLabel, Label discountPrice,
                                    Runnable contentsRefresher,
                                    Label savedPrice, DiscountContext discountContext) {
        this.prijsLabel = prijsLabel;
        this.discountPrice = discountPrice;
        this.discount = discountContext;
        this.savedPrice = savedPrice;
        this.contentsRefresher = contentsRefresher;
    }

    @Override
    public void update(double totalPrice, ShoppingCart cart, double discountP) {
        prijsLabel.setText(dec.format(totalPrice) );
        contentsRefresher.run();
        discountPrice.setText(dec.format(discountP));
        savedPrice.setText("- " + dec.format((totalPrice-discountP)));
    }
}
