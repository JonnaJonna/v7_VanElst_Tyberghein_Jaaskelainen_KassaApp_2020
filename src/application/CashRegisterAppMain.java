package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.discountStrategy.DiscountContext;
import model.loadSaveStrategy.LoadSaveContext;
import model.Shop;
import view.CashRegisterView;
import view.CustomerView;
import model.KassaProperties;

public class CashRegisterAppMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		KassaProperties properties = new KassaProperties();	//application properties
		LoadSaveContext loadSaveContext = properties.getLoadSaveStrategyContext();	//application context
		DiscountContext discountContext = properties.getDiscountStrategyContext();
		Shop shop = new Shop(loadSaveContext, discountContext);
		CashRegisterView cashRegisterView = new CashRegisterView(shop);
		CustomerView customerView = new CustomerView(shop);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
