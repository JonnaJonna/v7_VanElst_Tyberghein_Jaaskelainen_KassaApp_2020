package view.panels;

import controller.ShoppingCartObserver;
import javafx.scene.control.Label;

public class ObserverTotalPrice implements ShoppingCartObserver {

    private Label prijsLabel;

    public ObserverTotalPrice(Label prijsLabel) {
        this.prijsLabel = prijsLabel;
    }

    @Override
    public void update(double totalPrice) {
        prijsLabel.setText(Double.toString(totalPrice));
    }
}
