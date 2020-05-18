package application;

import javafx.application.Application;
import javafx.stage.Stage;
import model.LoadSaveContext;
import model.LoadSaveFactory;
import model.Shop;
import model.database.LoadSaveStrategy;
import view.KassaView;
import view.KlantView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class KassaAppMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		LoadSaveContext loadSaveContext = getLoadSaveContext();
		Shop shop = new Shop(loadSaveContext);

		KassaView kassaView = new KassaView(shop);
		KlantView klantView = new KlantView(shop);
	}

	private LoadSaveContext getLoadSaveContext() {
		LoadSaveContext loadSaveContext;
		Properties properties = new Properties();
		try(InputStream fis = new FileInputStream("src/files/config.properties")){
			properties.load(fis);
			loadSaveContext = new LoadSaveContext();
			//get strategy from properties file
			LoadSaveStrategy loadSaveStrategy = LoadSaveFactory.createLoadSaveStrategy(properties.getProperty("strategy"));
			loadSaveContext.setStrategy(loadSaveStrategy);
			fis.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return loadSaveContext;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
