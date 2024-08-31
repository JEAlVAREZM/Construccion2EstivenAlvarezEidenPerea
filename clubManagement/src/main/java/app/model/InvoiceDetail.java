package app.model;

public class InvoiceDetail {
    private long id;
    private Invoice invoice; //
    private int itemNumber;
    private String concept;
    private double itemValue;

    public InvoiceDetail() {
    }

    public InvoiceDetail(long id, Invoice invoice, int itemNumber, String concept, double itemValue) {
        this.id = id;
        this.invoice = invoice;
        this.itemNumber = itemNumber;
        this.concept = concept;
        this.itemValue = itemValue;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public double getItemValue() {
        return itemValue;
    }

    public void setItemValue(double itemValue) {
        this.itemValue = itemValue;
    }
}
