package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.LoadSaveContext;
import model.LoadSaveFactory;
import model.database.LoadSaveStrategy;
import view.panels.ProductOverviewPane;
import view.panels.ShoppingCartPane;

import java.io.*;
import java.util.Properties;

public class KassaMainPane extends BorderPane {
    private LoadSaveContext loadSaveContext;

	public KassaMainPane(){
	    TabPane tabPane = new TabPane();
        ShoppingCartPane shoppingCartPane = new ShoppingCartPane(this.getLoadSaveContext());
        Tab kassaTab = new Tab("Kassa", shoppingCartPane);
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(this.getLoadSaveContext());
        Tab artikelTab = new Tab("Artikelen",productOverviewPane);
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
	private LoadSaveContext getLoadSaveContext() {
        Properties properties = new Properties();
        try(InputStream fis = new FileInputStream("src/files/config.properties")){
            properties.load(fis);
            loadSaveContext = new LoadSaveContext();
            //get strategy from properties file
            LoadSaveStrategy loadSaveStrategy = LoadSaveFactory.createLoadSaveStrategy(properties.getProperty("strategy"));
            loadSaveContext.setStrategy(loadSaveStrategy);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadSaveContext;
    }
}
