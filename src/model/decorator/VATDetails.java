package model.decorator;

import model.shoppingCart.ShoppingCart;

/**
 * @author Wouter V.E.
 */

public class VATDetails extends InvoiceDecorator{
    Invoice invoice;
    ShoppingCart cart;
    public VATDetails(Invoice invoice, ShoppingCart cart){
        this.invoice = invoice;
        this.cart = cart;
    }
    @Override
    public String getInvoice() {
        return invoice.getInvoice() + "\nPrice excluding VAT: €" + cart.getTotalAfterDiscount()*0.94 + "\nTotal VAT: €" + cart.getTotalAfterDiscount()*0.06;
    }
}
