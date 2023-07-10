package com.example.uasfashiongaming;

public class HistoryItem {
    private String itemName;
    private String itemPrice;

    public HistoryItem(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    public String getName() {
        return itemName;
    }

    public String getPrice() {
        return itemPrice;
    }

}
