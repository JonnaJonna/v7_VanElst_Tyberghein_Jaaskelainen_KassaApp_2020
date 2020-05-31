package model.decorator;

import model.KassaProperties;

/**
 * @author Wouter V.E.
 */

public class GeneralHeaderText extends InvoiceDecorator{
    Invoice invoice;
    KassaProperties properties;

    public GeneralHeaderText(Invoice invoice, KassaProperties properties){
        this.invoice = invoice;
        this.properties = properties;
    }
    @Override
    public String getInvoice() {
        return properties.getHeaderText() + "\n" + invoice.getInvoice();
    }
}
