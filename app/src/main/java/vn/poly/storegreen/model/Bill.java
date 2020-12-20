package vn.poly.storegreen.model;

import java.io.Serializable;

public class Bill implements Serializable {
    public String idBill;
    public String dateAddBill;
    public String clientName;
    public double total;

    public Bill() {
    }

    public Bill(String idBill, String dateAddBill, String clientName, double total) {

        this.idBill = idBill;
        this.dateAddBill = dateAddBill;
        this.clientName = clientName;
        this.total = total;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getDateAddBill() {
        return dateAddBill;
    }

    public void setDateAddBill(String dateAddBill) {
        this.dateAddBill = dateAddBill;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
