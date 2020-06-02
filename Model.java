package com.example.inventory;

public class Model {
    private String id;
    private String barcodeNumber,productName,price,date;

    public Model() { }

    public Model(String barcode_number, String product_name, String price, String date) {
        this.barcodeNumber = barcode_number;
        this.productName = product_name;
        this.price = price;
        this.date = date;
    }

    public Model(String id, String barcodeNumber, String productName, String price, String date) {
        this.id = id;
        this.barcodeNumber = barcodeNumber;
        this.productName = productName;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarcodeNumber() {
        return barcodeNumber;
    }

    public void setBarcodeNumber(String barcodeNumber) {
        this.barcodeNumber = barcodeNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}