package view.panels;

import controller.ShoppingCartController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.Article;
import model.Shop;

/**
 * @author Ruben T and Jonna J.
 */
//tab1 of kassaview
public class KassaPane extends GridPane {
    private int soldCount = 0;
    protected boolean holding = false;
    protected Label bedrag, infoDiscount, discount, infoSaved, saved;
    protected TableView cartView;
    protected Button holdButton;
    protected Button activateButton;
    protected Button paymentButton;
    protected Button cancelButton;

    public KassaPane(Shop shop) {
        ShoppingCartController shoppingCartController = shop.getShoppingCartController();
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        TextField code = new TextField();
        Alert alert = new Alert(Alert.AlertType.NONE);

        holdButton = new Button("HOLD");
        activateButton = new Button("ACTIVATE");
        paymentButton = new Button("PAYMENT");
        cancelButton = new Button("CANCEL");

        //by default the activateButton should be disabled
        activateButton.setDisable(true);
        activateButton.setVisible(false);

        //Tableview the scanned items
        cartView = new TableView(shoppingCartController.getCartContents());
        this.add(cartView, 0, 1);

        TableColumn<Article, String> colDescription = new TableColumn<Article, String>("Description");
        colDescription.setMinWidth(150);
        colDescription.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
        TableColumn<Article, String> colPrice = new TableColumn<Article, String>("Price");
        colPrice.setMinWidth(150);
        colPrice.setCellValueFactory(new PropertyValueFactory<Article, String>("price"));
        TableColumn<Article, Integer> colAantal = new TableColumn<Article, Integer>("Aantal");
        colAantal.setMinWidth(150);
        colAantal.setCellValueFactory(new PropertyValueFactory<Article, Integer>("stock"));

        TableColumn<Article, Void> removeButton = createRemoveButton(shoppingCartController);

        cartView.getColumns().addAll(colDescription, colPrice, colAantal, removeButton);

        //Total price label
        Label info = new Label("De huidige prijs is: €");
        bedrag = new Label("0");
        infoSaved = new Label("You saved: € ");
        saved = new Label("0");
        infoDiscount = new Label("Total after discount: € ");
        discount = new Label("0");
        this.add(info, 0, 2);
        this.add(bedrag, 1, 2);
        this.add(infoSaved, 0, 3);
        this.add(saved, 1, 3);
        this.add(infoDiscount, 0, 4);
        this.add(discount, 1, 4);

        ObserverPriceAndContents observerPriceAndContents = new ObserverPriceAndContents(bedrag, cartView,
                discount, saved, shop.getDiscountContext());
        shoppingCartController.addObserver(observerPriceAndContents);

        //Scanning items
        code.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!shoppingCartController.addArticle(code.getText())){
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Non existing code!");
                    alert.show();
                }
                else{
                    //add to tableview
                    code.setText("");
                }
            }
        });
        this.add(code, 0, 0);

        holdButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shoppingCartController.putCartOnHold();
                holding = true;
                holdActiveButtons();
            }
        });

        activateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shoppingCartController.getCartFromHold();
                holding = false;
                holdActiveButtons();
            }
        });
        //TODO handle payments
        paymentButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shoppingCartController.sellCart();
                if(holding == true){
                    if(soldCount < 2){
                        soldCount++;
                        System.out.println(soldCount);
                    }
                    else{
                        System.out.println("Hold is cancelled!");
                        holding = false;
                        holdActiveButtons();
                    }
                } else soldCount = 0;
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shoppingCartController.cancelCart();
            }
        });

        this.add(holdButton, 0, 5);
        this.add(activateButton, 0, 5);
        this.add(cancelButton, 1, 5);
        this.add(paymentButton, 2, 5);
    }
    private TableColumn<Article, Void> createRemoveButton(ShoppingCartController shoppingCartController) {
        TableColumn<Article, Void> removeButton = new TableColumn<Article, Void>("Remove");
        removeButton.setMinWidth(100);
        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> cellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button btn = new Button("Remove");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Article article = getTableView().getItems().get(getIndex());
                            shoppingCartController.removeArticle(article);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        removeButton.setCellFactory(cellFactory);
        return removeButton;
    }

    private void holdActiveButtons(){
        if(holding){    //activateButton visible
            holdButton.setDisable(true);
            holdButton.setVisible(false);

            activateButton.setDisable(false);
            activateButton.setVisible(true);
        } else{   //hold button visible
            activateButton.setDisable(true);
            activateButton.setVisible(false);

            holdButton.setDisable(false);
            holdButton.setVisible(true);
        }
    }
    public void updateView(double totalPrice, ObservableList<Article> cart, double discountPrice){
        bedrag.setText(Double.toString(totalPrice));
        cartView.setItems(cart);
    }
}
