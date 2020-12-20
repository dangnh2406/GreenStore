package vn.poly.storegreen.model;

public class BillDetai {
    public String idBill;
    public String IdProduct;
    public double quantityPurchased;
    public double importPrices;
    public double total;

    public BillDetai() {
    }

    public BillDetai(String idBill, String idProduct, double quantityPurchased, double importPrices, double total) {
        this.idBill = idBill;
        IdProduct = idProduct;
        this.quantityPurchased = quantityPurchased;
        this.importPrices = importPrices;
        this.total = total;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdProduct() {
        return IdProduct;
    }

    public void setIdProduct(String idProduct) {
        IdProduct = idProduct;
    }

    public double getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(double quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public double getImportPrices() {
        return importPrices;
    }

    public void setImportPrices(double importPrices) {
        this.importPrices = importPrices;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
