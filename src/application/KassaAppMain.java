package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.LoadSaveContext;
import view.KassaView;
import view.KlantView;

import java.util.ArrayList;
import java.util.List;

public class KassaAppMain extends Application {
	private LoadSaveContext loadSaveContext;
	@Override
	public void start(Stage primaryStage) {
		loadSaveContext = new LoadSaveContext();
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();

		List<String> stra;
		stra = loadSaveContext.getStrategyList();
		stra.forEach(st-> {
			System.out.println(st);
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
