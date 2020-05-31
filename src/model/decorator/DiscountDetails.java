package model.decorator;

import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class DiscountDetails extends InvoiceDecorator{
    Invoice invoice;
    ShoppingCart cart;
    public DiscountDetails(Invoice invoice){
        this.invoice = invoice;
    }
    @Override
    public String getInvoice() {
        return invoice.getInvoice() +  "\nPrice before discount: €" + cart.getTotalPrice() + "\nTotal discount: €" + (cart.getTotalPrice()-cart.getTotalAfterDiscount());
    }
}
