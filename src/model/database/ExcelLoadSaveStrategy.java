package model.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Article;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import excel.ExcelPlugin;

/** @author Jonna J.
 */

public class ExcelLoadSaveStrategy implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin = new ExcelPlugin();
    private static ExcelLoadSaveStrategy uniqueInstance;

    Article article = null;
    private ObservableList<Article> articles;
    private File filename = new File("src/files/artikel.xls");

    private ExcelLoadSaveStrategy(){}

    public static ExcelLoadSaveStrategy getInstance(){
        if(uniqueInstance == null){
            uniqueInstance = new ExcelLoadSaveStrategy();
        }
        return uniqueInstance;
    }

    @Override
    public ObservableList<Article> load() {
        //arraylist<arraylist<string>> -> observableList<Article>
        articles = FXCollections.observableArrayList(new ArrayList<Article>());
        try {
            ArrayList<ArrayList<String>> arr = excelPlugin.read(filename);
            for(int i = 0; i < arr.size(); i++){
                String code = arr.get(i).get(0);
                String description = arr.get(i).get(1);
                String group = arr.get(i).get(2);
                String price = arr.get(i).get(3);
                String stock = arr.get(i).get(4);
                article = new Article(Integer.parseInt(code.trim()),description,group,
                        Double.parseDouble(price.trim()),Integer.parseInt(stock.trim()));
                articles.add(article);
                //System.out.println(article);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public void save(ObservableList<Article> articles) {
        //observablelist<Article> -> arraylist<arraylist<string>>
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        articles.forEach(art-> {
            ArrayList<String> str = new ArrayList<String>();
            str.add(String.valueOf(art.getArticleCode()));
            str.add(art.getDescription());
            str.add(art.getArticleGroup());
            str.add(String.valueOf(art.getPrice()));
            str.add(String.valueOf(art.getStock()));
            list.add(str);
        });
        try {
            excelPlugin.write(filename, list);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
