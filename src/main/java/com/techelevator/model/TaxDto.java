package com.techelevator.model;

public class TaxDto {
    private double salesTax;

    private String lastUpdated;

    public TaxDto(double salesTax, String lastUpdated) {
        this.salesTax = salesTax;
        this.lastUpdated = lastUpdated;
    }

    public TaxDto() {
    }

    public double getSalesTaxRate() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
