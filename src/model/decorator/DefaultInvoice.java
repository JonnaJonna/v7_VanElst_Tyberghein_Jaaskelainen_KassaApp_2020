package model.decorator;

import javafx.collections.ObservableList;
import model.Article;
import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class DefaultInvoice extends Invoice {

    public ObservableList<Article> invoiceItems;

    public DefaultInvoice(ShoppingCart cart){
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
    public Invoice completeInvoice(ShoppingCart cart, boolean needsHeaderText, boolean needsDateAndTime, boolean needsDiscountDetails, boolean needsVATDetails, boolean needsFooterText){
        Invoice invoice = new DefaultInvoice(cart);
        if(needsHeaderText){
            invoice = new GeneralHeaderText(invoice);
        }
        if(needsDateAndTime){
            invoice = new DateAndTime(invoice);
        }
        if(needsDiscountDetails){
            invoice = new DiscountDetails(invoice);
        }
        if(needsVATDetails){
            invoice = new VATDetails(invoice);
        }
        if(needsFooterText){
            invoice = new GeneralFooterText(invoice);
        }
        return invoice;
    }

    @Override
    public String getInvoice() {
        return Text;
    }
}
