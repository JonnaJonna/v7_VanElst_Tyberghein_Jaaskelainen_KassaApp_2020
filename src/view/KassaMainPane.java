package view;


import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Article;
import model.LoadSaveContext;
import model.LoadSaveFactory;
import model.database.LoadSaveStrategy;
import model.database.TextLoadSaveStrategy;
import view.panels.ProductOverviewPane;

import java.util.ArrayList;
import java.util.List;

public class KassaMainPane extends BorderPane {
    private LoadSaveContext loadSaveContext;

	public KassaMainPane(){
	    TabPane tabPane = new TabPane(); 	    
        Tab kassaTab = new Tab("Kassa");
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
	private LoadSaveContext getLoadSaveContext(){
        loadSaveContext = new LoadSaveContext();
        //this still needs to be fetched from the properties (properties class)
        LoadSaveStrategy loadSaveStrategy = LoadSaveFactory.createLoadSaveStrategy("TEXT");
        loadSaveContext.setStrategy(loadSaveStrategy);
        return loadSaveContext;
    }
}
