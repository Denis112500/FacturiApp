package com.example.facturiapp.models;

public class Factura {
    private String id;
    private String clientName;
    private double amount;
    private String date;
    private String description;
    private boolean isPaid;

    //Creare factura noua
    public Factura(String id, String clientName, double amount, String date, String description, boolean isPaid) {
        this.id = id;
        this.clientName = clientName;
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.isPaid = isPaid;
    }

    // extragere date
    public String getId() { return id; }
    public String getClientName() { return clientName; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public boolean isPaid() { return isPaid; }
}