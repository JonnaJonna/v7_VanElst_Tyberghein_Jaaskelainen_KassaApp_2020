package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.database.ArticleDB;
import view.KassaView;
import view.KlantView;
import model.database.*;
import java.util.ArrayList;

public class KassaAppMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		//Testing the article database
		ArticleDB articleDB = new ArticleDB();
		ArrayList<Article> articles;
		articles = articleDB.load();
		articles.forEach(art-> {
			System.out.println(art);
		});
		articleDB.save(articles);
		//Testing ends
		launch(args);
	}
}
