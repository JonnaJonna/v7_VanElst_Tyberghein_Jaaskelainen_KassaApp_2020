package model.decorator;

import model.KassaProperties;

/**
 * @author Wouter V.E.
 */

public class GeneralHeaderText extends InvoiceDecorator{
    Invoice invoice;
    KassaProperties properties;

    public GeneralHeaderText(Invoice invoice){
        this.invoice = invoice;
    }
    @Override
    public String getInvoice() {
        return properties.getHeaderText() + "\n" + invoice.getInvoice();
    }
}
