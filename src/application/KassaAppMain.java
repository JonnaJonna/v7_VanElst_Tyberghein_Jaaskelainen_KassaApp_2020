package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.database.TextDatabase;
import view.KassaView;
import view.KlantView;
import model.Article;

public class KassaAppMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		//Testing the article database
		TextDatabase textDatabase = new TextDatabase();
		ObservableList<Article> articles;
		articles = textDatabase.load();
		articles.forEach(art-> {
			System.out.println(art);
		});
		textDatabase.save(articles);
		//Testing ends
		launch(args);
	}
}
