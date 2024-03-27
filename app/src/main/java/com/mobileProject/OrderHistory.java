package com.mobileProject;

public class OrderHistory {
    private String listingDate;
    private double price;
    private String status;
    private String bookName;

    public OrderHistory(String listingDate, double price, String status, String bookName) {
        this.listingDate = listingDate;
        this.price = price;
        this.status = status;
        this.bookName = bookName;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String action) {
        this.status = action;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "listingDate='" + getListingDate() + '\'' +
                ", price=" + getPrice() +
                ", action='" + getStatus() + '\'' +
                ", bookName='" + getBookName() + '\'' +
                '}';
    }
}

