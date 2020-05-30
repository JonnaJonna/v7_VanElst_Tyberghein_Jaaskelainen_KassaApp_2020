package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Shop;
import view.panels.CustomerPane;

public class KlantView {

	private Stage stage = new Stage();		
		
	public KlantView(Shop shop){
		stage.setTitle("CASH DESK");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);

		CustomerPane shoppingCartPane = new CustomerPane(shop);
		shoppingCartPane.prefHeightProperty().bind(scene.heightProperty());
		shoppingCartPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(shoppingCartPane);

		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}
