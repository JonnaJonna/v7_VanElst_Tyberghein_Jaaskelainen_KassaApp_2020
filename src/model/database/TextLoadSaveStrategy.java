package model.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Article;

/**
 * @author Jonna J.
 */

public class TextLoadSaveStrategy implements LoadSaveStrategy {

    private static TextLoadSaveStrategy uniqueInstance;

    Article article = null;
    private ObservableList<Article> articles;
    private File filename = new File("src/files/article.txt");

    private TextLoadSaveStrategy(){}

    public static TextLoadSaveStrategy getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new TextLoadSaveStrategy();
        }
        return uniqueInstance;
    }

    /**
     * -load: reads articles from text file
     * @return arraylist of article objects
     */
    @Override
    public ObservableList<Article> load() {
        try{
            Scanner scanner = new Scanner(filename);
            scanner.useDelimiter(",|\\n");
            articles = FXCollections.observableArrayList(new ArrayList<Article>());
            try{
                while(scanner.hasNext()){
                    String code = scanner.next();
                    String description = scanner.next();
                    String group = scanner.next();
                    String price = scanner.next();
                    String stock = scanner.next();
                    article = new Article(Integer.parseInt(code.trim()),description,group,
                            Double.parseDouble(price.trim()),Integer.parseInt(stock.trim()));
                    articles.add(article);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return articles;
    }

    /**
     * -save: writes an arraylist of article objects to a text file
     * @param articles
     */
    @Override
    public void save(ObservableList<Article> articles){
        try{
            FileWriter fw = new FileWriter(filename);
            articles.forEach(art-> {
                String s = art.getArticleCode()+","+art.getDescription()+","+
                        art.getArticleGroup()+","+art.getPrice()+","+art.getStock()+"\n";
                try {
                    fw.write(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
