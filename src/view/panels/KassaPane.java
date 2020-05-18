package view.panels;

import controller.ShoppingCartController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * @author Ruben T
 */

public class KassaPane extends GridPane {

    public KassaPane(ShoppingCartController shoppingCartController) {
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
    }
}
