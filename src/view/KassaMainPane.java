package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Shop;
import view.panels.KassaPane;
import view.panels.LogPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

public class KassaMainPane extends BorderPane {

	public KassaMainPane(Shop shop){
	    TabPane tabPane = new TabPane();

        KassaPane kassaPane = new KassaPane(shop);
        Tab kassaTab = new Tab("Cash desk", kassaPane);

        ProductOverviewPane productOverviewPane = new ProductOverviewPane(shop);
        Tab artikelTab = new Tab("Articles",productOverviewPane);

        SettingsPane settingsPane = new SettingsPane(shop);
        Tab instellingTab = new Tab("Settings", settingsPane);

        LogPane logPane = new LogPane();
        Tab logTab = new Tab("Log", logPane);

        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}

}
