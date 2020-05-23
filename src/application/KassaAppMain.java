package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.LoadSaveContext;
import model.Shop;
import view.KassaView;
import view.KlantView;
import model.KassaProperties;

public class KassaAppMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		KassaProperties properties = new KassaProperties();	//application properties
		LoadSaveContext loadSaveContext = properties.getLoadSaveStrategy();	//application context
		Shop shop = new Shop(loadSaveContext);
		KassaView kassaView = new KassaView(shop);
		KlantView klantView = new KlantView(shop);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
