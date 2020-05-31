package view.panels;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import java.time.LocalDateTime;


public class LogPane extends GridPane {

    protected Text logText = new Text("No sales have been made yet.");

    public LogPane(){
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(20);

        this.add(logText, 1, 1);
    }

    public void addSaleToLog(double totalprice, double discountedPrice){
        if(logText.getText().equals("No sales have been made yet.")){
            logText.setText("");
        }
        LocalDateTime timestamp = LocalDateTime.now();
        String date = timestamp.getDayOfMonth() + "/" + timestamp.getMonth().toString() + "/" + timestamp.getYear();
        String time = timestamp.getHour() + ":" + timestamp.getMinute() + ":" + timestamp.getSecond();

        logText.setText(logText.getText() + "\nSale made on " + date + " at " + time + ": \nTotal price of €" + totalprice +  " with discount of €" + (totalprice - discountedPrice) + " for finalised price of €" + discountedPrice + ".\n\n" );
    }
}
