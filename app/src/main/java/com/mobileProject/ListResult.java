package com.mobileProject;

public class ListResult {
    /*
    listingID
    str.append("Name: " + c.getString(0));
            str.append("\nSeller: " + c.getString(1));
            str.append("\nPrice " + c.getString(2));
            str.append("\nDetails: " + c.getString(3));
            */
     private int row;
     private int listingID;
     private String bName;
     private String sellerName;
     private int sellerID;
     private double price;
     private String details;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getListingID() {
        return listingID;
    }

    public void setListingID(int listingID) {
        this.listingID = listingID;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public ListResult(int row, int listingID, String bName, String sellerName, int sellerID, double price, String details) {
        this.row = row;
        this.listingID = listingID;
        this.bName = bName;
        this.sellerName = sellerName;
        this.sellerID = sellerID;
        this.price = price;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "row=" + row +
                ", listingID=" + listingID +
                ", bName='" + bName + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", sellerID=" + sellerID +
                ", price=" + price +
                ", details='" + details + '\'' +
                '}';
    }
}
