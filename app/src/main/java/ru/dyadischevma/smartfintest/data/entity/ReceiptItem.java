package ru.dyadischevma.smartfintest.data.entity;

public class ReceiptItem {
    private final Good good;
    private final double quantity;
    private final double totalPrice;

    public ReceiptItem(Good good, double quantity) {
        this.good = good;
        this.quantity = quantity;
        totalPrice = good.getPrice() * quantity;
    }

    public Good getGood() {
        return good;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}