package view.panels;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Article;
import model.LoadSaveContext;

public class ProductOverviewPane extends GridPane {
	private LoadSaveContext loadSaveContext;
	private TableView<Article> table;

	public ProductOverviewPane(LoadSaveContext context) {
		this.loadSaveContext = context;
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Products:"), 0, 0, 1, 1);
		table = new TableView<Article>();
		table.setItems(loadSaveContext.load());	//reads the items from file
		TableColumn<Article, String> colCode = new TableColumn<Article, String>("Code");
		colCode.setMinWidth(100);
		colCode.setCellValueFactory(new PropertyValueFactory<Article, String>("articleCode"));
		TableColumn<Article, String> colDescription = new TableColumn<Article, String>("Description");
		colDescription.setMinWidth(200);
		colDescription.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
		TableColumn<Article, String> colGroup = new TableColumn<Article, String>("Group");
		colGroup.setMinWidth(200);
		colGroup.setCellValueFactory(new PropertyValueFactory<Article, String>("articleGroup"));
		TableColumn<Article, String> colPrice = new TableColumn<Article, String>("Price");
		colPrice.setMinWidth(100);
		colPrice.setCellValueFactory(new PropertyValueFactory<Article, String>("price"));
		TableColumn<Article, String> colStock = new TableColumn<Article, String>("Stock");
		colStock.setMinWidth(100);
		colStock.setCellValueFactory(new PropertyValueFactory<Article, String>("stock"));

		table.getColumns().addAll(colCode,colDescription, colGroup, colPrice,colStock);
		table.getSortOrder().add(colDescription);

		this.getChildren().addAll(table);
	}
	public void refresh(){table.refresh();}
}
