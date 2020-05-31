package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import model.Shop;
import view.panels.CashRegisterPane;
import view.panels.LogPane;
import view.panels.ProductOverviewPane;
import view.panels.SettingsPane;

public class CashRegisterMainPane extends BorderPane {

	public CashRegisterMainPane(Shop shop){
	    TabPane tabPane = new TabPane();

        LogPane logPane = new LogPane();
        Tab logTab = new Tab("Log", logPane);

        CashRegisterPane cashRegisterPane = new CashRegisterPane(shop, logPane);
        Tab kassaTab = new Tab("Cash desk", cashRegisterPane);

        ProductOverviewPane productOverviewPane = new ProductOverviewPane(shop);
        Tab artikelTab = new Tab("Articles",productOverviewPane);

        SettingsPane settingsPane = new SettingsPane(shop);
        Tab instellingTab = new Tab("Settings", settingsPane);


        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}

}
