package model.decorator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Article;
import model.KassaProperties;
import model.shoppingCart.ShoppingCart;

import java.util.ArrayList;

/**
 * @author Wouter V.E.
 */

public class DefaultInvoice extends Invoice {

    public ObservableList<Article> invoiceItems;
    private KassaProperties properties = new KassaProperties();

    public DefaultInvoice(ShoppingCart cart){
        invoiceItems = FXCollections.observableArrayList(cart.getContents());

        for(Article a:cart.getContents()){
            for(Article b:invoiceItems){
                if(a.getArticleCode()==b.getArticleCode()){
                    b.setStock(b.getStock() + 1);
                }
            }
        }
        String itemString = new String();
        for(Article c:invoiceItems){
            itemString += c.getDescription() + "    " + c.getStock() + "   " + c.getPrice() + "\n";
        }
        Text = "Description        Amount   Price\n****************************\n" + itemString + "****************************\nAmount paid(including discount): €" + cart.getTotalAfterDiscount();
    }

    //TODO finalizing a sale calls completeInvoice and prints the result to the console
        public Invoice completeInvoice(ShoppingCart cart){
        Invoice invoice = new DefaultInvoice(cart);
        if(properties.getNeedsHeaderText()){
            invoice = new GeneralHeaderText(invoice);
        }
        if(properties.getNeedsDateAndTime()){
            invoice = new DateAndTime(invoice);
        }
        if(properties.getNeedsDiscountDetails()){
            invoice = new DiscountDetails(invoice);
        }
        if(properties.getNeedsVATDetails()){
            invoice = new VATDetails(invoice);
        }
        if(properties.getNeedsFooterText()){
            invoice = new GeneralFooterText(invoice);
        }
        return invoice;
    }

    @Override
    public String getInvoice() {
        return Text;
    }
}
