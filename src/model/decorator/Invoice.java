package model.decorator;

/**
 * @author Wouter V.E.
 */

public abstract class Invoice {
    String Text = "Empty Invoice";

    public String getText() {
        return Text;
    }

    public abstract String  getInvoice();
}
