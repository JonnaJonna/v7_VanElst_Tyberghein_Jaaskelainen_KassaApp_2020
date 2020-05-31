package model.decorator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Article;
import model.KassaProperties;
import model.shoppingCart.ShoppingCart;

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

        public Invoice completeInvoice(ShoppingCart cart){
        Invoice invoice = new DefaultInvoice(cart);
        if(properties.getNeedsHeaderText() && !(properties.getHeaderText().equals("This is header")||properties.getHeaderText().trim().equals(""))){
            invoice = new GeneralHeaderText(invoice, properties);
        }
        if(properties.getNeedsDateAndTime()){
            invoice = new DateAndTime(invoice);
        }
        if(properties.getNeedsDiscountDetails()){
            invoice = new DiscountDetails(invoice, cart);
        }
        if(properties.getNeedsVATDetails()){
            invoice = new VATDetails(invoice, cart);
        }
        if(properties.getNeedsFooterText() && !(properties.getFooterText().equals("This is footer") || properties.getFooterText().trim().equals(""))){
            invoice = new GeneralFooterText(invoice, properties);
        }
        return invoice;
    }

    @Override
    public String getInvoice() {
        return Text;
    }
}
