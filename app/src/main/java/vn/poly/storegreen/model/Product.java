package vn.poly.storegreen.model;

public class Product {
    public String iD;
    public String productName;
    public double importPrices;
    public double prices;
    public double amount;
    public byte[] img;
    public String describe;
    public String dateAdded, dateExpiration;
    public  int sale;
    public Product() {
    }



    public Product(String iD, String productName, double importPrices, double prices, double amount, byte[] img, String describe, String dateAdded, String dateExpiration, int sale) {
        this.iD = iD;
        this.productName = productName;
        this.importPrices = importPrices;
        this.prices = prices;
        this.amount = amount;
        this.img = img;
        this.describe = describe;
        this.dateAdded = dateAdded;
        this.dateExpiration = dateExpiration;
        this.sale = sale;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getImportPrices() {
        return importPrices;
    }

    public void setImportPrices(double importPrices) {
        this.importPrices = importPrices;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
