package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;
import model.Shop;
import view.KassaView;
import view.KlantView;
import model.KassaProperties;

public class KassaAppMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		KassaProperties properties = new KassaProperties();	//application properties
		LoadSaveContext loadSaveContext = properties.getLoadSaveStrategyContext();	//application context
		DiscountContext discountContext = properties.getDiscountStrategyContext();
		Shop shop = new Shop(loadSaveContext, discountContext);
		KassaView kassaView = new KassaView(shop);
		KlantView klantView = new KlantView(shop);

		System.out.print(discountContext.getStrategy());
		System.out.println("\n");
		System.out.println(discountContext.getPercentage() + " " + discountContext.getStrategy());
	}
	public static void main(String[] args) {
		launch(args);
	}
}
