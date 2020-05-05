package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.database.TextLoadSaveStrategy;
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
		TextLoadSaveStrategy textLoadSaveStrategy = new TextLoadSaveStrategy();
		ObservableList<Article> articles;
		articles = textLoadSaveStrategy.load();
		articles.forEach(art-> {
			System.out.println(art);
		});
		textLoadSaveStrategy.save(articles);
		//Testing ends
		launch(args);
	}
}
