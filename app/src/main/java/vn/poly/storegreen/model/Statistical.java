package vn.poly.storegreen.model;

public class Statistical {
    public String id;
    public double giaBan;
    public double giaNhap;
    public double soLuongMua;
    public double tongTien;

    public Statistical() {
    }

    public Statistical(String id, double giaBan, double giaNhap, double soLuongMua, double tongTien) {
        this.id = id;
        this.giaBan = giaBan;
        this.giaNhap = giaNhap;
        this.soLuongMua = soLuongMua;
        this.tongTien = tongTien;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(double soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
