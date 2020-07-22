package ru.dyadischevma.smartfintest.data.entity;

public class ReceiptItem {
    final Good good;
    final double quantity;
    final double totalPrice;

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